package me.jesusmx.tacohub;

import io.github.fxmxgragfx.api.item.ItemHandler;
import io.github.fxmxgragfx.api.listener.ListenersHandler;
import io.github.nosequel.tab.shared.TabHandler;
import io.github.nosequel.tab.v1_10_R1.v1_10_R1TabAdapter;
import io.github.nosequel.tab.v1_12_R1.v1_12_R1TabAdapter;
import io.github.nosequel.tab.v1_14_R1.v1_14_R1TabAdapter;
import io.github.nosequel.tab.v1_15_R1.v1_15_R1TabAdapter;
import io.github.nosequel.tab.v1_16_R3.v1_16_R3TabAdapter;
import io.github.nosequel.tab.v1_7_R4.v1_7_R4TabAdapter;
import io.github.nosequel.tab.v1_8_R3.v1_8_R3TabAdapter;
import io.github.nosequel.tab.v1_9_R1.v1_9_R1TabAdapter;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import me.jesusmx.tacohub.commands.impl.TacoHubCommand;
import me.jesusmx.tacohub.commands.impl.builder.BuilderCommand;
import me.jesusmx.tacohub.commands.impl.media.*;
import me.jesusmx.tacohub.commands.impl.queue.JoinQueueCommand;
import me.jesusmx.tacohub.commands.impl.queue.LeaveQueueCommand;
import me.jesusmx.tacohub.commands.impl.queue.ToggleQueueCommand;
import me.jesusmx.tacohub.commands.impl.spawn.SetSpawnCommand;
import me.jesusmx.tacohub.commands.impl.staff.SkullCommand;
import me.jesusmx.tacohub.cosmetics.command.CosmeticsCommand;
import me.jesusmx.tacohub.hooker.Hooker;
import me.jesusmx.tacohub.permissions.PermissionCore;
import me.jesusmx.tacohub.permissions.type.*;
import me.jesusmx.tacohub.provider.ScoreboardProvider;
import me.jesusmx.tacohub.provider.TablistProvider;
import me.jesusmx.tacohub.pvpmode.cache.PvPModeHandler;
import me.jesusmx.tacohub.pvpmode.command.PvPModeCommand;
import me.jesusmx.tacohub.pvpmode.config.PvPModeFile;
import me.jesusmx.tacohub.queue.QueueManager;
import me.jesusmx.tacohub.queue.custom.QueueHandler;
import me.jesusmx.tacohub.utils.CC;
import me.jesusmx.tacohub.utils.bukkit.command.PluginCommand;
import me.jesusmx.tacohub.utils.bukkit.license.TacoLicense;
import me.jesusmx.tacohub.utils.bungee.BungeeUtils;
import me.jesusmx.tacohub.utils.files.cosmetics.ArmorsFile;
import me.jesusmx.tacohub.utils.files.cosmetics.HatsFile;
import me.jesusmx.tacohub.utils.files.cosmetics.ParticlesFile;
import me.jesusmx.tacohub.utils.files.features.*;
import me.jesusmx.tacohub.utils.files.menus.HubSelectorFile;
import me.jesusmx.tacohub.utils.files.menus.ServerSelectorFile;
import me.jesusmx.tacohub.utils.files.menus.SubSelectorFile;
import me.jesusmx.tacohub.utils.files.normal.ConfigFile;
import me.jesusmx.tacohub.utils.files.normal.MessagesFile;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("InstantiationOfUtilityClass")
@Getter
public class TacoHub extends JavaPlugin {

    public static Chat chat;
    private QueueHandler queueHandler;
    private QueueManager queueManager;
    private PermissionCore permissionCore;
    private List<YamlConfiguration> configFiles;

    @Override
    public void onEnable() {
        if(!new TacoLicense(this, ConfigFile.getConfig().getString("LICENSE-KEY"),
                "http://150.136.251.119:8080/api/client",
                "86d35475fe24625ae72b1bb43ac47b4e26262ef0").verify()) {
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getScheduler().cancelTasks(this);
            return;
        }
        if (!this.getDescription().getName().equals("TacoHub") || !this.getDescription().getAuthors().contains("Rui")) {
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getConsoleSender().sendMessage(CC.translate("&cNO CHANGE AUTHOR IN PLUGIN.YML :)!"));
            return;
        }
        this.bungee();
        this.files();
        new PvPModeHandler();
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8] &aThe files have been loaded correctly"));
        new ListenersHandler().registerAll();
        new ItemHandler().registerAll();
        this.commands();
        this.managers();
        this.setupChat();

