package com.github.covertlizard.cereal;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: Cereal
 * Created: 11/12/2015
 * Time: 12:20
 * Package: com.github.covertlizard.cereal
 * Description: Utility class that makes casting configuration objects easier
 */
@SuppressWarnings("all")
public class ConfigObject
{
    private final Object object;

    /**
     * Helps casting configuration objects that are deserialized
     * @param object the deserialized object
     */
    public ConfigObject(Object object)
    {
        this.object = object;
    }

    public boolean isString()
    {
        return this.object instanceof String;
    }
    public String asString()
    {
        return (String) this.object;
    }
    public boolean isInteger()
    {
        return this.object instanceof Integer;
    }
    public int asInteger()
    {
        return (int) this.object;
    }
    public boolean isBoolean()
    {
        return this.object instanceof Boolean;
    }
    public boolean asBoolean()
    {
        return (boolean) this.object;
    }
    public boolean isDouble()
    {
        return this.object instanceof Double;
    }
    public double asDouble()
    {
        return (double) this.object;
    }
    public boolean isLong()
    {
        return this.object instanceof Long;
    }
    public long asLong()
    {
        return (long) this.object;
    }
    public boolean isByte()
    {
        return this.object instanceof Byte;
    }
    public byte asByte()
    {
        return (byte) this.object;
    }
    public boolean isList()
    {
        return this.object instanceof List;
    }
    public List asList()
    {
        return (List) this.object;
    }
    public <T>List<T> asList(Class<T> clazz)
    {
        return (List<T>) this.asList().stream().map(e -> clazz.cast(e)).collect(Collectors.toList());
    }
    public boolean isVector()
    {
        return this.object instanceof Vector;
    }
    public Vector asVector()
    {
        return (Vector) this.object;
    }
    public boolean isOfflinePlayer()
    {
        return this.object instanceof OfflinePlayer;
    }
    public OfflinePlayer asOfflinePlayer()
    {
        return (OfflinePlayer) this.object;
    }
    public boolean isItemStack()
    {
        return this.object instanceof ItemStack;
    }
    public ItemStack asItemStack()
    {
        return (ItemStack) this.object;
    }
    public boolean isLocation()
    {
        return this.object instanceof Location;
    }
    public Location asLocation()
    {
        return (Location) this.object;
    }
    public boolean isColor()
    {
        return this.object instanceof Color;
    }
    public Color asColor()
    {
        return (Color) this.object;
    }
    public <T> T as(Class<T> clazz)
    {
        return clazz.cast(this.object);
    }
}