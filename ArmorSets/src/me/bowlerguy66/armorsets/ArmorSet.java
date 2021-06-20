package me.bowlerguy66.armorsets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class ArmorSet {

	protected String name;
	protected String title;
	protected List<String> description;
	
	protected List<Material> acceptablePieces;
	
	public ArmorSet(String name, List<String> description) {
		this.name = name;
		this.title = "";
		this.description = description;
		this.acceptablePieces = new ArrayList<Material>();
	}
	
	public abstract void tick();
	public abstract void tick(LivingEntity e);
	public abstract void tickArmor(LivingEntity e);
	public abstract void loadEntity(LivingEntity e);
	public abstract void unloadEntity(LivingEntity e);
	public abstract boolean checkPiece(ItemStack item);
	public abstract ItemStack makePiece(Material mat);
	public abstract ItemStack upgradePiece(ItemStack item);
	public abstract List<Listener> getListeners();
	
	public String getName() {
		return name;
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	public List<Material> getAcceptablePieces() {
		return acceptablePieces;
	}
	
	public static void clearModifiers(LivingEntity entity, Attribute attribute, String name) {
		if(entity == null || attribute == null || name == null) return;
		if(entity.getAttribute(attribute) == null) return;
		Collection<AttributeModifier> mods = entity.getAttribute(attribute).getModifiers();
		if(mods == null || mods.isEmpty()) return;
		for(AttributeModifier a : mods) {
			if(!a.getName().equals(name)) continue;
			entity.getAttribute(attribute).removeModifier(a);		
		}
	}
	
}
