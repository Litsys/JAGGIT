package com.justagameclan.litsys.pvp;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.block.Chest;

import com.sk89q.worldedit.CuboidClipboard;

public class Dungeon {
	public Location dungeonLocation;
	public ArrayList<Chest> chests = new ArrayList<Chest>();
	public String dungeonRarity;
	public int dungeonRarityInt;
	public CuboidClipboard dungeonBlocks;
	public String dungeonType;
	
	public Dungeon(String dungeonRarity, int dungeonRarityInt, CuboidClipboard dungeonBlocks, String dungeonType)
	{
		this.dungeonRarity = dungeonRarity;
		this.dungeonRarityInt = dungeonRarityInt;
		this.dungeonBlocks = dungeonBlocks;
		this.dungeonType = dungeonType;
	}
	
	public void setLocation(Location dungeonLocation) {
		this.dungeonLocation = dungeonLocation;
	}
	
	public Location getDungeonLocation() {
			return this.dungeonLocation;	
	}
	
	public void addChests(Chest chests) {
		this.chests.add(chests);
	}
	
	public void removeChests(int chests) {
		this.chests.remove(chests);
	}
	
	public ArrayList<Chest> getChests() {
		return this.chests;
	}
	
	public String getDungeonRarity() {
		return this.dungeonRarity;
	}
	
	public int getDungeonRarityInt() {
		return this.dungeonRarityInt;
	}
	
	public CuboidClipboard getDungeonBlocks() { 
		return this.dungeonBlocks;
	}
	
	public String getDungeonType() {
		return this.dungeonType;
	}
	
}
