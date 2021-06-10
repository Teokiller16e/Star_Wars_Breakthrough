package com.project.breakthru.app.parts;

import com.project.breakthru.core.model.Side;

public class GoldGraveyardPart extends GraveyardPart 
{

	public GoldGraveyardPart()
	{
		this.side = Side.GOLD;
		PartRefresher.setGoldGraveyardPart(this);
	}
}
