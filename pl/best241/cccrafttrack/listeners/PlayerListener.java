// 
// Decompiled by Procyon v0.5.30
// 

package pl.best241.cccrafttrack.listeners;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Entity;
import pl.best241.cccrafttrack.managers.CraftTrackManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener
{
    @EventHandler
    public static void entityDamageByEntityListener(final EntityDamageByEntityEvent event) {
        final Entity victimEntity = event.getEntity();
        final Entity damagerEntity = event.getDamager();
        if (damagerEntity instanceof Player) {
            final Player player = (Player)damagerEntity;
            System.out.println("Player is damaging!");
            if (CraftTrackManager.isCraftTrackSword(player.getItemInHand())) {
                System.out.println("Player has crafttrack");
                final ItemStack craftTrack = player.getItemInHand();
                player.setItemInHand(CraftTrackManager.updateCraftTrackSword(craftTrack, 0, 1, (int)Math.ceil(event.getFinalDamage()), 0));
            }
        }
    }
    
    @EventHandler
    public static void onDeathPlayer(final PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final EntityDamageEvent entityDamageEvent = player.getLastDamageCause();
        if (entityDamageEvent instanceof EntityDamageByEntityEvent) {
            final EntityDamageByEntityEvent damageByEntity = (EntityDamageByEntityEvent)entityDamageEvent;
            if (damageByEntity.getDamager() instanceof Player || damageByEntity.getDamager() instanceof Arrow) {
                Player damager = null;
                if (damageByEntity.getDamager() instanceof Player) {
                    damager = (Player)damageByEntity.getDamager();
                }
                else if (damageByEntity.getDamager() instanceof Arrow) {
                    final Arrow arrow = (Arrow)damageByEntity.getDamager();
                    if (arrow.getShooter() instanceof Player) {
                        damager = (Player)arrow.getShooter();
                    }
                }
                if (damager != null && CraftTrackManager.getCraftTrackSword().isSimilar(player.getItemInHand())) {
                    final ItemStack craftTrack = player.getItemInHand();
                    player.setItemInHand(CraftTrackManager.updateCraftTrackSword(craftTrack, 1, 0, 0, 0));
                }
            }
        }
    }
    
    @EventHandler
    public static void onEntityDeath(final EntityDeathEvent event) {
        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            final EntityDamageByEntityEvent damageByEntity = (EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
            if (damageByEntity.getDamager() instanceof Player || damageByEntity.getDamager() instanceof Arrow) {
                Player damager = null;
                if (damageByEntity.getDamager() instanceof Player) {
                    damager = (Player)damageByEntity.getDamager();
                }
                else if (damageByEntity.getDamager() instanceof Arrow) {
                    final Arrow arrow = (Arrow)damageByEntity.getDamager();
                    if (arrow.getShooter() instanceof Player) {
                        damager = (Player)arrow.getShooter();
                    }
                }
                if (damager != null && event.getEntity().getType() != EntityType.PLAYER) {
                    if (CraftTrackManager.isCraftTrackSword(damager.getItemInHand())) {
                        final ItemStack craftTrack = damager.getItemInHand();
                        damager.setItemInHand(CraftTrackManager.updateCraftTrackSword(craftTrack, 0, 0, 0, 1));
                    }
                }
                else if (damager != null && event.getEntity().getType() == EntityType.PLAYER && CraftTrackManager.isCraftTrackSword(damager.getItemInHand())) {
                    final ItemStack craftTrack = damager.getItemInHand();
                    damager.setItemInHand(CraftTrackManager.updateCraftTrackSword(craftTrack, 1, 0, 0, 0));
                }
            }
        }
    }
    
    @EventHandler
    public void entityDamageEntity(final EntityDamageByEntityEvent event) {
        final Entity entity = event.getDamager();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (CraftTrackManager.isCraftTrackSword(player.getItemInHand())) {
                final ItemStack item = player.getItemInHand();
                item.setDurability((short)1);
            }
        }
    }
    
    @EventHandler
    public static void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        if (CraftTrackManager.isCraftTrackSword(player.getItemInHand()) || CraftTrackManager.isCraftTrackPickAxe(player.getItemInHand())) {
            final ItemStack item = player.getItemInHand();
            item.setDurability((short)1);
            if (!event.isCancelled() && CraftTrackManager.isCraftTrackPickAxe(player.getItemInHand())) {
                if (event.getBlock().getType() == Material.OBSIDIAN) {
                    CraftTrackManager.updateCraftTrackPickaxe(player.getItemInHand(), 1, 0, 0, 0, 0);
                }
                if (event.getBlock().getType() == Material.STONE) {
                    CraftTrackManager.updateCraftTrackPickaxe(player.getItemInHand(), 0, 1, 0, 0, 0);
                }
                CraftTrackManager.updateCraftTrackPickaxe(player.getItemInHand(), 0, 0, 0, 0, 1);
            }
        }
    }
}
