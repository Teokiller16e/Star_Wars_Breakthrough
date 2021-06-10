package com.project.breakthru.app.listener;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;

import com.project.breakthru.app.parts.PartRefresher;
import com.project.breakthru.app.parts.breakthruBoardPart;
import com.project.breakthru.app.room.BreakthruRoom;
import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.BreakthruMove;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;
import com.project.breakthru.core.model.piece.Piece;
import com.project.breakthru.player.AlphaBetaPlayer;
import com.project.breakthru.player.BreakthruPlayer;

public class BreakthruMoveListener extends MouseAdapter 
{
	private final Label label;
	public static Board board;
	private static Piece selectedPiece;
	public static Piece previousPiece;
	private static Side side;
	public static BreakthruPlayer golden,silver;
	//private static JPanel panel,layout,btnPanel;
	private static JFrame frame; 
	//private static int side_counter;
	
	public BreakthruMoveListener (Label label)
	{
		this.label = label;
		if(BreakthruRoom.sideChosen==1)
			side = Side.GOLD;
		else if(BreakthruRoom.sideChosen==2)
			side= Side.SILVER;
		else if (BreakthruRoom.sideChosen==3)
			side = Side.GOLD; 
		else if (BreakthruRoom.sideChosen==4) 
			side = Side.SILVER; 
		 
		
		
				
		board = breakthruBoardPart.getBreakthruRoom().getBoard();
		golden = breakthruBoardPart.getBreakthruRoom().getPlayer(Side.GOLD);
		silver = breakthruBoardPart.getBreakthruRoom().getPlayer(Side.SILVER);	
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) 
	{
			resetLegalSquares();
			Piece piece = ( (Square) (label.getData())).getPiece();
			
			if(piece!=null && piece.getSide()==side)
			{
				selectedPiece = piece;
				if(BreakthruRoom.sideChosen==1 || BreakthruRoom.sideChosen==4)
				{
					for(Square square : piece.computeLegalMoves(golden.counter_moves))
					{
						if(selectedPiece == previousPiece)
							square.setLegal(false);
						else
						square.setLegal(true);
						
					}
				}
				else if (BreakthruRoom.sideChosen==2|| BreakthruRoom.sideChosen==3)
				{
					for(Square square : piece.computeLegalMoves(silver.counter_moves))
					{
						if(selectedPiece == previousPiece)
							square.setLegal(false);
						else
						square.setLegal(true);
						
					}
				}
			}	
	}
	
