package com.justagameclan.litsys.pvp;
/*
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
*/
public class dungeonSpawner {
	/*
	public CuboidClipboard schematic;
	public Map<CuboidClipboard, LinkedList<BlockState>> blocks = new HashMap<CuboidClipboard, LinkedList<BlockState>>();
	public ArrayList<CuboidClipboard> dungeons = new ArrayList<CuboidClipboard>();
	public Map<CuboidClipboard, String> dungeonType = new HashMap<CuboidClipboard, String>();
	public Map<CuboidClipboard, String> dungeonRarity = new HashMap<CuboidClipboard, String>();
	public Map<Chest, Integer> chestFound = new HashMap<Chest, Integer>();
	public Map<Chest, CuboidClipboard> chestOwner = new HashMap<Chest, CuboidClipboard>();
	public static Map<String, Integer> RarityToValue = new HashMap<String, Integer>();
	static {
			RarityToValue.put("Common",50);
			RarityToValue.put("Uncommon",30);
			RarityToValue.put("Rare",10);
			RarityToValue.put("Legendary",8);
			RarityToValue.put("EPIC",2);
	}
	
	private static final Material[] dungeonItems = {Material.ARROW,Material.BOW,Material.IRON_INGOT,
		Material.GOLD_INGOT,Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,
		Material.IRON_BOOTS,Material.GOLD_SWORD,Material.OBSIDIAN,Material.NETHER_WARTS,
		Material.DIAMOND_SWORD,Material.DIAMOND,Material.DIAMOND_PICKAXE,Material.BLAZE_POWDER,
		Material.BLAZE_ROD,Material.GHAST_TEAR,Material.SUGAR_CANE,Material.DIAMOND_HELMET,
		Material.DIAMOND_CHESTPLATE,Material.DIAMOND_BOOTS,Material.POTION};
	private static final int[] dungeonItemsWorth = {50,50,50,50,30,30,30,30,30,30,30,10,10,10,10,10,10,10,8,8,8,2};
	private static final String[] RarityItems = {"Common", "Uncommon", "Rare", "Legendary", "EPIC"};
	private static final int[] RarityWeights = {50,30,10,8,2};
	private static pvp plugin;
	
	public dungeonSpawner(pvp instance) {
		plugin = instance;
	}
	//old for below: plugin.getDataFolder().getPath() + "/structures/" + File
	public CuboidClipboard loadDungeon () throws DataException, IOException {
		Random generator = new Random();
		File file = plugin.structures[generator.nextInt(plugin.structures.length)];
		System.out.println("Dungeon: "+file.getName()+" was chosen to be spawned!");
		
		//dungeonType .put(dungeon, dType);
		
		schematic = SchematicFormat.MCEDIT.load(file);
		
		if (file.getName().contains("udungeon")) {
			dungeonType.put(schematic, "underground");
		}
		else if (file.getName().contains("sdungeon")) {
			dungeonType.put(schematic, "sky");
		}
		else {
			dungeonType.put(schematic,  "aboveground");
		}
		
		return schematic;
	}
	
	public void putBlock(CuboidClipboard dungeon, BlockState block) {
		LinkedList<BlockState> list = getBlockList(dungeon);
				
		list.add(block);
	}
	
	private LinkedList<BlockState> getBlockList(CuboidClipboard dungeon) {
		LinkedList<BlockState> result = blocks.get(dungeon);
		
		if (result == null ) {
			result = new LinkedList<BlockState>();
			blocks.put(dungeon, result);
		}
				
		return result;
	}
	
	
	public String getDungeonType(CuboidClipboard dungeon) {
		String result = dungeonType.get(dungeon);
		return result;
	}
	public void removeBlock(CuboidClipboard dungeon) {
		LinkedList<BlockState> list = getBlockList(dungeon);
		
		while (list.size() > 0) {
			BlockState old = list.removeFirst();
			old.update(true);
		}
		
		list.remove(dungeon);
	}
	public void deSpawnDungeon() {
		CuboidClipboard dungeon = dungeons.get(0);
		if (chestFound.size() > 0) {
			System.out.println("num chests: " + chestFound.size());
			chestFound.clear();
		}
		if (chestOwner.size() > 0) {
			chestOwner.clear();
		}		
		if (dungeonType.size() > 0) {
			dungeonType.clear();	
		}
		plugin.DungeonLocation = null;
		removeBlock(dungeon);
		plugin.announceTrigger = 0;
		Bukkit.broadcastMessage("Dungeon has Despawned!");
		dungeons.remove(0);
	}
	
	public Block initDungeon(CuboidClipboard dungeon) throws DataException, IOException {

			Random generator = new Random();
			if (dungeonType.get(dungeon).equalsIgnoreCase("aboveground")) {
			int x = generator.nextInt(10000)-5000;
			int y = 63;
			int z = generator.nextInt(10000)-5000;
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x, y, z);
			///////////////////////
			while (!startPoint.isEmpty())
			{
				if (startPoint.getY() >= 256 || (startPoint.getY()+dungeon.getHeight()) >= 256)
				{
					initDungeon(dungeon);
					break;
				}
				else
				{
					startPoint = Bukkit.getWorld("world").getBlockAt(x,y+1,z);
					y++;
				}
			}
			for (int cy = startPoint.getY(); cy <= dungeon.getHeight(); cy++) {
				if (!Bukkit.getWorld("world").getBlockAt(x,y+cy,z).isEmpty() || y+cy < 256) {
					// this is fine
					continue;
				}
				else {
					initDungeon(dungeon);
					break;
				}
			}
			return startPoint;
			/////////////////////////
		}
		else if (dungeonType.get(dungeon).equalsIgnoreCase("sky")) {
			int x = generator.nextInt(10000)-5000;
			int y = 128;
			int z = generator.nextInt(10000)-5000;
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x,y,z);
			return startPoint;
		}
		else { //type is underground!
			int x = generator.nextInt(10000)-5000;
			int y = 30;
			int z = generator.nextInt(10000)-5000;
			int addY = dungeon.getHeight();
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x, y+addY, z);
			while (startPoint.isEmpty() || startPoint.isLiquid()) {
				startPoint = Bukkit.getWorld("world").getBlockAt(x,y+addY-1,z);
				y--;
				if (y <= 3) {
					initDungeon(dungeon);
					break;
				}
				else continue;
			}
			startPoint = Bukkit.getWorld("world").getBlockAt(x,y,z);
			return startPoint;
		}
		
	}
	
	public int weightedDraw(int[] weights,Integer modifier) {

		int totalWeight = 0;
		int i;
		for (i = 0; i < weights.length; i++) {
			totalWeight += weights[i];
			
		}
		int selection = new Random().nextInt(totalWeight);
		if (modifier >= 50) {
			double additional = (20/modifier);
			selection = (int) Math.ceil(selection + additional);
			while (selection > totalWeight*0.5) {
				selection = selection - new Random().nextInt(100);
			}
		}
		else if (modifier >= 30){
			double additional = (50/modifier);
			selection = (int) Math.ceil(selection + additional);
			while (selection > totalWeight*0.6) {
				selection = selection - new Random().nextInt(100);
			}
		}
		else if (modifier >= 10){
			double additional = (100/modifier);
			selection = (int) Math.ceil(selection + additional);
			while (selection > totalWeight*0.9) {
				selection = selection - new Random().nextInt(100);
			}
		}
		else if (modifier >= 8) {
			double additional = (200/modifier);
			selection = (int) Math.ceil(selection + additional);
			while (selection > totalWeight) {
				selection = selection - new Random().nextInt(100);
			}
		}
		else if (modifier >= 2) {
			double additional = (400/modifier);
			selection = (int) Math.ceil(selection + additional);
			while (selection > totalWeight) {
				selection = selection - new Random().nextInt(100);
			}
		}
		totalWeight = weights[0];
		
		for (i = 0; (i < weights.length - 1) && (selection >= totalWeight); i++) {
			totalWeight += weights[i + 1];
		}
		return i;
	}
	
	public void spawnDungeon() throws DataException, IOException {
		System.out.println("Loading Dungeon from file..");
		if (dungeons.size() > 0) {
			deSpawnDungeon();
		}
		CuboidClipboard dungeon = loadDungeon();
		dungeons.add(dungeon);
		System.out.println("dungeon added and loaded!");
		//////////////////////////////////////////////
		int i;
		i = weightedDraw(RarityWeights,0);
		dungeonRarity.put(dungeon,RarityItems[i]);
		//////////////////////////////////////////////
		try {
			Block startPoint;
			startPoint = initDungeon(dungeon);
			System.out.println("Dungeon Location has been found!");
			plugin.DungeonLocation = startPoint.getLocation();
			for (int lz = 0; lz <= dungeon.getLength(); lz++)
			{
				for (int lx = 0; lx <= dungeon.getWidth(); lx++)
				{
					for (int ly = 0; ly <= dungeon.getHeight(); ly++)
					{
						BaseBlock block;
						try {
							block = dungeon.getPoint(new BlockVector(lx,ly,lz));
							int px = startPoint.getX() + lx;
							int py = startPoint.getY() + ly;
							int pz = startPoint.getZ() + lz;
							putBlock(dungeon,Bukkit.getWorld("world").getBlockAt(px,py,pz).getState());
							Bukkit.getWorld("world").getBlockAt(px, py, pz).setTypeIdAndData(block.getType(), (byte) block.getData(), false);
							Block newBlock = Bukkit.getWorld("world").getBlockAt(px,py,pz);
							if (newBlock.getType().equals(Material.CHEST)){
								chestOwner.put((Chest)newBlock.getState(), dungeon);
								chestFound.put((Chest)newBlock.getState(),0);
								System.out.println("Found chest at "+newBlock.getX()+","+newBlock.getY()+","+newBlock.getZ());
								///////////////////////////////
								String dRarity = dungeonRarity.get(dungeon);
								System.out.println("Dungeons Rarity is \"" + dRarity + "\"" );
								int dungeonValue = RarityToValue.get(dRarity);
								for (int drawNum = 1; drawNum <= 10; drawNum++) {
									i = weightedDraw(dungeonItemsWorth,dungeonValue);
									Material drawnMat = dungeonItems[i];
									Chest spawnChest = (Chest) newBlock.getState();
									//System.out.println("Adding "+drawnMat.toString()+" to spawned chest!");
									spawnChest.getBlockInventory().addItem(new ItemStack(drawnMat));
								}

								
								//////////////////////////////
							}
							
							
						} catch (ArrayIndexOutOfBoundsException ex) {
							
						}
					}
				}
			}
			System.out.println("Dungeon Spawned at " + startPoint.getX() +","+ startPoint.getY() +"," + startPoint.getZ());
			String starter;
			if ( dungeonRarity.get(dungeon).toLowerCase().startsWith("a") || dungeonRarity.get(dungeon).toLowerCase().startsWith("e") || dungeonRarity.get(dungeon).toLowerCase().startsWith("i") || dungeonRarity.get(dungeon).toLowerCase().startsWith("o") || dungeonRarity.get(dungeon).toLowerCase().startsWith("u") ) {
				starter = "An ";
			}
			else {
				starter = "A ";
			}
			Bukkit.broadcastMessage(ChatColor.WHITE + starter + ChatColor.RED + dungeonRarity.get(dungeon) + ChatColor.WHITE + " Dungeon has Spawned!");
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}