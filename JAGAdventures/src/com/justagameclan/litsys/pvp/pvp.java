package com.justagameclan.litsys.pvp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class pvp extends JavaPlugin {
	private playerListener playerListener = new playerListener(this);
	private DungeonControl dungeonControl = new DungeonControl(this);
	public Location dungeonLocation;
	public ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
	public File[] structures;
	public File itemConfig;
	public int announceTrigger;
	public ArrayList<Team> TeamsList = new ArrayList<Team>();
	public FileConfiguration config = getConfig();
	
	//RecordKeeping
	public ArrayList<RecordKeeping> Records = new ArrayList<RecordKeeping>();
	public RecordKeeping RecordKills = new RecordKeeping("Kill", "null", 0);
	public RecordKeeping RecordStreak = new RecordKeeping("Streak", "null", 0);
	public File RecordsFile;
	public File RecordKillsFile;
	public File RecordStreakFile;
	public Map<String,PlayerEx> PVPPlayers = new HashMap<String, PlayerEx>();
	
	//Record Keeping //being phased out.
	//public Map<String, Integer> playerSessKills = new HashMap<String, Integer>();
	//public Map<String, Integer> playerSessStreak = new HashMap<String, Integer>();
	
	@SuppressWarnings("unchecked")
	public void onEnable(){
		getLogger().info("[J.A.G] PVP  has been enabled!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.playerListener, this);		
		this.dungeonLocation = null;
		//Better check...
		File StructureLocation = new File (this.getDataFolder() + File.separator + "structures");
		File StatisticLocation = new File (this.getDataFolder() + File.separator + "statistics");
		if (!StructureLocation.exists()) {
			StructureLocation.mkdirs();
		} else if (!StatisticLocation.exists()) {
			StatisticLocation.mkdirs();
		}
		//Load Structures..
		this.structures = StructureLocation.listFiles();
		//Load Statistics..
		RecordsFile = new File(StatisticLocation + File.separator + "Records.db");
		if (!RecordsFile.exists()) {
			try {
				RecordsFile.createNewFile();
				this.RecordKills = new RecordKeeping("Kill", "null", 0);
				this.RecordStreak = new RecordKeeping("Streak", "null", 0);
				this.Records.add(RecordKills);
				this.Records.add(RecordStreak);
				SLAPI.save(Records, this.RecordsFile.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.Records = (ArrayList<RecordKeeping>) SLAPI.load(RecordsFile.toString());
				for (RecordKeeping recordItem : Records) {
					if (recordItem.getRecordType() == "Kill") {
						this.RecordKills = recordItem;
					} else if (recordItem.getRecordType() == "Streak") {
						this.RecordStreak = recordItem;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.itemConfig = new File (this.getDataFolder(), "itemdrops.yml");
		if (!this.itemConfig.exists()) {
			this.saveResource("itemdrops.yml", true);
		} else {
			
		}
		//playerListener.loadStats();
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
			public void run() { 
					dungeonControl.spawnDungeon();
			}
		}, 36000L, 36000L);
	}
	public void onDisable(){
		getLogger().info("[J.A.G] PVP has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("schematic")) {
			if (sender.isOp()) {
				System.out.println("Creating Dungeon..");
				dungeonControl.spawnDungeon();
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Error: You must be an OP to do this!");
				return true;
			}
		}
		else if (cmd.getName().equalsIgnoreCase("undodungeon")) {
			if (sender.isOp()) {
				dungeonControl.deSpawnDungeon();
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Error: You must be an OP to do this!");
				return true;
			}
		}
		else if (cmd.getName().equalsIgnoreCase("weaponDrop")) {
			if (args.length == 0) {
				Drop weapon = new Drop();
				Player rPlayer = (Player) sender;
				rPlayer.getWorld().dropItem(rPlayer.getLocation(), weapon.getItem());
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Error: No arguments required!");
				return false;
			}
		}
		else if (cmd.getName().equalsIgnoreCase("money")) {
			if (args.length == 1)
			{
				if (this.PVPPlayers.containsKey(args[0]))
				{
					PlayerEx PlayerEx = this.PVPPlayers.get(args[0]);
					sender.sendMessage(ChatColor.WHITE + "Player " + PlayerEx.getPlayerEntity().getName() + " has " + PlayerEx.getMoney() + " GP");
					return true;
				}

			}
			else {
				sender.sendMessage(ChatColor.RED + "Error: please complete the full command!, make sure you have just the username after!");
				return false;
			}
		}
		else if (cmd.getName().equalsIgnoreCase("team")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Error: please complete the full command!");
				return false;
			}
			else {
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length == 2) {
						for (Team team : TeamsList) {
							if (team.getTeamName().equalsIgnoreCase(args[1])) {
								sender.sendMessage(ChatColor.RED + "Error Creating Team: Team with Same name already exists. Choose another.");
								return true;
							}
						}
						//Initialize list of team mates with the creator to start
						Team TeamAdd = new Team((Player)sender,args[1]);
						TeamsList.add(TeamAdd);
						sender.sendMessage(ChatColor.WHITE + "Team " + ChatColor.GREEN + args[1] + ChatColor.WHITE + " Successfully Created!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Error Creating Team: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("add")) {
					if (args.length == 3){
						if (Bukkit.getPlayer(args[1]) != null) {
							for (Team team : TeamsList) {
								if (args[2].equalsIgnoreCase(team.getTeamName())) {
									ArrayList<Player> TeamMembers = team.getTeamMembers();
									for (Player player: TeamMembers) {
										if (player.equals((Player)sender)) {
											for (Player playa: TeamMembers) {
												if (playa.getDisplayName().equalsIgnoreCase(Bukkit.getPlayer(args[1]).getDisplayName())) {
													// dont add here..
													sender.sendMessage(ChatColor.RED + "This player is on the team already!");
													return true;
												}
											}
										}
									}
									//add here.. (send request)
									team.addTeamInvite(Bukkit.getServer().getPlayer(args[1]));
									Bukkit.getServer().getPlayer(args[1]).sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.WHITE + " is requesting you to join their team (" + ChatColor.GREEN + team.getTeamName() + ChatColor.WHITE +") name type '/team accept' to join" );
									//OLD TO NOT SEND REQUEST -- team.addTeamMember();
									sender.sendMessage(ChatColor.WHITE +  "Player "+ ChatColor.GREEN + Bukkit.getPlayer(args[1]).getName() + ChatColor.WHITE + " was sent a request to join team " + ChatColor.GREEN + team.getTeamName());
									return true;
								}
							}
							sender.sendMessage(ChatColor.RED + "Error adding team mate: Make sure you have created the team or are an active member of it first!");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Error adding team mate: Make sure your team mate exists or is online!");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Error adding team mate: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					if (args.length == 3) {
						for (Team team: TeamsList) {
							if (args[2].equalsIgnoreCase(team.getTeamName())) {
								ArrayList<Player> TeamMembers = team.getTeamMembers();
								for (Player player: TeamMembers) {
									if (player.equals((Player)sender)) {
										for (Player playa: TeamMembers) {
											if (playa.equals(Bukkit.getServer().getPlayer(args[1]))) {
												team.removeTeamMember(Bukkit.getServer().getPlayer(args[1]));
												sender.sendMessage(ChatColor.WHITE + "Player " + ChatColor.GREEN + Bukkit.getPlayer(args[1]).getName() + ChatColor.WHITE + " was successfully removed from team "  + ChatColor.GREEN + team.getTeamName());
												Bukkit.getPlayer(args[1]).sendMessage(ChatColor.WHITE + "You were removed from team " + ChatColor.GREEN + team.getTeamName() + ChatColor.WHITE + " by " + sender.getName());
												return true;
											}
										}
										sender.sendMessage(ChatColor.RED + "Error removing team mate: Make sure the player you're trying to remove is part of your team and online!");	
										return true;
									}
								}
							}
						}
						sender.sendMessage(ChatColor.RED + "Error removing team mate: Make sure you have created the team or are an active member of it first!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Error removing team mate: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("delete")) {
					if (args.length == 2) {
						for (Team team: TeamsList) {
							ArrayList<Player> TeamMembers = team.getTeamMembers();
							if (args[1].equalsIgnoreCase(team.getTeamName())) {
								for (Player player: TeamMembers) {
									if (player.equals((Player)sender)) {
										TeamsList.remove(team);
										sender.sendMessage(ChatColor.WHITE + "Team " + ChatColor.GREEN + args[1] + ChatColor.WHITE + " Successfully removed!");
										return true;
									}
								}
							}
						}
						sender.sendMessage(ChatColor.RED + "Error deleting team: Make sure the team exists and that you are a member of the team!");
						return true;
						
					} else {
						sender.sendMessage(ChatColor.RED + "Error deleting team: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("listmember")) {
					if (args.length == 2) {
						for (Team team: TeamsList) {
							if (args[1].equalsIgnoreCase(team.getTeamName())) {
								sender.sendMessage(ChatColor.GOLD + "List of " + team.getTeamName() + "'s Members:");
								ArrayList<Player> TeamMembers = team.getTeamMembers();
								for (Player player : TeamMembers) {
									sender.sendMessage(ChatColor.GREEN + player.getDisplayName());
								}
							}
							return true;
						}
						
					} else {
						sender.sendMessage(ChatColor.RED + "Error Listing Members: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("list")) {
					if (args.length == 1) {
						sender.sendMessage(ChatColor.GOLD + "List of Active Teams:");
						for (Team team: TeamsList) {
							sender.sendMessage(ChatColor.GREEN + team.getTeamName() + ChatColor.WHITE + " (" + team.getTeamSize() + " Members) - "+ team.getTeamKills() +" Kills ("+team.getTeamKDR()+") KDR");
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Error listing Teams: Invalid Syntax");
						return false;
					}
				} else if (args[0].equalsIgnoreCase("accept")) {
					if (args.length == 1) {
						for (Team team: TeamsList) {
							ArrayList<Player>TeamInvites = team.getTeamInviteMembers();
							if (TeamInvites != null)
							{
								for (Player player: TeamInvites) {
									if (sender.equals(player)) {
										team.removeTeamInvite((Player)sender);
										team.addTeamMember((Player)sender);
										ArrayList<Player>TeamMembers = team.getTeamMembers();
										for (Player playa: TeamMembers) {
											if (!(playa.equals((Player)sender))) { 
												playa.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.WHITE + " has accepted to join your team " + ChatColor.GREEN + team.getTeamName());
												
											} else {
												playa.sendMessage("You have joined team " + ChatColor.GREEN +  team.getTeamName());
											}
										}
										return true;
									}
								}
							}
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Error Accepting request: Invalid Syntax");
					}
				}
			}
					
		}
		return false;
	}
}
