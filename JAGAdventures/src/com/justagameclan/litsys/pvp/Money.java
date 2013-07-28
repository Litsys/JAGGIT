package com.justagameclan.litsys.pvp;

public class Money {
	public int value = 0;
	public boolean isBank = false;
	
	public void setMoney(int value)
	{
		this.value = value;
	}
	
	public int getMoney()
	{
		return this.value;
	}
	
	public void addMoney(int amt)
	{
		this.value = this.value + amt;
	}
	
	public void subMoney(int amt)
	{
		this.value = this.value - amt;
	}
	
	public boolean getBank()
	{
		return this.isBank;
	}
	
	public void setBank(boolean flag)
	{
		this.isBank = flag;
	}
}
