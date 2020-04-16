// 
// Decompiled by Procyon v0.5.30
// 

package pl.best241.cccrafttrack.managers;

import pl.best241.cccrafttrack.messages.MessagesData;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.ChatColor;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CraftTrackManager
{
    private static final String swordKills;
    private static final String swordHits;
    private static final String swordHpTakenString;
    private static final String swordKilledMobs;
    private static final String pickaxeObsydian;
    private static final String pickaxeStone;
    private static final String pickaxeDiamend;
    private static final String pickaxeGold;
    private static final String pickaxeBlocksAll;
    
    public static ItemStack getCraftTrackSword() {
        final ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta meta = item.getItemMeta();
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "CraftTrack");
        lore.add(CraftTrackManager.swordKills + "0");
        lore.add(CraftTrackManager.swordHits + "0");
        lore.add(CraftTrackManager.swordHpTakenString + "0");
        lore.add(CraftTrackManager.swordKilledMobs + "0");
        meta.setLore((List)lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack makeFake(final ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        List<String> lore = (List<String>)meta.getLore();
        if (lore == null) {
            lore = new ArrayList<String>();
        }
        lore.add(" ");
        for (final String line : MessagesData.fakeItemMessage.split("\n")) {
            lore.add(line);
        }
        meta.setLore((List)lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack updateCraftTrackSword(final ItemStack item, final int killsUpdate, final int hitsUpdate, final int hpTaken, final int mobsKilled) {
        final ItemMeta meta = item.getItemMeta();
        final ArrayList<String> lore = (ArrayList<String>)meta.getLore();
        final int currentKills = Integer.parseInt(lore.get(1).replace(CraftTrackManager.swordKills, "")) + killsUpdate;
        final int currentHits = Integer.parseInt(lore.get(2).replace(CraftTrackManager.swordHits, "")) + hitsUpdate;
        final int currentTakenHP = Integer.parseInt(lore.get(3).replace(CraftTrackManager.swordHpTakenString, "")) + hpTaken;
        final int currentMobKills = Integer.parseInt(lore.get(4).replace(CraftTrackManager.swordKilledMobs, "")) + mobsKilled;
        lore.set(1, CraftTrackManager.swordKills + currentKills);
        lore.set(2, CraftTrackManager.swordHits + currentHits);
        lore.set(3, CraftTrackManager.swordHpTakenString + currentTakenHP);
        lore.set(4, CraftTrackManager.swordKilledMobs + currentMobKills);
        meta.setLore((List)lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static boolean isCraftTrackSword(final ItemStack item) {
        return item.getType() == Material.DIAMOND_SWORD && (item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().get(0).equals(ChatColor.RED + "CraftTrack"));
    }
    
    public static boolean isCraftTrackPickAxe(final ItemStack item) {
        return item.getType() == Material.DIAMOND_PICKAXE && (item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().get(0).equals(ChatColor.RED + "CraftTrack"));
    }
    
    public static ItemStack getCraftTrackPickaxe() {
        final ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        final ItemMeta meta = item.getItemMeta();
        final ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "CraftTrack");
        lore.add(CraftTrackManager.pickaxeObsydian + "0");
        lore.add(CraftTrackManager.pickaxeStone + "0");
        lore.add(CraftTrackManager.pickaxeDiamend + "0");
        lore.add(CraftTrackManager.pickaxeGold + "0");
        lore.add(CraftTrackManager.pickaxeBlocksAll + "0");
        meta.setLore((List)lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack updateCraftTrackPickaxe(final ItemStack item, final int obs, final int stone, final int diamend, final int gold, final int blocks) {
        if (!isCraftTrackPickAxe(item)) {
            return null;
        }
        final ItemMeta meta = item.getItemMeta();
        final ArrayList<String> lore = (ArrayList<String>)meta.getLore();
        final int obsidianBroken = Integer.parseInt(lore.get(1).replace(CraftTrackManager.pickaxeObsydian, "")) + obs;
        final int stoneBroken = Integer.parseInt(lore.get(2).replace(CraftTrackManager.pickaxeStone, "")) + stone;
        final int diamondBroken = Integer.parseInt(lore.get(3).replace(CraftTrackManager.pickaxeDiamend, "")) + diamend;
        final int goldBroken = Integer.parseInt(lore.get(4).replace(CraftTrackManager.pickaxeGold, "")) + gold;
        final int blocksBroken = Integer.parseInt(lore.get(5).replace(CraftTrackManager.pickaxeBlocksAll, "")) + blocks;
        lore.set(1, CraftTrackManager.pickaxeObsydian + obsidianBroken);
        lore.set(2, CraftTrackManager.pickaxeStone + stoneBroken);
        lore.set(3, CraftTrackManager.pickaxeDiamend + diamondBroken);
        lore.set(4, CraftTrackManager.pickaxeGold + goldBroken);
        lore.set(5, CraftTrackManager.pickaxeBlocksAll + blocksBroken);
        meta.setLore((List)lore);
        item.setItemMeta(meta);
        return item;
    }
    
    static {
        swordKills = ChatColor.DARK_GREEN + "Zabojstwa: " + ChatColor.GOLD;
        swordHits = ChatColor.DARK_GREEN + "Uderzen: " + ChatColor.GOLD;
        swordHpTakenString = ChatColor.DARK_GREEN + "Zadanego HP: " + ChatColor.GOLD;
        swordKilledMobs = ChatColor.DARK_GREEN + "Zabitych mobow: " + ChatColor.GOLD;
        pickaxeObsydian = ChatColor.DARK_GREEN + "Obsidianu: " + ChatColor.GOLD;
        pickaxeStone = ChatColor.DARK_GREEN + "Kamienia: " + ChatColor.GOLD;
        pickaxeDiamend = ChatColor.DARK_GREEN + "Diamentow: " + ChatColor.GOLD;
        pickaxeGold = ChatColor.DARK_GREEN + "Zlota: " + ChatColor.GOLD;
        pickaxeBlocksAll = ChatColor.DARK_GREEN + "Ogolnie: " + ChatColor.GOLD;
    }
}
