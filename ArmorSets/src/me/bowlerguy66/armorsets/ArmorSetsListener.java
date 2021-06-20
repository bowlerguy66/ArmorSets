package me.bowlerguy66.armorsets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArmorSetsListener implements Listener {

	private ArmorSets plugin;
	
	public ArmorSetsListener(ArmorSets plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		for(ArmorSet set : plugin.getArmorSets()) {
			set.loadEntity(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		for(ArmorSet set : plugin.getArmorSets()) {
			set.unloadEntity(event.getPlayer());
		}
	}	
	
	@EventHandler
	public void onArmorChange(InventoryClickEvent event) {
		
		if(!(event.getWhoClicked() instanceof Player)) return;
		if(event.getCurrentItem() == null) return;

		// Tests if the armor slots were right/left clicked
		boolean validNormal = (event.isLeftClick() || event.isRightClick()) && event.getSlotType() == SlotType.ARMOR;
		// Tests the shift click
		boolean validShift  = event.isShiftClick() && isArmorMaterial(event.getCurrentItem().getType());
		boolean validDrop   = event.getSlotType() == SlotType.ARMOR && event.getAction().toString().contains("DROP");
		
		if(!(validNormal || validShift) && !validDrop) return;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				for(ArmorSet set : plugin.getArmorSets()) {
					set.tickArmor((Player) event.getWhoClicked());
				}				
			}
		}, 1L);
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		
		if(event.getItem() == null) return;
		if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(!isArmorMaterial(event.getItem().getType()));
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				for(ArmorSet set : plugin.getArmorSets()) {
					set.tickArmor(event.getPlayer());
				}				
			}
		}, 1L);
		
	}
	
	public boolean isArmorMaterial(Material mat) {
		String matName = mat.toString();
		return matName.contains("HELMET") || matName.contains("CHESTPLATE") || matName.contains("LEGGINGS") || matName.contains("BOOTS");
	}
	
}
