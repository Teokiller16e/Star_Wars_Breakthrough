package com.project.breakthru.app.parts;

public class PartRefresher 
{
	private static breakthruBoardPart breakthruPart;
	private static GraveyardPart goldGraveyardPart,silverGraveyardPart;
	
	public static void refresh ()
	{
		if(breakthruPart == null || goldGraveyardPart == null || silverGraveyardPart ==null)
				return ;
		
		breakthruPart.setFocus();
		goldGraveyardPart.setFocus();
		silverGraveyardPart.setFocus();
	}
	
	
	public static void setBreakthruPart(breakthruBoardPart breakthruPart) 
	{
		PartRefresher.breakthruPart = breakthruPart;
	}


	public static void setGoldGraveyardPart(GraveyardPart goldGraveyardPart) {
		PartRefresher.goldGraveyardPart = goldGraveyardPart;
	}


	public static GraveyardPart getGoldGraveyardPart() {
		return goldGraveyardPart;
	}


	public static GraveyardPart getSilverGraveyardPart() {
		return silverGraveyardPart;
	}


	public static void setSilverGraveyardPart(GraveyardPart silverGraveyardPart) {
		PartRefresher.silverGraveyardPart = silverGraveyardPart;
	}
	
	
}
