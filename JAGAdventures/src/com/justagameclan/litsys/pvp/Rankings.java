package com.justagameclan.litsys.pvp;

import java.util.ArrayList;

public class Rankings {

	private ArrayList<String> listMembers;
	
	public Rankings(int size) {
		this.listMembers = new ArrayList<String>(size);
	}
	
	public void setRank(int Position, String Rankee) {
		this.listMembers.set(Position-1, Rankee);
	}
	
	public String getRank (int index) {//Fix later
		return this.listMembers.get(index);
	}
	
	public ArrayList<String> returnList() {
		return listMembers;
	}
}
