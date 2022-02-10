package io.github.fxmxgragfx.api.cf;

import lombok.experimental.UtilityClass;
import me.jesusmx.tacohub.TacoHub;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@UtilityClass
public class ClassFinder {

    public static  <T> Set<Class<? extends T>> findAll(Class<? extends T> type) {
        Set<Class<? extends T>> classes = new HashSet<>();
        try {
            JarFile jarFile = new JarFile(new File(TacoHub.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if(entry.getName().endsWith(".class") && !entry.getName().contains("$")) {
                    String className = entry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    if(!className.startsWith(TacoHub.class.getPackage().getName())) continue;
                    try {
                        Class<?> clazz = Class.forName(className);
                        try {
                            Class<? extends T> fClass = clazz.asSubclass(type);
                            classes.add(fClass);
                        } catch (Exception ignored) {}
                    } catch (ClassNotFoundException ignored) {}
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
