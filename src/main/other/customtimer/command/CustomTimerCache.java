package me.jesusmx.tacohub.customtimer.command;

import lombok.Getter;
import me.jesusmx.tacohub.customtimer.CharProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author UKry
 * @Project TacoHub
 **/

public class CustomTimerCache {

    @Getter private static HashMap<String, CustomTimer> timers;
    @Getter private static HashMap<CustomTimer, CharProcessor> chars;
    @Getter private static HashMap<CustomTimer, Integer> charPos;
    @Getter private static HashMap<CustomTimer, Integer> reaming;
    @Getter private static List<CustomTimer> inited;
    @Getter private static HashMap<CustomTimer, String> lastText;

    public CustomTimerCache() {
        timers = new HashMap<>();
        chars = new HashMap<>();
        charPos = new HashMap<>();
        reaming = new HashMap<>();
        inited = new ArrayList<>();
        lastText = new HashMap<>();
    }

    public static List<CustomTimer> getActiveCustomTimers() {
        return timers.values().stream().filter(CustomTimer::isActivated).collect(Collectors.toList());
    }

}
