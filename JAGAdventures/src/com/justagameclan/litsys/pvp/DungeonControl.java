package com.justagameclan.litsys.pvp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
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

public class DungeonControl extends ChunkGenerator{
	public Map<CuboidClipboard, LinkedList<BlockState>> blocks = new HashMap<CuboidClipboard, LinkedList<BlockState>>();
	private static pvp plugin;
	public static ArrayList<Dungeon> dungeons;
	public Dungeon dungeon;
	//private CraftChest[] chests;
	
	public DungeonControl(pvp instance) {
		plugin = instance;
	}
		
	public void spawnDungeon() {
		if (dungeon != null)
		{
			deSpawnDungeon();
			DungeonControl.dungeons.remove(0);
		}
		try {
			this.dungeon = createDungeon();
			DungeonControl.dungeons.add(dungeon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dungeonLocation();
		//Materialise dungeon
		dungeonMaterialize();
		Bukkit.broadcastMessage(ChatColor.BLUE + "A " + ChatColor.GOLD + dungeon.getDungeonRarity() + ChatColor.BLUE + " Dungeon has Spawned!");
		System.out.println("@ "+dungeon.getDungeonLocation().getBlockX()+","+dungeon.getDungeonLocation().getBlockY()+","+dungeon.getDungeonLocation().getBlockZ());
		plugin.dungeonLocation = dungeon.getDungeonLocation();
	}
	
	public void removeBlock(CuboidClipboard dungeonBlock) {
		LinkedList<BlockState> list = getBlockList(dungeon.getDungeonBlocks());
		
		while (list.size() > 0) {
			BlockState old = list.removeFirst();
			old.update(true);
		}
		
		list.remove(dungeonBlock);
	}
	
	public void deSpawnDungeon() {
		CuboidClipboard dungeonBlocks = dungeon.getDungeonBlocks();
		//empty chests!
		// add later!
		//remove chests!
		if (!dungeon.getChests().isEmpty()) {
			while (!dungeon.getChests().isEmpty()){
				dungeon.removeChests(0);
			}
			
		}
		removeBlock(dungeonBlocks);
		plugin.announceTrigger = 0;
		Bukkit.broadcastMessage("Dungeon has Despawned!");
		plugin.dungeonLocation = null;
	}
	
	public Dungeon createDungeon() throws IOException, DataException {
		Dungeon dungeon;
		CuboidClipboard dungeonBlocks;
		int dungeonRarityInt = 0;
		//Initialise Rarity
		DrawControl<String> DungeonDraw = new DrawControl<String>();
		DungeonDraw.add(50,"Common");
		DungeonDraw.add(30,"Uncommon");
		DungeonDraw.add(10,"Rare");
		DungeonDraw.add(8,"Legendary");
		DungeonDraw.add(2,"EPIC");
		String dungeonRarity = (String) DungeonDraw.next(0);
		// Set the Int value
		if (dungeonRarity == "Common")
		{
			dungeonRarityInt = 50;
		} else if (dungeonRarity == "Uncommon") {
			dungeonRarityInt = 30;
		} else if (dungeonRarity == "Rare") {
			dungeonRarityInt = 10;
		} else if (dungeonRarity == "Legendary") {
			dungeonRarityInt =  8;
		} else if (dungeonRarity == "EPIC") {
			dungeonRarityInt = 2;
		}
		
		//Get File (Blocks)
		Random generator = new Random();
		File file = plugin.structures[generator.nextInt(plugin.structures.length)];
		dungeonBlocks = SchematicFormat.MCEDIT.load(file);
		//Set Dungeon Type
		String dungeonType;
		if (file.getName().contains("udungeon")) {
			dungeonType = "underground";
		}
		else if (file.getName().contains("sdungeon")) {
			dungeonType = "sky";
		}
		else {
			dungeonType = "aboveground";
		}
		//create logical dungeon
		dungeon = new Dungeon(dungeonRarity, dungeonRarityInt, dungeonBlocks, dungeonType);
		return dungeon;
	}
		
	public void dungeonLocation() {
		Random generator = new Random();
		// Above Ground Search
		if (dungeon.getDungeonType().equalsIgnoreCase("aboveground")) {
			int x = generator.nextInt(10000)-5000;
			int y = 63;
			int z = generator.nextInt(10000)-5000;
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x,y,z);
			while (!startPoint.isEmpty())
			{
				if (startPoint.getY() >= 256 || (startPoint.getY()+dungeon.getDungeonBlocks().getHeight()) >= 256) {
					dungeonLocation();
					break;
				} else {
					startPoint = Bukkit.getWorld("world").getBlockAt(x,y+1,z);
					y++;
				}
			}
			for (int cy = startPoint.getY(); cy <= dungeon.getDungeonBlocks().getHeight(); cy++) {
				if (!Bukkit.getWorld("world").getBlockAt(x,y+cy,z).isEmpty() || y+cy < 256) {
					// this is fine
					continue;
				}
				else {
					dungeonLocation();
					break;
				}
			}
			dungeon.setLocation(startPoint.getLocation());
		}
		// Sky Search
		else if (dungeon.getDungeonType().equalsIgnoreCase("sky")) {
			int x = generator.nextInt(10000)-5000;
			int y = 128;
			int z = generator.nextInt(10000)-5000;
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x,y,z);
			//future checks here
			dungeon.setLocation(startPoint.getLocation());
		}
		// Underground 
		else {
			int x = generator.nextInt(10000)-5000;
			int y = 30;
			int z = generator.nextInt(10000)-5000;
			int addY = dungeon.getDungeonBlocks().getHeight();
			Block startPoint = Bukkit.getWorld("world").getBlockAt(x, y+addY, z);
			while (startPoint.isEmpty() || startPoint.isLiquid()) {
				startPoint = Bukkit.getWorld("world").getBlockAt(x,y+addY-1,z);
				y--;
				if (y <= 3) {
					dungeonLocation();
					break;
				}
				else continue;
			}
			startPoint = Bukkit.getWorld("world").getBlockAt(x,y,z);
			dungeon.setLocation(startPoint.getLocation());
		}
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

	public void dungeonMaterialize() {
		for (int lz = 0; lz <= dungeon.getDungeonBlocks().getLength(); lz++)
		{
			for (int lx = 0; lx <= dungeon.getDungeonBlocks().getWidth(); lx++)
			{
				for (int ly = 0; ly <= dungeon.getDungeonBlocks().getHeight(); ly++)
				{
					BaseBlock block;
					try {
						block = dungeon.getDungeonBlocks().getPoint(new BlockVector(lx,ly,lz));
						int px = (int) (dungeon.getDungeonLocation().getX() + lx);
						int py = (int) (dungeon.getDungeonLocation().getY() + ly);
						int pz = (int) (dungeon.getDungeonLocation().getZ() + lz);
						putBlock(dungeon.getDungeonBlocks(),Bukkit.getWorld("world").getBlockAt(px,py,pz).getState());
						Bukkit.getWorld("world").getBlockAt(px, py, pz).setTypeIdAndData(block.getType(), (byte) block.getData(), false);
						Block newBlock = Bukkit.getWorld("world").getBlockAt(px,py,pz);
						
						if (newBlock.getType().equals(Material.CHEST)){
							Chest dungeonChest = (Chest) newBlock.getState();
							System.out.println("New chest created!");
							dungeon.addChests(dungeonChest);
							System.out.println("Found chest at "+newBlock.getX()+","+newBlock.getY()+","+newBlock.getZ());
							int dungeonValue = dungeon.getDungeonRarityInt();
							//set modifier
							double modifier;
							modifier = (1/(Math.log(dungeonValue)/Math.log(2)));
							for (int drawNum = 1; drawNum <= 10; drawNum++) {
								//CraftChest spawnChest = new DungeonChest(newBlock, chestIDs[chestIDs.length-1]);
								//Chest spawnChest = (Chest) newBlock.getState();
								dungeonChest.getBlockInventory().addItem(drawItem(modifier));
							}
						}						
					} catch (ArrayIndexOutOfBoundsException ex) {
						
					}
				}
			}
		}
		//dungeon.setChestIDs(chestIDs);
	}
	
	public ItemStack drawItem(double modifier) {
		DrawControl<Material> ItemDraw = new DrawControl<Material>();
		ItemDraw.add(50, Material.ARROW);
		ItemDraw.add(50, Material.BOW);
		ItemDraw.add(50, Material.IRON_INGOT);
		ItemDraw.add(50, Material.GOLD_INGOT);
		ItemDraw.add(30, Material.IRON_HELMET);
		ItemDraw.add(30, Material.IRON_CHESTPLATE);
		ItemDraw.add(30, Material.IRON_LEGGINGS);
		ItemDraw.add(30, Material.IRON_BOOTS);
		ItemDraw.add(30, Material.GOLD_SWORD);
		ItemDraw.add(30, Material.OBSIDIAN);
		ItemDraw.add(30, Material.NETHER_WARTS);
		ItemDraw.add(10, Material.DIAMOND_SWORD);
		ItemDraw.add(10, Material.DIAMOND_AXE);
		ItemDraw.add(10, Material.DIAMOND);
		ItemDraw.add(10, Material.DIAMOND_PICKAXE);
		ItemDraw.add(10, Material.BLAZE_POWDER);
		ItemDraw.add(10, Material.BLAZE_ROD);
		ItemDraw.add(10, Material.GHAST_TEAR);
		ItemDraw.add(10, Material.SUGAR_CANE);
		ItemDraw.add(8, Material.DIAMOND_HELMET);
		ItemDraw.add(8, Material.DIAMOND_CHESTPLATE);
		ItemDraw.add(8, Material.DIAMOND_BOOTS);
		ItemDraw.add(2, Material.POTION);
		Material DrawnItem = ItemDraw.next(modifier);
		ItemStack returnItemStack;
		if (DrawnItem.equals(Material.ARROW))
		{
			returnItemStack = new ItemStack(DrawnItem, 10);
		} else if (DrawnItem.equals(Material.DIAMOND_SWORD) || DrawnItem.equals(Material.DIAMOND_AXE)) { // This Weapon may be something Special!!
			boolean isSpecial = new Random().nextBoolean();
			if (isSpecial) { // You're special!
				System.out.println("A special weapon has been spawned in the world!"); // For development purposes
				Weapon weapon = new Weapon();
				returnItemStack = new ItemStack(weapon.getItem());
			} else { // proceed with regular item
				returnItemStack = new ItemStack(DrawnItem);
			}
		} else {
			returnItemStack = new ItemStack(DrawnItem);
		}
		return returnItemStack;
		
	}
	
	public ArrayList<Dungeon> getDungeons() {
		return DungeonControl.dungeons;
	}
}
