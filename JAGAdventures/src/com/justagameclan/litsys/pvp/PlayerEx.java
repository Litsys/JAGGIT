package com.justagameclan.litsys.pvp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerEx {
	private String Team = null;
	private Player p;
	private ScoreboardManager ScoreboardManager = Bukkit.getScoreboardManager();
	private Scoreboard ViewableBoard = ScoreboardManager.getNewScoreboard();
	private Scoreboard PrivateBoard = ScoreboardManager.getNewScoreboard();
	private ArrayList<Player> Assistalbes = new ArrayList<Player>();
	private Objective obj = ViewableBoard.registerNewObjective("Statisics", "dummy");
	private Objective privObj = PrivateBoard.registerNewObjective("Statistics", "dummy");
	private Score DamageRec = privObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Damage Recived:"));
	private Score Money = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Money:"));
	private Score Kills = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Kills:"));
	private Score KillStreak = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Kill Streak:"));
	private Score Deaths = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Deaths:"));
	private Score DeathStreak = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Death Streak:"));
	private Score Assists = privObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Assists:"));
	private Score Damage = privObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Damage Dealt:")); 

	
	public PlayerEx(Player playerEntity) {
		this.p = playerEntity;
		this.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.obj.setDisplayName("Your Statistics:");
		this.privObj.setDisplaySlot(null);
		this.Kills.setScore(0);
		this.KillStreak.setScore(0);
		this.Deaths.setScore(0);
		this.DeathStreak.setScore(0);
		this.DamageRec.setScore(0);
		this.Assists.setScore(0);
		this.Damage.setScore(0);
	}	
	
	public void addAssistee(Player p) {
		this.Assistalbes.add(p);
	}
	
	public void removeAssistee(Player p) {
		this.Assistalbes.remove(p);
	}
	
	public void clearAssistees() {
		this.Assistalbes.clear();
	}
	
	public void addDamageRec(int d) {
		this.DamageRec.setScore(DamageRec.getScore() + d);
	}
	
	public int getDamageRec() {
		return this.DamageRec.getScore();
	}
	
	public ArrayList<Player> getAssistee() {
		return this.Assistalbes;
	}
	public void addKill() {
		this.Kills.setScore(this.Kills.getScore() + 1);
	}
	
	public void clearKills() {
		this.Kills.setScore(0);
	}
	
	public int getKills() {
		return this.Kills.getScore();
	}
	
	public void clearStreak() {
		this.KillStreak.setScore(0);
	}
	
	public void addStreak() {
		this.KillStreak.setScore(this.KillStreak.getScore() + 1);
	}
	
	public int getStreak() {
		return this.KillStreak.getScore();
	}
	
	public void setTeam(String TeamName) {
		this.Team = TeamName;
	}
	
	public String getTeam() {
		return this.Team;
	}

	public int getDeaths() {
		return this.Deaths.getScore();
	}

	public void addDeath() {
		this.Deaths.setScore(this.Deaths.getScore() + 1);
	}

	public int getAssists() {
		return this.Assists.getScore();
	}

	public void addAssist() {
		this.Assists.setScore(this.Assists.getScore() + 1);
	}

	public int getMoney() { 
		return this.Money.getScore();
	}
	
	public void addMoney(int m) {
		this.Money.setScore(this.Money.getScore() + m);
		
		for (int i = 0; i <= 3; i++) {
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, (float) 2, (float) 4);
		}
	}
	
	public void clearMoney() { 
		this.Money.setScore(0);
	}
	
	public int getDamage() {
		return this.Damage.getScore();
	}

	public void addDamage(int d) {
		this.Damage.setScore(this.Damage.getScore() + d);
	}

	public Player getPlayerEntity() {
		return this.p;
	}

	public void setPlayerEntity(Player playerEntity) {
		this.p = playerEntity;
	}
	
}