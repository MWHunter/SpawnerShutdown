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
        getConfig().getStringList("spawner-blacklist").forEach(type -> typesBlacklisted.add(EntityType.valueOf(type)));
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPreSpawnEvent(PreSpawnerSpawnEvent event) {
        event.setCancelled(typesBlacklisted.contains(event.getType()));
    }
}
