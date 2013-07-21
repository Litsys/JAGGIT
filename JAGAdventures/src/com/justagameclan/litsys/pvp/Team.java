package com.justagameclan.litsys.pvp;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Team {
	private ArrayList<Player> TeamMembers = new ArrayList<Player>();
	private ArrayList<Player> TeamInvites = new ArrayList<Player>();
	private int TeamKills;
	private int TeamDeaths;
	private double TeamKDR;
	private String TeamName;
	
	public Team (Player TeamMemberOne, String TeamName){
		this.TeamMembers.add(TeamMemberOne);
		this.TeamName = TeamName;
		this.TeamKills = 0;
		this.TeamDeaths = 0;
		this.TeamKDR = 0.00;
	}
	
	public void addTeamKill() {
		TeamKills++;
	}
	
	public void addTeamDeath() {
		TeamDeaths++;
	}
	
	public int getTeamKills() {
		return this.TeamKills;
	}
	
	public int getTeamDeaths() {
		return this.TeamDeaths;
	}
	
	public double getTeamKDR() {
		if (TeamDeaths > 0) {
			this.TeamKDR = TeamKills/TeamDeaths;	
		} else {
			TeamKDR = TeamKills;
		}
		return this.TeamKDR;
	}
	
	public void addTeamInvite(Player MemberName) {
		TeamInvites.add(MemberName);
	}
	
	public void removeTeamInvite(Player MemberName) {
		TeamInvites.remove(MemberName);
	}
	
	public ArrayList<Player> getTeamInviteMembers() {
		return this.TeamInvites;
	}
	
	public ArrayList<Player> getTeamMembers() {
		return this.TeamMembers;
	}
	
	public void addTeamMember(Player MemberName) {
		TeamMembers.add(MemberName);
	}
	
	public void removeTeamMember(Player MemberName) {
		TeamMembers.remove(MemberName);
	}
	
	public void setTeamName(String Name) {
		this.TeamName = Name;
	}
	
	public String getTeamName() {
		return this.TeamName;
	}
	
	public int getTeamSize() {
		return TeamMembers.size();
	}
	
}