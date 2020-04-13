package me.vergo.main.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.vergo.main.Main;


public class VeinMiningEvent implements Listener
{

	public static ArrayList<UUID> targ = new ArrayList<UUID>();
	
	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			for(int i = 0; i<targ.size(); i++)
			{
				if(targ.get(i).equals(p.getUniqueId()))
				{
					if(p.getItemInHand() != null)
					{
						if(p.getItemInHand().hasItemMeta())
						{

							if(p.getItemInHand().getItemMeta().hasLore())
							{
								ItemMeta meta = p.getItemInHand().getItemMeta();
								List<String> lore = meta.getLore();
								lore.add(ChatColor.LIGHT_PURPLE + "Vein Miner");
								meta.setLore(lore);
								p.getItemInHand().setItemMeta(meta);
								p.setItemInHand(p.getItemInHand());
								targ.remove(i);
							}else {
								ItemMeta meta = p.getItemInHand().getItemMeta();
								ArrayList<String> lore = new ArrayList<String>();
								lore.add(ChatColor.LIGHT_PURPLE + "Vein Miner");
								meta.setLore(lore);
								p.getItemInHand().setItemMeta(meta);
								p.setItemInHand(p.getItemInHand());
								targ.remove(i);
							}
						}else {
							ItemMeta meta = p.getItemInHand().getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							lore.add(ChatColor.LIGHT_PURPLE + "Vein Miner");
							meta.setLore(lore);
							p.getItemInHand().setItemMeta(meta);
							p.setItemInHand(p.getItemInHand());
							targ.remove(i);
						}
					}
				}
			}
		}
	}
	
	
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		ItemStack item = e.getPlayer().getItemInHand();
		Block block = e.getBlock();
		Material prevBlock = null;
		int cooldowntime = plugin.getConfig().getInt("Cooldown");

		if(item.hasItemMeta())
		{
			if(item.getItemMeta().hasLore())
			{
				List<String> lore = item.getItemMeta().getLore();
				for(int i =0; i<lore.size(); i++)
				{
					if(lore.get(i).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Vein Miner"))
					{
						if(cooldown.containsKey(p.getUniqueId()))
						{
							long secondsleft = ((cooldown.get(p.getUniqueId())/1000) + cooldowntime) - (System.currentTimeMillis() / 1000);
							if(secondsleft <= 0)
							{
								prevBlock = block.getType();
								
		
								
								for(int a = 0; a<15; a++)
								{
									for (int b = 0; b<15; b++)
									{
										for(int c = 0; c<15; c++)
										{
											if(block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()+b).getType() != null || block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()+b).getType() == Material.AIR)
											{
												if(block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()+b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()+b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()+c,(int)block.getZ()+b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()+c,(int)block.getZ()+b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()-c,(int)block.getZ()-b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()-c,(int)block.getZ()-b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()-c,(int)block.getZ()+b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()-c,(int)block.getZ()+b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()-b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()-c,(int)block.getZ()-b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()+c,(int)block.getZ()+b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()-a,(int)block.getY()+c,(int)block.getZ()+b).breakNaturally();
												}
												if(block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()+c,(int)block.getZ()-b).getType() == prevBlock)
												{
													block.getWorld().getBlockAt((int)block.getX()+a,(int)block.getY()+c,(int)block.getZ()-b).breakNaturally();
												}
											}
										}
										
									}
									e.setCancelled(true);
								}

								
								cooldown.remove(p.getUniqueId());
							}else {

							}
						}else {
							cooldown.put(p.getUniqueId(), System.currentTimeMillis());
						}
		
					}
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
}
