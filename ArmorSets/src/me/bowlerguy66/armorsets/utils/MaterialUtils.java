package me.bowlerguy66.armorsets.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public class MaterialUtils {

	public static List<Material> leather() {
		return leather(null);
	}
	
	public static List<Material> leather(List<Material> removals) {
		List<Material> leather = Arrays.asList(Material.LEATHER_BOOTS,Material.LEATHER_LEGGINGS,Material.LEATHER_CHESTPLATE,Material.LEATHER_HELMET);
		if (removals != null) leather.removeAll(removals);
		return leather;
	}

	public static List<Material> chainmail() {
		return chainmail(null);
	}
	
	public static List<Material> chainmail(List<Material> removals) {
		List<Material> chainmail = Arrays.asList(Material.CHAINMAIL_BOOTS,Material.CHAINMAIL_LEGGINGS,Material.CHAINMAIL_CHESTPLATE,Material.CHAINMAIL_HELMET);
		if (removals != null) chainmail.removeAll(removals);
		return chainmail;
	}

	public static List<Material> gold() {
		return gold(null);
	}
	
	public static List<Material> gold(List<Material> removals) {
		List<Material> gold = Arrays.asList(Material.GOLDEN_BOOTS,Material.GOLDEN_LEGGINGS,Material.GOLDEN_CHESTPLATE,Material.GOLDEN_HELMET);
		if (removals != null) gold.removeAll(removals);
		return gold;
	}

	public static List<Material> iron() {
		return iron(null);
	}
	
	public static List<Material> iron(List<Material> removals) {
		List<Material> iron = Arrays.asList(Material.IRON_BOOTS,Material.IRON_LEGGINGS,Material.IRON_CHESTPLATE,Material.IRON_HELMET);
		if (removals != null) iron.removeAll(removals);
		return iron;
	}

	public static List<Material> diamond() {
		return diamond(null);
	}
	
	public static List<Material> diamond(List<Material> removals) {
		List<Material> diamond = Arrays.asList(Material.DIAMOND_BOOTS,Material.DIAMOND_LEGGINGS,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_HELMET);
		if (removals != null) diamond.removeAll(removals);
		return diamond;
	}

	public static List<Material> netherite() {
		return netherite(null);
	}
	
	public static List<Material> netherite(List<Material> removals) {
		List<Material> netherite = Arrays.asList(Material.NETHERITE_BOOTS,Material.NETHERITE_LEGGINGS,Material.NETHERITE_CHESTPLATE,Material.NETHERITE_HELMET);
		if (removals != null) netherite.removeAll(removals);
		return netherite;
	}

}