	@Override
	public void mouseDown(MouseEvent e) 
	{			
		if(BreakthruRoom.sideChosen==1 ) //Choosing Gold side
		{
			if(side== Side.GOLD)
			{
				DecideMovePlayerGold();
				/*
				 * Square targetSquare = (Square)label.getData(); if(targetSquare.isLegal()) {
				 * Square initialSquare = selectedPiece.getSquare(); Piece targetPiece =
				 * targetSquare.getPiece(); BreakthruMove move = new
				 * BreakthruMove(initialSquare,targetSquare,targetPiece);
				 * 
				 * if(IsTerminalNode(move)==2) {GameOver();}
				 * 
				 * golden.checkActionMoves(initialSquare,targetSquare); golden.MakeMove(move);
				 * //System.out.println("Silver played score || Gold Score :"+board.
				 * EvalutionFunction(side)); board.PrintFeatures(side);
				 * 
				 * if(golden.counter_moves==0) { if(golden.actionMove ||
				 * move.getTargetSquare().getPiece().toString()=="F") { side= side.opposite();
				 * golden.counter_moves=0; DecideMouseListenerSilver(); } else {
				 * golden.counter_moves++; previousPiece = move.getTargetSquare().getPiece(); }
				 * } else if(golden.counter_moves==1) golden.counter_moves++;
				 * 
				 * if(golden.counter_moves==2) { side = side.opposite(); golden.counter_moves=0;
				 * previousPiece = null; DecideMouseListenerSilver();//reset previous piece }
				 * resetLegalSquares(); }
				 */
			}
			//In silver's side we have to specify the action & motion moves to find the n of moves
			/*
			 * else if (side == Side.SILVER) { BreakthruMove move =
			 * silver.decideMove(silver.counter_moves);
			 * //System.out.println("Move legal property -->"+move.getTargetSquare().isLegal
			 * ());
			 * 
			 * boolean moveLegalProperty = false; try { moveLegalProperty=
			 * move.getTargetSquare().isLegal();
			 * 
			 * } catch(Exception f) { System.out.println("Exception"); f.getCause();
			 * GameOver();//In this case it has not a move to return and surrenders }
			 * if(move.getMoveScore()== 12345678) {GameOver();}
			 * 
			 * 
			 * if(moveLegalProperty==true) { silver.MakeMove(move);
			 * //System.out.println("Silver made a move");
			 * //System.out.println("Silver played  Score :"+board.EvaluationFunctionSilver(
			 * side)*-1); if(silver.counter_moves==0) { if(move.isActionMove()==true) {
			 * side=side.opposite(); silver.counter_moves=0; } else {
			 * silver.counter_moves++; BreakthruPlayer.previousPiece =
			 * move.getTargetSquare().getPiece(); } }
			 * 
			 * else if(silver.counter_moves==1) { silver.counter_moves++; } }
			 * 
			 * if(silver.counter_moves==2) { side = side.opposite(); silver.counter_moves=0;
			 * BreakthruPlayer.previousPiece = null; }
			 * 
			 * resetLegalSquares(); }
			 */
			
		}
				
		else if (BreakthruRoom.sideChosen==2)//Choosing silver 
		{
			if(side== Side.SILVER)
			{
				DecideMovePlayerSilver();
				/*
				 * Square targetSquare = (Square)label.getData(); if(targetSquare.isLegal()) {
				 * Square initialSquare = selectedPiece.getSquare(); Piece targetPiece =
				 * targetSquare.getPiece(); BreakthruMove move = new
				 * BreakthruMove(initialSquare,targetSquare,targetPiece);
				 * 
				 * if(IsTerminalNode(move)==2) {GameOver();}
				 * 
				 * silver.checkActionMoves(initialSquare,targetSquare); silver.MakeMove(move);
				 * //System.out.println("Silver played score || Gold Score :"+board.
				 * EvalutionFunction(side)); board.PrintFeatures(side);
				 * if(silver.counter_moves==0) { if(silver.actionMove ||
				 * move.getTargetSquare().getPiece().toString()=="F") { side= side.opposite();
				 * silver.counter_moves=0; DecideMouseListenerGold(); } else {
				 * silver.counter_moves++; previousPiece = move.getTargetSquare().getPiece(); }
				 * } else if(silver.counter_moves==1) silver.counter_moves++;
				 * 
				 * if(silver.counter_moves==2) { side = side.opposite(); silver.counter_moves=0;
				 * previousPiece = null;//reset previous piece DecideMouseListenerGold(); }
				 * resetLegalSquares();
				 * 
				 * }
				 */
				
			}
			
			//In silver's side we have to specify the action & motion moves to find the n of moves
			/*
			 * else if (side == Side.GOLD) { BreakthruMove move =
			 * golden.decideMove(golden.counter_moves);
			 * 
			 * if(move.getMoveScore()== 12345678 ) { GameOver();}
			 * 
			 * boolean moveLegalProperty = false; try { moveLegalProperty=
			 * move.getTargetSquare().isLegal(); } catch(Exception f) {
			 * System.out.println("Exception"); f.getCause(); GameOver();//In this case it
			 * has not a move to return and surrenders }
			 * 
			 * if(moveLegalProperty) { golden.MakeMove(move);
			 * //System.out.println("Gold played score|| Silver Score :"+board.
			 * EvalutionFunction(side)); if(golden.counter_moves==0) {
			 * if(move.isActionMove()==true ||
			 * move.getTargetSquare().getPiece().getScore()==16) { side=side.opposite();
			 * golden.counter_moves=0; } else { golden.counter_moves++;
			 * BreakthruPlayer.previousPiece = move.getTargetSquare().getPiece(); } }
			 * 
			 * else if(golden.counter_moves==1) { golden.counter_moves++; } }
			 * 
			 * if(golden.counter_moves==2) { side = side.opposite(); golden.counter_moves=0;
			 * BreakthruPlayer.previousPiece = null; }
			 * 
			 * resetLegalSquares(); }
			 */
		}
		else if (BreakthruRoom.sideChosen==3)
		{
			if(side==Side.GOLD)
			{DecideMouseListenerGold();}
			else if(side==Side.SILVER)
			{DecideMovePlayerSilver();}
			
		}
		else if (BreakthruRoom.sideChosen==4)
		{
			if(side==Side.SILVER)
			{DecideMouseListenerSilver();}
			else if (side == Side.GOLD)
			{DecideMovePlayerGold();}
		}
	}

		
	@Override
	public void mouseUp( MouseEvent e)
	{
		PartRefresher.refresh();
	}

	
	private static  void resetLegalSquares()
	{
		for(int r=0; r<Board.LENGTH;r++)
		{
			for(int c=0;c<Board.LENGTH;c++)
			{
				board.getSquare(r, c).setLegal(false);
			}
		}
	}
	
