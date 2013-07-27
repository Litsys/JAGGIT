package com.justagameclan.litsys.pvp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class Weapon {
	private String name;
	private double damage;
	private ArrayList<Enchantment> Enchantments;
	private ChatColor rarity;
	private ItemStack item;
	private ItemMeta itemMeta;
	private String[] manufacturers = { "Craftech", "Miner Co.", "Silver Zombie Systems", "Terrestrial Inc", "Mojave" };
	private String[] swordSynonyms = { "Blade", "Brand", "Broadsword", "Claymore", "Cutlass", "Dagger", "Glaive", "Rapier", "Sabre", "Scimitar" };
	private String[] axeSynonyms = { "Chopper", "Hatchet", "Tomahawk", "Axe" };
 	private String[] WeaponNames = new String[] { "Strurad","Darver","Vaetper","Taiash","Quaughim","Er'ran","Keshy",
											"Shyque","Tirak","Hihyl","Teand","Necex","Ardw","Enangwar","Imss",
											"Tanbqua","Morenthver","Latherusk","Sulss","Omnal","Bimer","Adann",
											"Vanus","Casayom","Quekinet","Sorat","Athshy","Tretorat","Im'hon",
											"Uskoan","Blocha","Sitwor","Honead'e","Drinshy","Utina","Ziyane",
											"Nonasevy","Achach","Scheethusk","Aughxtan","Nal'polu","Echessler",
											"Maif","Llayst","Tonvor","Oighti","Hatny","Isoud","Anuhin","Ghasam",
											"Onz","Tanildold","Aley","Kimnys","Elteik","Gar'cha","Samad","Seyph",
											"Orden","Radbdar","Ceryril","Lyetor","Estashaugh","Puk","Aleup","Lureja",
											"Ashir","Shiad","Orp","At'ash","Aughb","En'umi","Ormageing","Rilton",
											"Burves","Hat'est","Sahyde","Rakray","Ackyer","Endsam","Nyfun","Tiniskel",
											"Oriss","Woressbel","Reyldyer","Adper","Denp","Ormyer","Aughom","Rheult",
											"Dasit","Xilato","Lleytest","Endirorm","Lorytia","Xyciru","Davorden","Athkin",
											"Utase","Hon-athest","Nynal","Slayrche","Saiwen","At-essim","Llytoneld",
											"Erturard","Aldsam","Tinrisald","Deper","Warad","Emtai","Esseld","Denworight",
											"Ardwang","Istai","Clucha","Athiadel","Ran-kel","Trequaum","Undooph'u",
											"Ech'atho","Asrd","Ald'tas","Nuris","Chream","Nal'que'eyd","Saydar","En'gara",
											"Righ","Warom","Veret","Undeeng","Riv","Arche","Serper","Blewkim","Isnal",
											"Polad","Itod","Xakob","Umess","Banen","Ildamor","Vesenthbel","Darinn",
											"Skeline","Difum","Engeng","Rothris","Darest","Baosrod","Dilarim","Imsuler",
											"Essacka","Rakbwar","Boitheng","Awneld","Ybecko","Suvoro","Torow","Doreni",
											"Taigar","Ruigang","Yuridu","Atroth","Zosyso","Sant","Endck","Bibeut","Ad'bela",
											"Vorryne","Strogar","Bevut","Vorqueryn","Kozuy","Neveki","Imgold","Dabynu",
											"Rheussul","Eninekal","Breyph","Eneldack","Draohat","Vodiri","Awoph",
											"Cimosough","Vor'que","Nipys","Inghon","Uskdas","Suad","Lyeat","Yriso",
											"Kaleper","Ler'sul","Wivese","Ingtore","Lolas","Eldray","Emyet","Tur'dano",
											"Whyasale","Urnos","Nysshyser","Nysind","Lugkel","Viale","Essiring",
											"Omiar","Dramor","Nonymos","Iss'is","Rayeat","Ageeyf'st","Daryit","Ildem",
											"Kim-ryn","Ithery","Quyenath","Enementh","Ackale","Oldiss","Caruca","Darkimine",
											"Hinssul","Nied","Sam'iae","Itradina","Etcha","Theroughy","Garildingnysy","Aro",
											"Phoeghcha","Chaskim","Dankim","Ghalore","Engvor","Deldan" };
	private Material[] WeaponMaterials = new Material[] { Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.BLAZE_ROD };
	
	public Weapon() {
		this.name = WeaponNames[new Random().nextInt(WeaponNames.length)];
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
			weaponType = "Wand";
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
