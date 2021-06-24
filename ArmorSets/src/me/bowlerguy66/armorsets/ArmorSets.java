package me.bowlerguy66.armorsets;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorSets extends JavaPlugin {

	private List<ArmorSet> armorSets;
	
	private ArmorSetsCommands armorSetsCommands;
	private ArmorSetsListener armorSetsListener;
	
	@Override
	public void onEnable() {
		
		this.armorSets = new ArrayList<ArmorSet>();	
		
		armorSetsCommands = new ArmorSetsCommands(this);
		armorSetsListener = new ArmorSetsListener(this);
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(armorSetsListener, this);
		
		getCommand("listsets").setExecutor(armorSetsCommands);
		getCommand("upgradepiece").setExecutor(armorSetsCommands);
				
		// The scheduler will only run after the server is completely loaded, meaning 
		// all armorsets will have been registered by this time
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				registerListeners();
				clock();
				armorClock();
				
				for(ArmorSet set : armorSets) {
					for(World w : Bukkit.getWorlds()) {
						for(LivingEntity le : w.getLivingEntities()) {
							set.loadEntity(le);
						}
					}
				}
				
			}
		});
		
	}
	
	@Override
	public void onDisable() {
		
		for(ArmorSet set : armorSets) {
			for(World w : Bukkit.getWorlds()) {
				for(LivingEntity le : w.getLivingEntities()) {
					set.unloadEntity(le);
				}
			}
		}

	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		for(ArmorSet set : armorSets) {
			if(set.getListeners() == null) continue;
			for(Listener l : set.getListeners()) {
				pm.registerEvents(l, this);
			}
		}
	}
	
	private void clock() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(ArmorSet set : armorSets) {
					set.tick();
				}
			}
		}, 0L, 20L);
	}

	private void armorClock() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(ArmorSet set : armorSets) {
					for(World w : Bukkit.getWorlds()) {
						for(LivingEntity le : w.getLivingEntities()) {
							set.tickArmor(le);
						}
					}
				}
			}
		}, 0L, 5 * 20L);
	}

	public List<ArmorSet> getArmorSets() {
		return armorSets;
	}
	
	public ArmorSet getArmorSet(String name) {
		for(ArmorSet testSet : armorSets) {
			if(!testSet.getName().equals(name)) continue; 
			return testSet;
		}		
		return null;
	}

	
	public ArmorSet getArmorSetNoCase(String name) {
		for(ArmorSet testSet : armorSets) {
			if(!testSet.getName().equalsIgnoreCase(name)) continue; 
			return testSet;
		}		
		return null;
	}

	public void registerArmorSet(ArmorSet set) {
		for(ArmorSet testSet : armorSets) {
			if(!testSet.getName().equals(set.getName())) continue; 
			if(!testSet.getDescription().equals(set.getDescription())) continue;
			Bukkit.getLogger().warning("Possible set override! Found two matching sets with the name of \"" + set.getName() + "\"");
		}
		armorSets.add(set);
	}

	public void tickArmor(LivingEntity le) {
		for(ArmorSet set : getArmorSets()) {
			set.tickArmor(le);
		}				
	}
	
}
