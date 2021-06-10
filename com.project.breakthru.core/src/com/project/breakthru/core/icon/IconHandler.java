package com.project.breakthru.core.icon;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;

import org.eclipse.swt.graphics.Image;

import org.eclipse.swt.widgets.Display;

import com.project.breakthru.core.model.Side;

public class IconHandler
{
	private static Image gold,silver,blank;
	private static Path path;
	//private static Image blank_img;
	static
	{
		try 
		{
			 
			 URL location = IconHandler.class.getProtectionDomain().getCodeSource().getLocation();
			 path = Paths.get(location.toURI()).resolve("../com.project.breakthru.core/images");
			 blank =  new Image(Display.getDefault(),path.resolve("blank.png").toString());
			 silver =  new Image(Display.getDefault(),path.resolve("silver.png").toString());
			 gold =  new Image(Display.getDefault(),path.resolve("gold.png").toString());
		}
		catch(URISyntaxException ex) 
		{
			ex.printStackTrace();
		}
	
	}
	
	
	public static Image getIcon(Side side,String name)
	{
		
		if(side == Side.SILVER)
		{
			return silver;
		}
		
		else if(name=="flagship")
		{
			
			
			return new Image(Display.getDefault(),path.resolve("flagship.png").toString());
		}
		
		else
		{
			return gold;
		}
			
	}

	public static Image getBlankIcon() 
	{	
					//img = new Image(Display.getDefault(),path.resolve("blank.png").toString());
				return blank;
	}
}
