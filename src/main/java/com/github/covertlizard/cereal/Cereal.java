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
    private final Map<String, Object> serialize = new LinkedHashMap<>();
    private final String alias;

    /**
     * Implements ConfigurationSerializable and provides utility methods for serialization
     * @param alias the alias of your class to be used during serialization
     */
    protected Cereal(String alias)
    {
        this.alias = alias.isEmpty() ? this.getClass().getSimpleName() : alias;
        ConfigurationSerialization.registerClass(this.getClass(), this.alias);
    }

    /**
     * Implements ConfigurationSerializable and provides utility methods for serialization
     */
    protected Cereal()
    {
        this("");
    }

    @Override
    public Map<String, Object> serialize()
    {
        return this.serialize;
    }

    public String getAlias()
    {
        return this.alias;
    }
}