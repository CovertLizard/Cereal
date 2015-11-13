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