package com.github.covertlizard.cereal;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
     * @param sections the sections to be created
     */
    public Config(File file, String header, Map<String, Object> defaults, String... sections)
    {
        this.file = file;
        this.header = header == null ? "" : header;
        if(!this.file.exists()) this.save();
        Arrays.asList(sections).forEach(string -> {if(!super.contains(string)) super.createSection(string); this.save();});
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
     * @param sections the sections to be created
     */
    public Config(JavaPlugin plugin, String directory, String name, String header, Map<String, Object> defaults, String... sections)
    {
        this(new File(plugin.getDataFolder().getPath() + File.separator + directory, name.endsWith(".yml") ? name : name + ".yml"), header, defaults, sections);
    }

    /**
     * Creates the configuration file and loads defaults if specified
     * @param plugin the plugin
     * @param directory the directory to save the configuration file in
     * @param name the name of the configuration file
     * @param header the header of the configuration file
     * @param sections the sections to be created
     */
    public Config(JavaPlugin plugin, String directory, String name, String header, String... sections)
    {
        this(plugin, directory, name, header, null, sections);
    }

    /**
     * Creates the configuration file and loads defaults if specified
     * @param plugin the plugin
     * @param directory the directory to save the configuration file in
     * @param name the name of the configuration file
     * @param sections the sections to be created
     */
    public Config(JavaPlugin plugin, String directory, String name, String... sections)
    {
        this(plugin, directory, name, null, sections);
    }

    /**
     * Saves the configuration file with any new properties
     */
    public void save()
    {
        try
        {
            super.save(this.file);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the configuration file
     */
    public void load()
    {
        try
        {
            super.load(this.file);
        }
        catch (InvalidConfigurationException | IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the object at the specified path
     * @param path the path to set the object
     * @param object the object to set
     * @param save whether or not to save and load after setting value
     */
    public void set(String path, Object object, boolean save)
    {
        super.set(path, object);
        if(!save) return;
        this.save();
        this.load();
    }

    /**
     * Sets the object at the specified path
     * @param path the path to set the object
     * @param object the object to set
     */
    public void set(String path, Object object)
    {
        this.set(path, object, true);
    }

    /**
     * Serializes a ConfigurationSerializable at the specified path
     * @param path the path to serialize the object to
     * @param serializable the class to serialize
     * @param save whether or not to save and load after setting value
     */
    public void set(String path, ConfigurationSerializable serializable, boolean save)
    {
        super.set(path, serializable.serialize());
        if(!save) return;
        this.save();
        this.load();
    }
    /**
     * Serializes a ConfigurationSerializable at the specified path
     * @param path the path to serialize the object to
     * @param serializable the class to serialize
     */
    public void set(String path, ConfigurationSerializable serializable)
    {
        this.set(path, serializable, true);
    }

    /**
     * Gathers a configuration object from the specified path
     * @param path the path
     * @return the configuration object
     */
    public ConfigObject get(String path)
    {
        return new ConfigObject(super.get(path));
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