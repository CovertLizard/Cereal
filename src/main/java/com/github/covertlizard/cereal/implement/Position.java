package com.github.covertlizard.cereal.implement;

import com.github.covertlizard.cereal.Cereal;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Map;
import java.util.UUID;

/**
 * Project: Cereal
 * Created: 11/13/2015
 * Time: 13:10
 * Package: com.github.covertlizard.cereal.implement
 * Description: Designed for serializing a location
 */
@SuppressWarnings("all")
public class Position extends Cereal
{
    private transient String world;
    private transient double x;
    private transient double y;
    private transient double z;
    private transient float pitch;
    private transient float yaw;

    public Position(Location location)
    {
        this.world = location.getWorld().getUID().toString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
        super.put("world", this.world);
        super.put("x", this.x);
        super.put("y", this.y);
        super.put("z", this.z);
        super.put("pitch", this.pitch);
        super.put("yaw", this.yaw);
    }

    /**
     * Converts position to location
     * @return the location
     */
    public Location get()
    {
        return new Location(Bukkit.getServer().getWorld(UUID.fromString(this.world)), this.x, this.y, this.z, this.pitch, this.yaw);
    }

    /**
     * Deserializes map to a location object
     * @param serialize the map
     * @return the deserialized location object
     */
    public static Location deserialize(Map<String, Object> serialize)
    {
        return new Location(Bukkit.getServer().getWorld(UUID.fromString((String)serialize.get("world"))), (double) serialize.get("x"), (double) serialize.get("y"), (double) serialize.get("z"), (float) serialize.get("pitch"),  (float) serialize.get("yaw"));
    }
}