	public void DecideMovePlayerGold()
	{
		
		Square targetSquare = (Square)label.getData();
		if(targetSquare.isLegal())
		{			
			Square initialSquare = selectedPiece.getSquare();
			Piece targetPiece = targetSquare.getPiece();
			BreakthruMove move = new BreakthruMove(initialSquare,targetSquare,targetPiece);
			
			if(IsTerminalNode(move)==2)
			{GameOver();}
			
				golden.checkActionMoves(initialSquare,targetSquare);	
				golden.MakeMove(move);
				//System.out.println("Silver played score || Gold Score :"+board.EvalutionFunction(side));
				board.PrintFeatures(side);

				if(golden.counter_moves==0)
				{
					if(golden.actionMove || move.getTargetSquare().getPiece().toString()=="F")
					{
						side= side.opposite();
						golden.counter_moves=0;
						DecideMouseListenerSilver();
					}
					else
					{
						golden.counter_moves++;
						previousPiece = move.getTargetSquare().getPiece();
					}
				}
				else if(golden.counter_moves==1)
					golden.counter_moves++;
						
				if(golden.counter_moves==2)
				{
					side = side.opposite();
					golden.counter_moves=0;
					previousPiece = null;
					DecideMouseListenerSilver();//reset previous piece
				}
				resetLegalSquares();
		}
	}
	
