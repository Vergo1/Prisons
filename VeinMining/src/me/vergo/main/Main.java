package me.vergo.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.vergo.main.events.VeinMiningEvent;

public class Main extends JavaPlugin
{

	
	public void onEnable()
	{
		loadConfig();
		getServer().getPluginManager().registerEvents(new VeinMiningEvent(), this);

		
		this.getCommand("VeinMiner").setExecutor(new VeinMiningEnchant());
	}
	
	public void onDisable()
	{

	}
	
	public void loadConfig()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
}
