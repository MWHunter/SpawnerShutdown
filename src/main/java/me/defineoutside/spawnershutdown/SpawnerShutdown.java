package me.defineoutside.spawnershutdown;

import com.destroystokyo.paper.event.entity.PreSpawnerSpawnEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class SpawnerShutdown extends JavaPlugin {

    Set<EntityType> typesBlacklisted = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        for (String type : getConfig().getStringList("spawner-blacklist")) {
            typesBlacklisted.add(EntityType.valueOf(type));
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPreSpawnEvent(PreSpawnerSpawnEvent event) {
        if (typesBlacklisted.contains(event.getType())) {
            event.setCancelled(true);
        }
    }
}
