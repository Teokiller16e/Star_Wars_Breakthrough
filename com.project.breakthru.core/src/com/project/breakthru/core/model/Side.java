package com.project.breakthru.core.model;

public enum Side
{
	GOLD,
	SILVER;
	
	public Side opposite()
	{
		return this == GOLD ? SILVER : GOLD; 
	}
}
