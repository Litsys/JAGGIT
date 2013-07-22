package com.justagameclan.litsys.pvp;

import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.io.ObjectOutputStream;
//import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
//import org.bukkit.Material;
//import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
//import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

//import com.sk89q.worldedit.CuboidClipboard;


public class playerListener implements Listener {
	private pvp plugin;
	//private dungeonSpawner dungeonSpawner;
	//private dungeonSpawner DungeonControl;
		
	public playerListener(pvp instance) {
		plugin = instance;
	}
	public File saveStatDir;

	
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location playerLoc = player.getLocation();
		if (plugin.dungeonLocation == null) {
			return;
		}
		if ((playerLoc.distanceSquared(plugin.dungeonLocation) < 5000) && (plugin.announceTrigger < 1)) {
			Bukkit.broadcastMessage("A Player is getting close to the dungeon");
			player.sendMessage("You're getting close to the dungeon!");
			plugin.announceTrigger = 1;
		}
	}
	
	@EventHandler
	public void onPlayerOpen(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Action action = event.getAction();
		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (event.getClickedBlock().getType() == Material.CHEST) {
				Chest Chest = (Chest)event.getClickedBlock().getState();
				ArrayList<Dungeon> dungeons = DungeonControl.dungeons;
				for(Dungeon dungeon : dungeons ){
					ArrayList<Chest> dungeonChests = dungeon.chests;
					for (Chest chest : dungeonChests) {
						if (Chest == chest) {
							PlayerEx PlayerEx = plugin.PVPPlayers.get(event.getPlayer().getName());
							double foundMoney = Math.floor(1000 / dungeon.dungeonRarityInt);
							PlayerEx.personalMoney.addMoney(foundMoney);
							p.sendMessage(ChatColor.WHITE + "You have found " + ChatColor.GOLD + foundMoney + ChatColor.WHITE + " GP!");
						}
					}
				}
			}
		}
	}
	/*
	@EventHandler
	public void onPlayerOpen(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Action action = event.getAction();
		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getType() == Material.CHEST) {
				Chest Chest = (Chest)event.getClickedBlock().getState();
				System.out.println("THIS CHEST IS A DUNGEON CHEST!!!!!!");
				if (dungeonSpawner.chestFound.containsKey(Chest)) {
					if (dungeonSpawner.chestFound.get(Chest) == 0) {
						dungeonSpawner.chestFound.remove(Chest);
						dungeonSpawner.chestFound.put(Chest, 1);
						CuboidClipboard dungeon = dungeonSpawner.chestOwner.get(Chest);
						String starter;
						if ( dungeonSpawner.dungeonRarity.get(dungeon).toLowerCase().startsWith("a") || dungeonSpawner.dungeonRarity.get(dungeon).toLowerCase().startsWith("e") || dungeonSpawner.dungeonRarity.get(dungeon).toLowerCase().startsWith("i") || dungeonSpawner.dungeonRarity.get(dungeon).toLowerCase().startsWith("o") || dungeonSpawner.dungeonRarity.get(dungeon).toLowerCase().startsWith("u") ) {
							starter = "An ";
						}
						else {
							starter = "A ";
						}
						Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.WHITE + " Has found the chest of " + starter + ChatColor.RED  + dungeonSpawner.dungeonRarity.get(dungeon) + ChatColor.WHITE + "Dungeon!");
						
					}
					
					
				}
			}
		}
		
	}*/
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		PlayerEx PlayerEx = null;
		if (!plugin.PVPPlayers.containsKey(event.getPlayer().getName())) {
			//PVPPlayer = new PVPPlayer(event.getPlayer());
			PlayerEx = new PlayerEx(event.getPlayer());
			plugin.PVPPlayers.put(event.getPlayer().getName(), PlayerEx);
		} else {
			PlayerEx = plugin.PVPPlayers.get(event.getPlayer().getName());
		}
		int activeKills = 0;
		String activeKillsPlayer = null;
		int activeStreak = 0;
		String activeStreakPlayer = null;
		for (PlayerEx playa : plugin.PVPPlayers.values()) {
			System.out.println(playa);
		    int tempKills = playa.getKills();
		    int tempStreak = playa.getStreak();
		    if (tempKills > activeKills) {
		    	activeKills = tempKills;
		    	activeKillsPlayer = playa.getPlayerEntity().getName();
		    }
		    if (tempStreak > activeStreak) {
		    	activeStreak = tempStreak;
		    	activeStreakPlayer = playa.getPlayerEntity().getName();
		    }
		}
		event.getPlayer().sendMessage(ChatColor.RED + activeStreakPlayer + ChatColor.WHITE + " has the highest Kill Streak with " + ChatColor.RED + activeStreak + ChatColor.WHITE + " Kills in a row!");
		event.getPlayer().sendMessage(ChatColor.RED + activeKillsPlayer + ChatColor.WHITE + " has the highest kill count with " + ChatColor.RED + activeKills + ChatColor.WHITE + " kills total!");
	
		/*
		if (plugin.PVPPlayers.contains(event.getPlayer())) {
			PVPPlayer = new PVPPlayer(event.getPlayer());
		} else
		{
			PVPPlayer = new PVPPlayer(event.getPlayer());//event.getPlayer());
		}
		//String newPlayers = event.getPlayer().getName();
		
		if (plugin.playerSessKills.containsKey(newPlayer)) {
			plugin.playerSessKills.remove(newPlayer);
			plugin.playerSessKills.put(newPlayer, 0);
		}
		else{
			plugin.playerSessKills.put(newPlayer, 0);
		}
		if (plugin.playerSessStreak.containsKey(newPlayer)) {
			plugin.playerSessStreak.remove(newPlayer);
			plugin.playerSessStreak.put(newPlayer, 0);
		}
		else{
			plugin.playerSessStreak.put(newPlayer,0);
		}
		// Get Kills this session
		int killsNum = 0;
		String killsPlayer = null;
		for (String kills : plugin.playerSessKills.keySet()) {
			if (plugin.playerSessKills.get(kills) > killsNum) {
				killsNum = plugin.playerSessKills.get(kills);
				killsPlayer = kills;
			}
		}
		// Get Streaks this session
		int streakNum = 0;
		String streakPlayer = null;
		for (String streak : plugin.playerSessStreak.keySet() ) {
			if (plugin.playerSessStreak.get(streak) > streakNum) {
				streakNum = plugin.playerSessStreak.get(streak);
				streakPlayer = streak;
			}
		}
		if (!(streakPlayer == null)) {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.RED + streakPlayer + ChatColor.WHITE + " has the highest kill streak this session with " + ChatColor.RED + streakNum + ChatColor.WHITE + " Kills in a row!");
		} else {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.WHITE + "No one has a running Kill streak.. GET OUT THERE AND KILL!");
		}
		if (!(killsPlayer == null)) {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.RED + killsPlayer + ChatColor.WHITE + " has the highest kills this session with " + ChatColor.RED + killsNum + ChatColor.WHITE + " Kills!");
		} else {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.WHITE + "No one has any recorded Kills.. GET OUT THERE AND KILL!");
		}
		if (!(plugin.RecordKills == null)) {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.RED + plugin.RecordKills.getPlayer() + ChatColor.WHITE + " has the highest kills recorded with " + ChatColor.RED + plugin.RecordKills.getValue() + ChatColor.WHITE + " Kills!");	
		}
		if (!(plugin.RecordStreak == null)) {
			Bukkit.getServer().getPlayer(newPlayer).sendMessage(ChatColor.RED + plugin.RecordStreak.getPlayer() + ChatColor.WHITE + " has the highest kill streak recorded with " + ChatColor.RED + plugin.RecordStreak.getValue() + ChatColor.WHITE + " Kills in a row!");
		}*/
		
	}
	
	//No friendly Fire!~
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event) {
		DamageCause damageCause = event.getCause();
		Entity Attacker = null;
		Player TeammateCheck = null;
		Player Player = null;
		boolean playerExist = false;
		boolean teamMateExist = false;
		
		switch(damageCause) {
		case MAGIC:
		case ENTITY_ATTACK:
			Attacker = event.getDamager();
			break;
		case PROJECTILE:
			if (event.getDamager().getType().equals(EntityType.ARROW)) {
				Projectile arrow = (Arrow)event.getDamager();
				Attacker = arrow.getShooter();
			} else if (event.getDamager().getType().equals(EntityType.SPLASH_POTION)) {
				Projectile potion = (ThrownPotion)event.getDamager();
				Attacker = potion.getShooter();
			} else {
				return;
			}
			break;
		default:
			break;
		}
		// Make sure the attacker is a player
		if (Attacker.getType() != EntityType.PLAYER) return;
		if (event.getEntityType() != EntityType.PLAYER) return;
		Player = (Player)event.getEntity();
		PlayerEx PlayerAttacker = plugin.PVPPlayers.get(Player.getName());
		TeammateCheck = (Player)Attacker;
		for (Team team: plugin.TeamsList) {
			ArrayList<Player> TeamMembers = team.getTeamMembers();
			for (Player member: TeamMembers) {
				if (member.equals(Player)) {
					playerExist = true;
				} else if (member.equals(TeammateCheck)) {
					teamMateExist = true;
				}
			}
		}
		if (teamMateExist && playerExist){
			event.setCancelled(true);
		} else {
			PlayerAttacker.addDamage(event.getDamage());
		}
	}
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) throws IOException{
		Player KillerCheck = event.getEntity().getKiller();
		String Deadee = event.getEntity().getName();
		PlayerEx PlayerDeadee = plugin.PVPPlayers.get(Deadee);
		if (KillerCheck instanceof Player) {
			// KD tracking
			String Killer = KillerCheck.getName();
			PlayerEx PlayerKiller = plugin.PVPPlayers.get(Killer);
			PlayerKiller.addKill();
			PlayerKiller.addStreak();
			//COMBOBREAKER?
			int BrokenStreak = PlayerDeadee.getStreak();
			if (BrokenStreak >= 3) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "C-C-C-Combo Breaker! " + ChatColor.WHITE + "- " + ChatColor.RED + Killer + ChatColor.WHITE + " Has broken " + Deadee +"'s Kill streak of " + ChatColor.DARK_PURPLE + BrokenStreak + " Kills");
			}
			PlayerDeadee.clearStreak();
			PlayerDeadee.addDeaths();
			// Team stuff
			for (Team team: plugin.TeamsList) {
				ArrayList<Player>MemberList = team.getTeamMembers();
				for (Player player: MemberList) {
					if (player.equals(Bukkit.getPlayer(Killer))) {
						team.addTeamKill();
					} else if (player.equals(Bukkit.getPlayer(Deadee))) {
						team.addTeamDeath();
					}
				}
			}
			if (PlayerKiller.getKills() > plugin.RecordKills.getValue()) {
				if (plugin.RecordKills.getPlayer().equalsIgnoreCase(Killer)) {
					Bukkit.broadcastMessage(ChatColor.GREEN + Killer + ChatColor.WHITE + " has extended his Kill Total Record to " + ChatColor.DARK_PURPLE + PlayerKiller.getKills());
					plugin.RecordKills.setValue(PlayerKiller.getKills());
				} else {
					Bukkit.broadcastMessage(ChatColor.GREEN + Killer + ChatColor.WHITE + " has broken the all time Kill Total Record! " + ChatColor.DARK_PURPLE + "(" + plugin.RecordKills.getValue() + " kills set by "+ plugin.RecordKills.getPlayer() +"!)");
					plugin.RecordKills.setPlayer(Killer);
					plugin.RecordKills.setValue(PlayerKiller.getKills());
				}
				/*try {
					File TempFile = plugin.RecordKillsFile;
					plugin.RecordKillsFile.delete();
					TempFile.createNewFile();
					plugin.RecordKillsFile = TempFile;
					SLAPI.save(plugin.RecordKills, plugin.RecordKillsFile.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				try {
					plugin.Records = new ArrayList<RecordKeeping>();
					plugin.Records.add(plugin.RecordKills);
					plugin.Records.add(plugin.RecordStreak);
					File tempFile = plugin.RecordsFile;
					plugin.RecordsFile.delete();
					tempFile.createNewFile();
					plugin.RecordsFile = tempFile;
					SLAPI.save(plugin.Records, plugin.RecordsFile.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (PlayerKiller.getStreak() > plugin.RecordStreak.getValue()) {
				if (plugin.RecordStreak.getPlayer().equalsIgnoreCase(Killer)) {
					Bukkit.broadcastMessage(ChatColor.GREEN + Killer + ChatColor.WHITE + " has extended his Kill Streak Record to " + ChatColor.DARK_PURPLE + PlayerKiller.getStreak());
					plugin.RecordStreak.setValue(PlayerKiller.getStreak());
				} else if (plugin.RecordStreak.getPlayer().equalsIgnoreCase("null")) {
					Bukkit.broadcastMessage(ChatColor.GREEN + Killer + ChatColor.WHITE + " has gotten First Blood!, Kill him!");
					plugin.RecordStreak.setPlayer(Killer);
					plugin.RecordStreak.setValue(PlayerKiller.getStreak());
				} else {
					Bukkit.broadcastMessage(ChatColor.GREEN + Killer + ChatColor.WHITE + " has broken the all time Kill Streak Record! " + ChatColor.DARK_PURPLE + "(" + plugin.RecordStreak.getValue() + " kills set by "+ plugin.RecordStreak.getPlayer() +"!)");
					plugin.RecordStreak.setValue(PlayerKiller.getStreak());
					plugin.RecordStreak.setPlayer(Killer);
				}
				/*
				try {
					File TempFile = plugin.RecordStreakFile;
					plugin.RecordStreakFile.delete();
					TempFile.createNewFile();
					plugin.RecordStreakFile = TempFile;
					SLAPI.save(plugin.RecordStreak, plugin.RecordStreakFile.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				try {
					plugin.Records = new ArrayList<RecordKeeping>();
					plugin.Records.add(plugin.RecordKills);
					plugin.Records.add(plugin.RecordStreak);
					File tempFile = plugin.RecordsFile;
					plugin.RecordsFile.delete();
					tempFile.createNewFile();
					plugin.RecordsFile = tempFile;
					SLAPI.save(plugin.Records, plugin.RecordsFile.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (PlayerDeadee.personalMoney.getMoney() < 5) {
				double amt = PlayerDeadee.personalMoney.getMoney();
				PlayerKiller.personalMoney.addMoney(amt);
				PlayerDeadee.personalMoney.setMoney(0);
				PlayerKiller.getPlayerEntity().sendMessage(ChatColor.WHITE + "You have Earned " + ChatColor.GREEN + amt + ChatColor.WHITE + " GP for that kill");
				PlayerDeadee.getPlayerEntity().sendMessage(ChatColor.WHITE + "You have Lost " + ChatColor.GREEN + amt + ChatColor.WHITE + " GP for that death");
			}
			else
			{
				PlayerDeadee.personalMoney.subMoney(5);
				PlayerKiller.personalMoney.addMoney(5);
				PlayerKiller.getPlayerEntity().sendMessage(ChatColor.WHITE + "You have Earned " + ChatColor.GREEN + "5" + ChatColor.WHITE + " GP for that kill");
				PlayerDeadee.getPlayerEntity().sendMessage(ChatColor.WHITE + "You have Lost " + ChatColor.GREEN + "5" + ChatColor.WHITE + " GP for that death");
			}
			if (PlayerKiller.getStreak() >= 24) {
				Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "God Like! " + ChatColor.DARK_PURPLE + "(" + PlayerKiller.getStreak() + " Kill Streak)");
			}
			else {
				switch (PlayerKiller.getStreak()) {
				case 3: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Triple Kill! " + ChatColor.DARK_PURPLE + "(3 Kill Streak)"); break;
				case 5: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Multi Kill! " + ChatColor.DARK_PURPLE + "(5 Kill Streak)"); break;
				case 6: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Rampage! " + ChatColor.DARK_PURPLE + "(6 Kill Streak)"); break;
				case 7: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Killing Spree! " + ChatColor.DARK_PURPLE + "(7 Kill Streak)"); break;
				case 9: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Dominating! " + ChatColor.DARK_PURPLE + "(9 Kill Streak)"); break;
				case 11: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "UnStoppable! " + ChatColor.DARK_PURPLE + "(11 Kill Streak)"); break;
				case 13: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Mega Kill! " + ChatColor.DARK_PURPLE + "(13 Kill Streak)"); break;
				case 15: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Ultra Kill! " + ChatColor.DARK_PURPLE + "(15 Kill Streak)"); break;
				case 16: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Eagle Eye! " + ChatColor.DARK_PURPLE + "(16 Kill Streak)"); break;
				case 17: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Ownage! " + ChatColor.DARK_PURPLE + "(17 Kill Streak)"); break;
				case 18: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Ludicrous Kill! " + ChatColor.DARK_PURPLE + "(18 Kill Streak)"); break;
				case 19: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Head Hunter! " + ChatColor.DARK_PURPLE + "(19 Kill Streak)"); break;
				case 20: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Wicked Sick! " + ChatColor.DARK_PURPLE + "(20 Kill Streak)"); break;
				case 21: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Monster Kill! " + ChatColor.DARK_PURPLE + "(21 Kill Streak)"); break;
				case 23: Bukkit.broadcastMessage(ChatColor.RED + Killer + ChatColor.WHITE + " - " + ChatColor.GREEN + "Holy Shit! " + ChatColor.DARK_PURPLE + "(23 Kill Streak)"); break;
				}
			}
		}
		else {
			Player DeadPlayer = Bukkit.getServer().getPlayer(Deadee);
			DeadPlayer.sendMessage(ChatColor.WHITE + "Careful.. you don't want to lose your " + ChatColor.RED + PlayerDeadee.getStreak() + ChatColor.WHITE + " Kill Streak, do you?");
		}
	}
	
}