	public void DecideMovePlayerSilver()
	{
		Square targetSquare = (Square)label.getData();
		if(targetSquare.isLegal())
		{			
			Square initialSquare = selectedPiece.getSquare();
			Piece targetPiece = targetSquare.getPiece();
			BreakthruMove move = new BreakthruMove(initialSquare,targetSquare,targetPiece);
			
			if(IsTerminalNode(move)==2)
			{GameOver();}
			
				silver.checkActionMoves(initialSquare,targetSquare);	
				silver.MakeMove(move);
				//System.out.println("Silver played score || Gold Score :"+board.EvalutionFunction(side));
				board.PrintFeatures(side);
				if(silver.counter_moves==0)
				{
					if(silver.actionMove || move.getTargetSquare().getPiece().toString()=="F")
					{
						side= side.opposite();
						silver.counter_moves=0;
						DecideMouseListenerGold();
					}
					else
					{
						silver.counter_moves++;
						previousPiece = move.getTargetSquare().getPiece();
					}
				}
				else if(silver.counter_moves==1)
					silver.counter_moves++;
						
				if(silver.counter_moves==2)
				{
					side = side.opposite();
					silver.counter_moves=0;
					previousPiece = null;//reset previous piece
					DecideMouseListenerGold();
				}
				resetLegalSquares();
				
		}
		
	}
	public void DecideMouseListenerSilver()
	{
		BreakthruMove move = silver.decideMove(silver.counter_moves);
		//System.out.println("Move legal property -->"+move.getTargetSquare().isLegal());
	
		boolean moveLegalProperty = false;
		try
		{
			moveLegalProperty= move.getTargetSquare().isLegal();
				
		}
		catch(Exception f)
		{
			System.out.println("Exception");
			f.getCause();
			GameOver();//In this case it has not a move to return and surrenders
		}
		if(move.getMoveScore()== 12345678)
		{GameOver();}
		
		
		if(moveLegalProperty==true)
		{
			silver.MakeMove(move);
			//System.out.println("Silver made a move");
			//System.out.println("Silver played  Score :"+board.EvaluationFunctionSilver(side)*-1);
			board.PrintFeatures(side);

			if(silver.counter_moves==0)
			{
				if(move.isActionMove()==true)
				{
					side=side.opposite();
					silver.counter_moves=0;
				}
				else
				{
					silver.counter_moves++;
					BreakthruPlayer.previousPiece = move.getTargetSquare().getPiece();
					DecideMouseListenerSilver();
				}
			}

			else if(silver.counter_moves==1)
			{
				silver.counter_moves++;
			}				
		}
		
		if(silver.counter_moves==2)
		{
			side = side.opposite();
			silver.counter_moves=0;
			BreakthruPlayer.previousPiece = null;
		}
			
		resetLegalSquares();
	}
	
	public void DecideMouseListenerGold()
	{
		//golden.counter_moves=0;
		BreakthruMove move = golden.decideMove(golden.counter_moves);
		
		if(move.getMoveScore()== 12345678 )
		{ GameOver();}

		boolean moveLegalProperty = false;
		try
		{
			moveLegalProperty= move.getTargetSquare().isLegal();
				
		}
		catch(Exception f)
		{
			System.out.println("Exception");
			f.getCause();
			GameOver();//In this case it has not a move to return and surrenders
		}
		
		if(moveLegalProperty)
		{
			golden.MakeMove(move);
			//System.out.println("Gold played score|| Silver Score :"+board.EvalutionFunction(side));
			if(golden.counter_moves==0)
			{
				if(move.isActionMove()==true || move.getTargetSquare().getPiece().toString()=="F")
				{
					side=side.opposite();
					golden.counter_moves=0;
				}
				else
				{
					golden.counter_moves++;
					BreakthruPlayer.previousPiece = move.getTargetSquare().getPiece();
					DecideMouseListenerGold();
				}
			}

			else if(golden.counter_moves==1)
			{
				golden.counter_moves++;
			}				
		}
	
		if(golden.counter_moves==2)
		{
			side = side.opposite();
			golden.counter_moves=0;
			BreakthruPlayer.previousPiece = null;
		}
		
	resetLegalSquares();
	}
	
	//This is the function that we will implement inside if(depth == 0)
	private  int IsTerminalNode(BreakthruMove move)
	{	
		int gameOver=0;
		
			if(move.getInitialSquare().getPiece().toString()=="F")
			{
				if(AlphaBetaPlayer.listOfTerminalMoves.contains(move.getTargetSquare()))
					gameOver=1;
				
				 if(Board.goldArmy.getDeadPieces().contains(move.getInitialSquare().getPiece()))
						gameOver=2;
					
			}
	
		return gameOver;
	}

	public void  GameOver()
	{  	 
	  	 frame = new JFrame("");
	   	 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	 JOptionPane.showMessageDialog(frame,"GAME OVER YEEE  WON.");
	}

}
