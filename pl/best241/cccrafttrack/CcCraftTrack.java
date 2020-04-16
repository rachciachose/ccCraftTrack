// 
// Decompiled by Procyon v0.5.30
// 

package pl.best241.cccrafttrack;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pl.best241.cccrafttrack.messages.MessagesData;
import pl.best241.cccrafttrack.listeners.PubSubRecieveMessageListener;
import pl.best241.cccrafttrack.gui.CraftTrackGui;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import pl.best241.cccrafttrack.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CcCraftTrack extends JavaPlugin
{
    private static CcCraftTrack plugin;
    
    public void onEnable() {
        CcCraftTrack.plugin = this;
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new CraftTrackGui(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PubSubRecieveMessageListener(), (Plugin)this);
        MessagesData.loadMessages((Plugin)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lable, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("crafttrack")) {
            if (sender instanceof Player) {
                final Player player = (Player)sender;
                CraftTrackGui.openGui(player);
            }
            else {
                sender.sendMessage(MessagesData.commandsOnlyForPlayers);
            }
        }
        return false;
    }
    
    public static CcCraftTrack getPlugin() {
        return CcCraftTrack.plugin;
    }
}
