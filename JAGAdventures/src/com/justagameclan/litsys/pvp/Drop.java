package com.justagameclan.litsys.pvp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class Drop {
	private String name;
	private double damage;
	private ArrayList<Enchantment> Enchantments;
	private ChatColor rarity;
	private ItemStack item;
	private ItemMeta itemMeta;
	private String[] manufacturers = { "Craftech", "Miner Co.", "Silver Zombie Systems", "Terrestrial Inc", "Mojave" };
	private String[] swordSynonyms = { "Blade", "Brand", "Broadsword", "Claymore", "Cutlass", "Dagger", "Glaive", "Rapier", "Sabre", "Scimitar" };
	private String[] axeSynonyms = { "Chopper", "Hatchet", "Tomahawk", "Axe" };
	private String[] wandSynonyms = { "Wand", "Rod" };
	private Material[] WeaponMaterials = new Material[] { Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.BLAZE_ROD };
	
	public Drop() {
		//this.name = WeaponNames[new Random().nextInt(WeaponNames.length)];
		String Company = manufacturers[new Random().nextInt(manufacturers.length)];
		System.out.println(name);
		DrawControl<ChatColor> RarityDraw = new DrawControl<ChatColor>();
		RarityDraw.add(128, ChatColor.WHITE);
		RarityDraw.add(64, ChatColor.GREEN);
		RarityDraw.add(32, ChatColor.BLUE);
		RarityDraw.add(16, ChatColor.LIGHT_PURPLE);
		RarityDraw.add(8, ChatColor.YELLOW);
		RarityDraw.add(4, ChatColor.GOLD);
		RarityDraw.add(1, ChatColor.AQUA);
		int modifier = 1;
		ChatColor DrawnRarity = RarityDraw.next(modifier);
		this.rarity = DrawnRarity;
		System.out.println(rarity);
		Material WeaponType = WeaponMaterials[new Random().nextInt(WeaponMaterials.length)];
		String weaponType = "Nothing";
		if (WeaponType.toString().toLowerCase().contains("sword")) {
			weaponType = swordSynonyms[new Random().nextInt(swordSynonyms.length)];
		} else if (WeaponType.toString().toLowerCase().contains("axe")) {
			weaponType = axeSynonyms[new Random().nextInt(axeSynonyms.length)];
		} else if (WeaponType.toString().toLowerCase().contains("rod")) {
			weaponType = wandSynonyms[new Random().nextInt(wandSynonyms.length)];
			// other wand magic ... see what i did there?
			
		}
		this.item = new ItemStack(WeaponType, 1);
		
		this.itemMeta = this.item.getItemMeta();
		if (this.itemMeta != null){
			List<String> lore;
			if (this.itemMeta.hasLore()){
				lore = this.itemMeta.getLore();
			}else{
				lore = new ArrayList<String>();
			}
			lore.add("You've got something special!");
			this.itemMeta.setLore(lore);
			String displayName;
			if (this.itemMeta.hasDisplayName()){
				displayName = this.itemMeta.getDisplayName();
			} else {
				displayName = (this.rarity + Company + " " + weaponType);
			}
			this.itemMeta.setDisplayName(displayName);
			this.item.setItemMeta(itemMeta);
		}
	}
	
	public void setManufacturers(String[] man)
	{
		this.manufacturers = man;
	}
	
	public void setSwordSynonyms(String[] syn)
	{
		this.swordSynonyms = syn;
	}
	
	public void setAxeSynonyms(String[] syn)
	{
		this.axeSynonyms = syn;
	}
	
	public void setWandSynonyms (String[] syn)
	{
		this.wandSynonyms = syn;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getDamage()
	{
		return this.damage;
	}
	
	public ArrayList<Enchantment> getEnchantments()
	{
		return this.Enchantments;
	}
	
	
	public ChatColor getRarity()
	{
		return this.rarity;
	}
	
	public ItemStack getItem()
	{
		return this.item;
	}
}
