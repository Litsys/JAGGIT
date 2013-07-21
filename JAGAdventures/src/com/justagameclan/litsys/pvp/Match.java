package com.justagameclan.litsys.pvp;

import java.util.ArrayList;

public class Match {
	private ArrayList<Team> TeamVs;
	
	public Match(ArrayList<Team> TeamVs) {
		this.TeamVs = TeamVs;
	}
	
	public ArrayList<Team> getMatch() {
		return TeamVs;
	}
	
	public void addMatch(Team Team) {
		TeamVs.add(Team);
	}
	
	public void removeMatch(ArrayList<Team> Teams) {
		TeamVs.clear();
	}
}
