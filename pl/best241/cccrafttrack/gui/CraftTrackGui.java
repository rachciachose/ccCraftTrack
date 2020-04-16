// 
// Decompiled by Procyon v0.5.30
// 

package pl.best241.cccrafttrack.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.InventoryView;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.best241.cccrafttrack.managers.CraftTrackManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import pl.best241.cccrafttrack.messages.MessagesData;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CraftTrackGui implements Listener
{
    private static int maxSize;
    
    public static void openGui(final Player player) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)player, CraftTrackGui.maxSize, MessagesData.guiName);
        for (int i = 0; i < CraftTrackGui.maxSize; ++i) {
            final ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)DyeColor.BLACK.getData());
            inv.setItem(i, glass);
        }
        inv.setItem(11, CraftTrackManager.makeFake(CraftTrackManager.getCraftTrackSword()));
        inv.setItem(15, CraftTrackManager.makeFake(CraftTrackManager.getCraftTrackPickaxe()));
        player.openInventory(inv);
    }
    
    @EventHandler
    public static void onInventoryClickEvent(final InventoryClickEvent event) {
        final InventoryView view = event.getView();
        if (event.getClickedInventory() != null && view.getTopInventory() == event.getClickedInventory() && view.getTopInventory().getTitle().equals(MessagesData.guiName)) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            view.setCursor((ItemStack)null);
        }
    }
    
    static {
        CraftTrackGui.maxSize = 27;
    }
}
