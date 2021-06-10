package com.project.breakthru.app.room;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.project.breakthru.core.model.Army;
import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.piece.Flagship;
import com.project.breakthru.core.model.piece.Pawn;
import com.project.breakthru.player.AlphaBetaPlayer;
import com.project.breakthru.player.BreakthruPlayer;
import com.project.breakthru.player.IterativeDeepening;

public class BreakthruRoom
{
	public final Board board; 
	private static int row_column_temp = 7;//combined with the iteration to struct as fast as possible the position of the  pawns
	private int j;
	private final Map<Side,BreakthruPlayer> players;
	private static JPanel panel,layout,btnPanel;
	private static JFrame frame; 
	public static int sideChosen;
	public static boolean ai;
	public static Army goldArmy,silverArmy;

	//Constructor:
	public BreakthruRoom()
	{
		goldArmy = new Army();
		silverArmy = new Army();
   		board = new Board(goldArmy,silverArmy);
		
		j=4;
		for(int i=4; i<10;i++)
		{
			if(i<7)
			{
				goldArmy.addPiece(new Pawn(board.getSquare(3, i),Side.GOLD,7));
				goldArmy.addPiece(new Pawn(board.getSquare(7, i),Side.GOLD,7));
			}
			
			else
			{
				goldArmy.addPiece(new Pawn(board.getSquare(j,3),Side.GOLD,7));
				goldArmy.addPiece(new Pawn(board.getSquare(j,7),Side.GOLD,7));
				j++;
			}
			
		}
		
		goldArmy.addPiece(new Flagship(board.getSquare(5, 5),Side.GOLD,16));//Our flagship will cover

		j=3;
		for(int i=3; i<13;i++)
		{
			if(i<=row_column_temp)
			{
				silverArmy.addPiece(new Pawn(board.getSquare(i, 1),Side.SILVER,5));
				silverArmy.addPiece(new Pawn(board.getSquare(i, 9),Side.SILVER,5));
			}	
			else
			{	
				silverArmy.addPiece(new Pawn(board.getSquare(1,j),Side.SILVER,5));
				silverArmy.addPiece(new Pawn(board.getSquare(9,j),Side.SILVER,5));
				j++;
			}
			
		}	
			ChooseSideFunction();//Chooses the side of the players
			
			players = new HashMap<Side,BreakthruPlayer>();
			//Constructor of the players , in the near future i should implement also all the choices
			if(sideChosen==1 || sideChosen==4)
			{
				//ai=true;
				//AlphaBetaPlayer iterativeDeepening = new AlphaBetaPlayer(board,Side.SILVER);
				//players.put(Side.SILVER, new IterativeDeepening(board,Side.SILVER,iterativeDeepening));//,new AlphaBetaPlayer(board,Side.SILVER)));
				players.put(Side.SILVER,new AlphaBetaPlayer(board,Side.SILVER));
				players.put(Side.GOLD, new BreakthruPlayer(board,Side.GOLD));
				
			}
			else if(sideChosen==2 || sideChosen==3)
			{
				//AlphaBetaPlayer iterativeDeepening = new AlphaBetaPlayer(board,Side.GOLD);
				//players.put(Side.GOLD, new IterativeDeepening(board,Side.GOLD,iterativeDeepening));
				players.put(Side.GOLD,new AlphaBetaPlayer(board,Side.GOLD));
				players.put(Side.SILVER, new BreakthruPlayer(board,Side.SILVER));
			}
		
		}

	//We can steal this to settle the result of the game
	public void  ChooseSideFunction()
	{
		 panel = new JPanel(new BorderLayout());
 	 	 panel.setBorder(new EmptyBorder(2, 3, 2, 3));

	   	 layout = new JPanel(new GridBagLayout());
	   	 layout.setBorder(new EmptyBorder(5, 5, 5, 5));

	   	 JButton btn1 = new JButton("Dark Side"); 
	  	 JButton btn2 = new JButton("Light Side");
	  	 JButton btn3 = new JButton("Ai Dark Side --> First");
	  	 JButton btn4 = new JButton ("Ai Silver Side --> 2nd ");
	  	
	  	 btnPanel = new JPanel();
	  	 btnPanel.add(btn1);
	  	 btnPanel.add(btn2);
	  	 btnPanel.add(btn3);
	  	 btnPanel.add(btn4);
	  	 
	  	 
	  	 layout.add(btnPanel);
	   	 panel.add(layout, BorderLayout.CENTER); 
	  	 frame = new JFrame("Dark Side always has the first word");
	   	 frame.add(panel);
	   	 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	 frame.setLocationByPlatform(true);
	   	 frame.setSize(700, 600);
		 frame.setAlwaysOnTop(true);
	   	 frame.setVisible(true);
	  	 

	   btn1.addActionListener(new ActionListener()
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	    	sideChosen = 1;
	    	frame.dispose();
	    	frame.setVisible(false);

	    }
	  });

	  btn2.addActionListener(new ActionListener()
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	    	sideChosen = 2;
	    	frame.dispose();
	    	frame.setVisible(false);

	    }
	  });
	  
	  btn3.addActionListener(new ActionListener()
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	    	sideChosen = 3;
	    	frame.dispose();
	    	frame.setVisible(false);

	    }
	  });
	  btn4.addActionListener(new ActionListener()
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	    	sideChosen = 4;
	    	frame.dispose();
	    	frame.setVisible(false);

	    }
	  });

	  while(sideChosen!=1 && sideChosen!=2 && sideChosen!=3 && sideChosen!=4)
	  {
			try 
		  	{
				 Thread.sleep(1000);
		  	}
		  	catch(Exception e)
		  	{
		  		System.out.println("Everything is wrong here");
		  	}
	  }
	  
		 	  
	}
	
	public  Board getBoard()
	{
		return board;
	}
	
	public BreakthruPlayer getPlayer(Side side) 
	{
		return players.get(side);
	}
	
}
