// 
// Decompiled by Procyon v0.5.30
// 

package pl.best241.cccrafttrack.messages;

import org.bukkit.plugin.Plugin;

public class MessagesData
{
    private static MessagesConfig config;
    public static String commandsOnlyForPlayers;
    public static String guiName;
    public static String fakeItemMessage;
    
    public static void loadMessages(final Plugin plugin) {
        (MessagesData.config = new MessagesConfig(plugin, "messages.yml")).saveDefaultConfig();
        MessagesData.config.reloadCustomConfig();
        MessagesData.commandsOnlyForPlayers = MessagesData.config.getString("commandsOnlyForPlayers");
        MessagesData.guiName = MessagesData.config.getString("guiName");
        MessagesData.fakeItemMessage = MessagesData.config.getString("fakeItemMessage");
    }
}
