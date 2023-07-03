package com.massivecraft.factions.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.massivecraft.factions.event.FactionCreateEvent;
import com.massivecraft.factions.event.FactionJoinEvent;
import com.massivecraft.factions.event.FactionLeaveEvent;

public class CountryPlugin extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("country").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Code exécuté lors de la désactivation du plugin
    }

    @EventHandler
    public void onFactionCreate(FactionCreateEvent event) {
        String factionName = event.getFactionTag();
        getLogger().info("Le pays " + factionName + " a été créé !");
    }

    @EventHandler
    public void onFactionJoin(FactionJoinEvent event) {
        Player player = event.getfPlayer().getPlayer();
        String factionName = event.getFactionTag();
        player.sendMessage(ChatColor.GREEN + "Bienvenue dans le pays " + factionName + " !");
    }

    @EventHandler
    public void onFactionLeave(FactionLeaveEvent event) {
        Player player = event.getfPlayer().getPlayer();
        String factionName = event.getFactionTag();
        player.sendMessage(ChatColor.RED + "Vous avez quitté le pays " + factionName + ".");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("country.joinmessage")) {
            player.sendMessage(ChatColor.GOLD + "Bienvenue sur le serveur !");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("country")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String message = ChatColor.YELLOW + "Commande /country exécutée par " + player.getName();
                getLogger().info(message);
            }
            return true;
        }
        return false;
    }
}
