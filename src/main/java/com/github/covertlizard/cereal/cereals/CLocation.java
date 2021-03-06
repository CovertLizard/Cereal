package com.github.covertlizard.cereal.cereals;

import com.github.covertlizard.cereal.Cereal;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Project: Cereal
 * Created: 11/14/2015
 * Time: 21:59
 * Package: com.github.covertlizard.cereal.cereals
 * Description: Designed for serializing a Location
 */
@SuppressWarnings("all")
public class CLocation extends Cereal
{
    private transient final String world;
    private transient final double x;
    private transient final double y;
    private transient final double z;
    private transient final float pitch;
    private transient final float yaw;

    private transient WeakReference<Location> reference;

    /**
     * Creates a serializeable location
     * @param location the location to serialize
     */
    public CLocation(Location location)
    {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        super.put("world", this.world.toString());
        super.put("x", this.x);
        super.put("y", this.y);
        super.put("z", this.z);
        super.put("pitch", this.pitch);
        super.put("yaw", this.yaw);
    }

    /**
     * Creates a serializeable location
     * @param serialize the serialized location
     */
    public CLocation(Map<String, Object> serialize)
    {
        this.world = serialize == null || serialize.isEmpty() ?  "" : (String) serialize.get("world");
        this.x = serialize == null || serialize.isEmpty() ?  0 : (double) serialize.get("x");
        this.y = serialize == null || serialize.isEmpty() ?  0 : (double) serialize.get("y");
        this.z = serialize == null || serialize.isEmpty() ?  0 : (double) serialize.get("z");
        this.pitch = serialize == null || serialize.isEmpty() ?  0 : (float) ((double) serialize.get("pitch"));
        this.yaw = serialize == null || serialize.isEmpty() ?  0 : (float) ((double) serialize.get("yaw"));
    }

    public String getWorld()
    {
        return this.world;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }

    public float getPitch()
    {
        return this.pitch;
    }

    public float getYaw()
    {
        return this.yaw;
    }

    /**
     * Grabs the the org.bukkit.Location instance
     * @return the org.bukkit.Location instance
     */
    public Location getLocation()
    {
        if(!(this.reference == null || this.reference.get() == null)) return this.reference.get();
        this.reference = new WeakReference<Location>(new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z, this.pitch, this.yaw));
        return this.reference.get();
    }

    /**
     * Deserializes the location
     * @param serialize the serialized location
     * @return the deserialized location
     */
    public static CLocation deserialize(Map<String, Object> serialize)
    {
        return new CLocation(serialize);
    }
}