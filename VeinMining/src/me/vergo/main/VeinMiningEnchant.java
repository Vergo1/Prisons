package me.vergo.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.vergo.main.events.VeinMiningEvent;


public class VeinMiningEnchant implements CommandExecutor
{

	Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(label.equalsIgnoreCase("veinminer"))
			{
				if(args.length >= 1)
				{
					if(Bukkit.getPlayerExact(args[0]) != null)
					{
						VeinMiningEvent.targ.add(player.getUniqueId());
						player.sendMessage(ChatColor.GREEN + "You can now right click your pick to add enchant");
						

					}else {
						player.sendMessage(ChatColor.DARK_RED + "That player does not exist!");
					}
				}else {
					player.sendMessage(ChatColor.DARK_RED + "Please type a player you would this cmd ran on!");
				}
			}
		}
		
		return true;
	}

	
	
}
