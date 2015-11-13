package com.github.covertlizard.cereal;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Project: Cereal
 * Created: 11/12/2015
 * Time: 12:05
 * Package: com.github.covertlizard.cereal
 * Description: Custom implementation of the ConfigurationSerializable class.
 */
@SuppressWarnings("all")
public class Cereal implements ConfigurationSerializable
{
    protected final Map<String, Object> serialize = new LinkedHashMap<>();

    /**
     * Puts a key and a value into the serialize map
     * @param string the key
     * @param object the value
     */
    protected void put(String string, Object object)
    {
        this.serialize.put(string, object);
    }

    /**
     * Removes a key and its value from the map
     * @param string the key
     */
    protected void remove(String string)
    {
        this.serialize.remove(string);
    }

    @Override
    public Map<String, Object> serialize()
    {
        return this.serialize;
    }

    /**
     * Registers custom classes so they can be deserialized
     * @param classes the clases to register
     */
    public static void register(Class<? extends ConfigurationSerializable>... classes)
    {
        for(Class<? extends ConfigurationSerializable> clazz : classes) Cereal.register(clazz, clazz.getSimpleName());
    }

    /**
     * Registers the custom class to be deserialized
     * @param clazz the ConfigurationSerializable class
     * @param alias the alias to use when saving to a configuration file
     */
    public static void register(Class<? extends ConfigurationSerializable> clazz, String alias)
    {
        ConfigurationSerialization.registerClass(clazz, alias);
    }
}