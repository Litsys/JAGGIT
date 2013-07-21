package com.justagameclan.litsys.pvp;

public class Money {
	public double value = 0;
	public boolean isBank = false;
	
	public void setMoney(double value)
	{
		this.value = value;
	}
	
	public double getMoney()
	{
		return this.value;
	}
	
	public void addMoney(double amt)
	{
		this.value = this.value + amt;
	}
	
	public void subMoney(double amt)
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
