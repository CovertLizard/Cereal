package com.github.covertlizard.cereal;

import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: Cereal
 * Created: 11/10/2015
 * Time: 17:53
 * Package: com.github.covertlizard.cereal
 * Description: A special implementation of the YamlConfiguration class, designed for making configuration files easier.
 */
@SuppressWarnings("all")
public class Config extends YamlConfiguration
{
    private final String header;
    private final File file;

    /**
     * Creates the configuration file and loads defaults if specified
     * @param file the configuration file
     * @param header the header of the configuration file
     * @param defaults the default values for the configuration file
     */
    public Config(File file, String header, Map<String, Object> defaults)
    {
        this.file = file;
        this.header = header == null ? "" : header;
        this.save();
        this.load();
        super.addDefaults(defaults == null ? new HashMap<String, Object>() : defaults);
        super.options().copyDefaults(defaults != null);
        if(!this.header.isEmpty()) super.options().header(this.header);
    }

    /**
     * Creates the configuration file and loads defaults if specified
     * @param plugin the plugin
     * @param directory the directory to save the configuration file in
     * @param name the name of the configuration file
     * @param header the header of the configuration file
     * @param defaults the default values for the configuration file
     */
    public Config(JavaPlugin plugin, String directory, String name, String header, Map<String, Object> defaults)
    {
        this(new File(plugin.getDataFolder().getPath() + File.separator + directory + File.separator + name + ".yml"), header, defaults);
    }

    /**
     * Creates the configuration file and loads defaults if specified
     * @param plugin the plugin
     * @param directory the directory to save the configuration file in
     * @param name the name of the configuration file
     * @param header the header of the configuration file
     */
    public Config(JavaPlugin plugin, String directory, String name, String header)
    {
        this(plugin, directory, name, header, null);
    }

    /**
     * Creates the configuration file and loads defaults if specified
     * @param plugin the plugin
     * @param directory the directory to save the configuration file in
     * @param name the name of the configuration file
     */
    public Config(JavaPlugin plugin, String directory, String name)
    {
        this(plugin, directory, name, null);
    }

    /**
     * Saves the configuration file with any new properties
     */
    public void save()
    {
        try
        {
            if(!this.file.exists())
            {
                this.file.mkdirs();
                this.file.createNewFile();
            }
            Validate.isTrue(this.file.exists(), "The configuration file could not be saved.");
            super.save(this.file);
        }
        catch (IOException ignored)
        {

        }
    }

    /**
     * Loads the configuration file
     */
    public void load()
    {
        Validate.isTrue(this.file.exists(), "The configuration file could not be loaded.");
        try
        {
            super.load(this.file);
        }
        catch (Exception ignored)
        {

        }
    }

    public String getHeader()
    {
        return header;
    }

    public File getFile()
    {
        return file;
    }
}