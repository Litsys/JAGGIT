package com.justagameclan.litsys.pvp;

import org.bukkit.entity.Player;

public class PlayerEx {

	private int kills = 0;
	private int killStreak = 0;
	private int deaths = 0;
	private int assists = 0;
	private double damage = 0;
	private String Team = null;
	private Player p;

	public PlayerEx(Player playerEntity) {
		this.p = playerEntity;
	}
			
	public void addKill() {
		this.kills++;
	}
	
	public void clearKills() {
		this.kills = 0;
	}
	
	public int getKills() {
		return this.kills;
	}
	
	public void clearStreak() {
		this.killStreak = 0;
	}
	
	public void addStreak() {
		this.killStreak++;
	}
	
	public int getStreak() {
		return this.killStreak;
	}
	
	public void setTeam(String TeamName) {
		this.Team = TeamName;
	}
	
	public String getTeam() {
		return this.Team;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public void addDeaths() {
		this.deaths++;
	}

	public int getAssists() {
		return this.assists;
	}

	public void addAssists() {
		this.assists++;
	}

	public double getDamage() {
		return damage;
	}

	public void addDamage(double d) {
		this.damage = this.damage + d;
	}

	public Player getPlayerEntity() {
		return this.p;
	}

	public void setPlayerEntity(Player playerEntity) {
		this.p = playerEntity;
	}
	
}