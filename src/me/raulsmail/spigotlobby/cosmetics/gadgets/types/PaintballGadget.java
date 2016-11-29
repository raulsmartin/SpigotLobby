package me.raulsmail.spigotlobby.cosmetics.gadgets.types;

import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadget;
import me.raulsmail.spigotlobby.cosmetics.gadgets.Gadgets;
import me.raulsmail.spigotlobby.utils.BlockUtils;
import me.raulsmail.spigotlobby.utils.LobbyPlayer;
import me.raulsmail.spigotlobby.utils.ParticleEffect;
import me.raulsmail.spigotlobby.utils.SoundUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import java.util.*;

/**
 * Created by raulsmail.
 */
public class PaintballGadget extends Gadget {

    private static Map<UUID, List<EnderPearl>> enderPearls = new HashMap<>();

    public PaintballGadget(LobbyPlayer player) {
        super(player, Gadgets.PAINTBALL);
        equip();
    }

    @Override
    public void onClear() {
        for (List<EnderPearl> pearls : enderPearls.values()) {
            for (EnderPearl enderPearl : pearls) {
                enderPearl.remove();
            }
        }
    }

    @Override
    public void onRightClick() {
        List<EnderPearl> enderPearlsList = new ArrayList<>();
        if (enderPearls.containsKey(getPlayer().getPlayer().getUniqueId())) {
            enderPearlsList.addAll(enderPearls.get(getPlayer().getPlayer().getUniqueId()));
        }
        enderPearlsList.add(getPlayer().getPlayer().launchProjectile(EnderPearl.class, getPlayer().getPlayer().getEyeLocation().getDirection().normalize().multiply(1.25)));
        enderPearls.put(getPlayer().getPlayer().getUniqueId(), enderPearlsList);
    }

    @Override
    public void onLeftClick() {
    }

    private Boolean isEnderPearlFromPaintballGadget(EnderPearl enderPearl) {
        for (List<EnderPearl> enderPearlList : enderPearls.values()) {
            if (enderPearlList.contains(enderPearl)) {
                return true;
            }
        }
        return false;
    }

    private void removeEnderPearlFromList(EnderPearl enderPearl) {
        for (UUID uuid : enderPearls.keySet()) {
            List<EnderPearl> enderPearlList = enderPearls.get(uuid);
            if (enderPearlList.contains(enderPearl)) {
                enderPearlList.remove(enderPearl);
                if (enderPearlList.isEmpty()) {
                    enderPearls.remove(uuid);
                } else {
                    enderPearls.put(uuid, enderPearlList);
                }
            }
        }
    }

    @EventHandler
    public void onVehicleDestroy(VehicleDestroyEvent event) {
        for (List<EnderPearl> enderPearlList : enderPearls.values()) {
            for (EnderPearl enderPearl : enderPearlList) {
                if (enderPearl.getLocation().distance(event.getVehicle().getLocation()) < 10) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof EnderPearl) {
            if (isEnderPearlFromPaintballGadget((EnderPearl) event.getRemover())) {
                event.setCancelled(true);
            }
        } else if (event.getRemover().getUniqueId().equals(getPlayer().getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getType().equals(EntityType.ENDER_PEARL)) {
            if (isEnderPearlFromPaintballGadget((EnderPearl) event.getEntity())) {
                removeEnderPearlFromList((EnderPearl) event.getEntity());
                byte data = (byte) new Random().nextInt(15);
                Location center = event.getEntity().getLocation().add(event.getEntity().getVelocity());
                //TODO: Change block's color.
                for (Block block : BlockUtils.getBlocksInRadius(center.getBlock().getLocation(), 2, false)) {
                    BlockUtils.setBlockToRestore(block, Material.STAINED_CLAY, data, 20L * 3L);
                }
                ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.OBSIDIAN, (byte) 0), 0, 0, 0, 5, 5, center.clone().add(0, 0.5, 0), 128);
                SoundUtils.playSound(center, SoundUtils.Sounds.STEP_STONE, 1.0F, 1.5F);
                event.getEntity().remove();
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getPlayer().getUniqueId().equals(getPlayer().getPlayer().getUniqueId())) {
            if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
                if (enderPearls.containsKey(event.getPlayer().getUniqueId())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity().getType().equals(EntityType.ENDERMITE)) {
            event.setCancelled(true);
        }
    }
}
