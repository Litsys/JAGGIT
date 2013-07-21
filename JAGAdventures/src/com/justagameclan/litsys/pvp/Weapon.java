package com.justagameclan.litsys.pvp;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

public class Weapon {
	private String name;
	private double damage;
	private ArrayList<Enchantment> Enchantments;
	private Color rarity;
	private ItemStack item;
	private ItemMeta itemMeta;
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
	private ItemStack[] WeaponItems = new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.DIAMOND_AXE, 1) };
	
	public Weapon() {
		this.name = WeaponNames[new Random().nextInt(WeaponNames.length)];
		DrawControl<Color> RarityDraw = new DrawControl<Color>();
		RarityDraw.add(128, Color.WHITE);
		RarityDraw.add(64, Color.GREEN);
		RarityDraw.add(32, Color.BLUE);
		RarityDraw.add(16, Color.PURPLE);
		RarityDraw.add(8, Color.YELLOW);
		RarityDraw.add(4, Color.ORANGE);
		RarityDraw.add(1, Color.AQUA);
		int modifier = 1;
		Color DrawnRarity = RarityDraw.next(modifier);
		this.rarity = DrawnRarity;
		this.item = WeaponItems[new Random().nextInt(WeaponItems.length)];
		this.itemMeta.setDisplayName(rarity + name);
		this.item.setItemMeta(itemMeta);
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
	
	
	public Color getRarity()
	{
		return this.rarity;
	}
	
	public ItemStack getItem()
	{
		return this.item;
	}
}
