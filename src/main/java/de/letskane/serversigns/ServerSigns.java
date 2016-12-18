package de.letskane.serversigns;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Daniel on 18.12.2016.
 */
public class ServerSigns {

    public static String prefix = "";

    public static HashMap<String, String> servers = new HashMap<String, String>();
    public static HashMap<String, String> serverinfo = new HashMap<String, String>();

    public static File serverfile = new File("./plugins/ServerSigns", "servers.yml");
    public static FileConfiguration server = YamlConfiguration.loadConfiguration(serverfile);

    public static File signfile = new File("./plugins/ServerSigns", "signs.yml");
    public static FileConfiguration sign = YamlConfiguration.loadConfiguration(signfile);

    public ServerSigns() {
        loadFiles();
        Bukkit.getPluginManager().registerEvents(new SignPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignInteractListener(), this);

    }

    public void loadFiles() {
        loadServerFile();
        loadSignFile();

    }

    public void loadServerFile() {

        File serverfile = new File("./plugins/ServerSigns", "servers.yml");
        FileConfiguration server = YamlConfiguration.loadConfiguration(serverfile);
        server.addDefault("Server.Lobby.Adress", "localhost:25565");
        server.addDefault("Server.Lobby.ServerName", "Lobby");
        server.options().copyDefaults(true);

        try {
            server.save(serverfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSignFile() {

        File signfile = new File("./plugins/ServerSigns", "signs.yml");
        FileConfiguration sign = YamlConfiguration.loadConfiguration(signfile);

        try {
            sign.save(signfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> loadServers() {

        HashMap<String, String> serverinfo = new HashMap<String, String>();
        for(String s : server.getConfigurationSection("Server").getKeys(false)) {

            serverinfo.put(server.getString("Server." + s + ".ServerName"), server.getString("Server." + s + ".Adress"));
        }
        return serverinfo;
    }



}
