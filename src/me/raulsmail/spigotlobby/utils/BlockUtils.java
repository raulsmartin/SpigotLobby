package me.raulsmail.spigotlobby.utils;

import me.raulsmail.spigotlobby.SpigotLobby;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by raulsmail.
 */
public class BlockUtils {
    public static Map<Location, String> blocksToRestore = new HashMap<>();

    public static List<Block> getBlocksInRadius(Location location, Integer radius, Boolean hollow) {
        List<Block> blocks = new ArrayList<>();
        Integer blockX = location.getBlockX(), blockY = location.getBlockY(), blockZ = location.getBlockZ();
        for (int x = blockX - radius; x <= blockX + radius; x++) {
            for (int y = blockY - radius; y <= blockY + radius; y++) {
                for (int z = blockZ - radius; z <= blockZ + radius; z++) {
                    double distance = ((blockX - x) * (blockX - x) + (blockY - y) * (blockY - y) + (blockZ - z) * (blockZ - z));
                    if (distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {
                        Location loc = new Location(location.getWorld(), x, y, z);
                        if (!loc.getBlock().getType().equals(Material.BARRIER)) {
                            blocks.add(loc.getBlock());
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static void restoreBlockAt(Location location) {
        if (blocksToRestore.containsKey(location)) {
            Block block = location.getBlock();
            String blockData = blocksToRestore.get(location);
            for (Player player : block.getLocation().getWorld().getPlayers()) {
                player.sendBlockChange(location, Material.valueOf(blockData.split(":")[0]), Byte.valueOf(blockData.split(":")[1]));
            }
            blocksToRestore.remove(location);
        }
    }

    public static void setBlockToRestore(final Block block, final Material material, final Byte data, final Long duration) {
        SpigotLobby.getPlugin().getServer().getScheduler().runTaskAsynchronously(SpigotLobby.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (block.getType() != Material.AIR
                        && block.getType() != Material.SIGN_POST
                        && block.getType() != Material.CHEST
                        && block.getType() != Material.STONE_PLATE
                        && block.getType() != Material.WOOD_PLATE
                        && block.getType() != Material.IRON_PLATE
                        && block.getType() != Material.GOLD_PLATE
                        && block.getType() != Material.WALL_SIGN
                        && block.getType() != Material.WALL_BANNER
                        && block.getType() != Material.STANDING_BANNER
                        && block.getType() != Material.CROPS
                        && block.getType() != Material.LONG_GRASS
                        && block.getType() != Material.SAPLING
                        && block.getType() != Material.DEAD_BUSH
                        && block.getType() != Material.RED_ROSE
                        && block.getType() != Material.RED_MUSHROOM
                        && block.getType() != Material.BROWN_MUSHROOM
                        && block.getType() != Material.TORCH
                        && block.getType() != Material.LADDER
                        && block.getType() != Material.VINE
                        && block.getType() != Material.DOUBLE_PLANT
                        && block.getType() != Material.PORTAL
                        && block.getType() != Material.CACTUS
                        && block.getType() != Material.WATER
                        && block.getType() != Material.STATIONARY_WATER
                        && block.getType() != Material.LAVA
                        && block.getType() != Material.STATIONARY_LAVA
                        && block.getType() != Material.PORTAL
                        && block.getType() != Material.ENDER_PORTAL
                        && block.getType() != Material.SOIL
                        && block.getType() != Material.BARRIER
                        && block.getType() != Material.COMMAND
                        && block.getType() != Material.DROPPER
                        && block.getType() != Material.DISPENSER
                        && block.getType() != Material.BED
                        && block.getType() != Material.BED_BLOCK
                        && block.getType().getId() != 43
                        && block.getType().getId() != 44
                        && block.getType().isSolid()
                        && !block.getType().toString().contains("DOOR")) {
                    blocksToRestore.put(block.getLocation(), blocksToRestore.containsKey(block.getLocation()) ? blocksToRestore.get(block.getLocation()) : (block.getType().toString() + ":" + block.getData()));
                    for (Player player : block.getWorld().getPlayers()) {
                        player.sendBlockChange(block.getLocation(), material, data);
                    }
                    SpigotLobby.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(SpigotLobby.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            restoreBlockAt(block.getLocation());
                        }
                    }, duration);
                }
            }
        });
    }
}
