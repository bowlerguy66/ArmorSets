package me.bowlerguy66.armorsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class ArmorSetsCommands implements CommandExecutor {

	private ArmorSets plugin;
	
	public ArmorSetsCommands(ArmorSets plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("listsets")) {
			
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
				return true;
			}
			
			if(args.length > 1) {
				sender.sendMessage(ChatColor.RED + "You can only have one argument!");
				return true;
			} 
			
			int page = 0;
			if(args.length == 1) {
				try {
					page = Integer.parseInt(args[0]);
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + "Argument must be a number!");
					return true;
				}
			}
			
			List<String> names = new ArrayList<String>();
			for(ArmorSet set : plugin.getArmorSets()) {
				names.add(set.getName());
			}
			Collections.sort(names);
			
			if(page * 10 > names.size()) {
				page = (int) ((double)names.size() / 10.0);
			}
			
			sender.sendMessage(ChatColor.AQUA + "======= Active ArmorSets =======");
			for(int i = 0; i < 10; i++) {
				int target = i + 10 * page;
				if(target >= names.size()) break;
				sender.sendMessage(ChatColor.GRAY + " - " + ChatColor.BLUE + "" + ChatColor.ITALIC + names.get(target));
			}
			sender.sendMessage(ChatColor.AQUA + "==============================");
			return true;
			
		} else if(cmd.getName().equalsIgnoreCase("upgradepiece")) {
			
			if(!sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
				return true;
			}
			
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
				return true;
			}	
			
			if(args.length != 1) {
				sender.sendMessage(ChatColor.RED + "You " + (args.length == 0 ? "must " : "can only ") + "have one argument!");
				return true;
			} 
			
			ArmorSet set = plugin.getArmorSet(args[0]);
			
			if(set == null) {
				sender.sendMessage(ChatColor.RED + "Couldn't find set \"" + args[0] + "\"");
				return true;
			}
			
			Player p = (Player) sender;
			ItemStack hand = p.getEquipment().getItemInMainHand();
			
			if(hand == null) {
				sender.sendMessage(ChatColor.RED + "You must be holding an item to do this!");
				return true;
			}
			
			ItemStack newItem = set.upgradePiece(hand);
			
			if(newItem == null) {
				sender.sendMessage(ChatColor.RED + "That item can't be upgraded by \"" + set.getName() + "\"");
				return true;
			}
			
			p.getEquipment().setItemInMainHand(newItem);
			p.sendMessage(ChatColor.GOLD + "Item upgraded by " + ChatColor.AQUA + set.getName());
			return true;
			
		}
		
		return false;
		
	}
	
}
