package com.project.breakthru.app.parts;

import com.project.breakthru.core.model.Side;

public class SilverGraveyardPart extends GraveyardPart 
{
	public SilverGraveyardPart()
	{
		this.side = Side.SILVER;
		PartRefresher.setSilverGraveyardPart(this);
	}
}