        this.scoreboard();
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8&8] &aThe scoreboard has been successfully started"));

        this.tablist();
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8] &aThe tablist has been successfully started"));

        this.permissions();
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&6TacoHub&8] &aThe rank system " + this.permissions() + " &aHas been loaded successfully"));
        //new CustomTimerCache();
    }


    private void bungee() {
        new BukkitRunnable() {
            @Override
            public void run() {
                BungeeUtils.refreshGlobalCount();
                BungeeUtils.refreshServerList();
                BungeeUtils.refreshServerCount();
            }
        }.runTaskTimerAsynchronously(this, 10L, 10L);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeUtils());
    }

    private void files() {
        configFiles = Arrays.asList(ConfigFile.getConfig(), HookerFile.getConfig(), HotbarFile.getConfig(), HubSelectorFile.getConfig(), MessagesFile.getConfig(), ScoreboardFile.getConfig(), ServerSelectorFile.getConfig(), TablistFile.getConfig(), CosmeticsFile.getConfig(), HatsFile.getConfig(), SubSelectorFile.getConfig(), ParticlesFile.getConfig(), ArmorsFile.getConfig(), PvPModeFile.getConfig());
    }

    private void commands() {
        new PluginCommand("tacohub", TacoHubCommand.class, "taco", "hub", "hubcore");
        //    Media Commands //
        new PluginCommand("discord", DiscordCommand.class, "dc");
        new PluginCommand("help", HelpCommand.class);
        new PluginCommand("store", StoreCommand.class);
        new PluginCommand("teamspeak", TeamspeakCommand.class, "ts", "ts3");
        new PluginCommand("twitter", TwitterCommand.class);
        new PluginCommand("website", WebsiteCommand.class, "web");
        //    Staff Commands //
        new PluginCommand("builder", BuilderCommand.class, "build", "buildermode");
        new PluginCommand("setspawn", SetSpawnCommand.class);
        new PluginCommand("skull", SkullCommand.class);
        //    Queue Commands //
        new PluginCommand("leavequeue", LeaveQueueCommand.class);
        new PluginCommand("togglequeue", ToggleQueueCommand.class,"pausequeue");
        new PluginCommand("joinqueue", JoinQueueCommand.class, "play");

        // Cosmetics Commands //
        new PluginCommand("cosmetics", CosmeticsCommand.class);

        // PvPMode //
        new PluginCommand("pvpmode", PvPModeCommand.class, "pvp");

        // CustomTimer Commands //
        //new PluginCommand("customtimer", CustomTimerCommand.class, "timers", "customtimers", "timer");
    }

    private void managers() {
        queueManager = new QueueManager();
        queueHandler = new QueueHandler();
        new Hooker();
    }

    public void setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
    }

    private void scoreboard() {
        if (ScoreboardFile.getConfig().getBoolean("SCOREBOARD.ENABLED")) {
            Assemble assemble = new Assemble(this, new ScoreboardProvider());
            assemble.setTicks(200);
            assemble.setAssembleStyle(AssembleStyle.CUSTOM.descending(true).startNumber(16));
        }
    }

    private void tablist() {
        if (TablistFile.getConfig().getBoolean("TABLIST.ENABLE")) {
            if (Bukkit.getVersion().contains("1.7")) {
                new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.8")) {
                new TabHandler(new v1_8_R3TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.9")) {
                new TabHandler(new v1_9_R1TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.10")) {
                new TabHandler(new v1_10_R1TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.12")) {
                new TabHandler(new v1_12_R1TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.14")) {
                new TabHandler(new v1_14_R1TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.15")) {
                new TabHandler(new v1_15_R1TabAdapter(), new TablistProvider(), this, 20L);
            }
            if (Bukkit.getVersion().contains("1.16")) {
                new TabHandler(new v1_16_R3TabAdapter(), new TablistProvider(), this, 20L);
            }
        }
    }

    private String permissions() {
        String core = ConfigFile.getConfig().getString("RANK-SUPPORT");
        switch (core) {
            case "AquaCore":
                permissionCore = new AquaCorePermissionCore();
                return "AquaCore";
            case "Vault":
                permissionCore = new VaultPermissionCore();
                return "Vault";
            case "Mizu":
                permissionCore = new MizuPermissionCore();
                return "Mizu";
            case "Hestia":
                permissionCore = new HestiaPermissionCore();
                return "Hestia";
            case "Zoom":
                permissionCore = new ZoomPermissionCore();
                return "Zoom";
            case "Zoot":
                permissionCore = new ZootPermissionCore();
                return "Zoot";
        }
        return "Nothing";
    }

    @Override
    public void onDisable() {
    }

    public static TacoHub getInstance() {
        return TacoHub.getPlugin(TacoHub.class);
    }

    public Collection<? extends Player> getOnlinePlayers() {
        Collection<Player> collection = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            collection.add(player);
        }
        return collection;
    }
}
