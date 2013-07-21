package com.justagameclan.litsys.pvp;

public class RecordKeeping implements java.io.Serializable{
	
	/**
	 * This Class is for Top Single ranks. IE MOST kills.
	 */
	private static final long serialVersionUID = 1L;
	String recordType;
	int value;
	String player;
	
	public RecordKeeping (String recordType, String player, int value) {
		this.recordType = recordType;
		this.value = value;
		this.player = player;
	}
	
	public void setRecordType(String recordType) { 
		this.recordType = recordType;
	}
	
	public String getRecordType() {
		return this.recordType;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public String getPlayer() {
		return this.player;
	}
}
