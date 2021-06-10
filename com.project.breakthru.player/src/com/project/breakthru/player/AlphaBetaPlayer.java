package com.project.breakthru.player;


import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.BreakthruMove;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;
import com.project.breakthru.core.model.piece.Piece;


public class AlphaBetaPlayer extends BreakthruPlayer
{
	private HashMap<Long,Integer> orderMoveList = new HashMap<Long,Integer>();
	public static Piece previousPiece;
	private static final int SEARCH_DEPTH = 4;
	public static int alpha_beta_counter;
	public static  ArrayList<Square> listOfTerminalMoves;
	public  int currentDepth;
	public  List<BreakthruMove> historyMoves;
	private final TranspositionTable TT;
	private Map<Long,Integer>sortedList = new TreeMap<Long,Integer>();
	
	public AlphaBetaPlayer(Board board, Side side) 
	{
		super(board, side);
		TT = new TranspositionTable();
		currentDepth = 0;
		historyMoves = new ArrayList<BreakthruMove>();
		FillInTerminalNodes();
	}

	@Override
	public BreakthruMove decideMove(int initialCounter)
	{
		alpha_beta_counter = initialCounter;
		BreakthruMove returnMove = evaluateNegaMax(SEARCH_DEPTH,-100000,100000);
	
		
		return returnMove;
	}
	
	//NegaMax : 
	@Override
	public BreakthruMove evaluateNegaMax(int depth, int alpha, int beta) 
	{	
		int rating = -100000;
	    BreakthruMove bestMove= new BreakthruMove();
	    List<BreakthruMove> testLegalMoves = NegaMaxComputeLegalMoves();
	    for (BreakthruMove move : testLegalMoves) 
	    {	
			
			/*
			 * for(int i = 0; i < 2- depth; i++) { System.out.print("\t"); }
			 * System.out.println(move.toString() + alpha_beta_counter);
			 */		
	    	
			MakeMove(move);
			historyMoves.add(move);
			currentDepth++;
	        //move.setMoveScore(0);
	        if (move.isMotionMove() && !move.isActionMove() && alpha_beta_counter==0)
			{
	        	
	        	previousPiece = move.getTargetSquare().getPiece();
				alpha_beta_counter++;
				if (depth-1 == 0 || IsTerminalNode()!=0) 
				{
					if(IsTerminalNode()>0)
						move.setMoveScore(12345678);
					else
					{
						move.setMoveScore(board.EvalutionFunction(side) *(Side.SILVER == side ? -1 : 1));//cause the silver is the minimizer and after maximizing he will negate
						//move.setMoveScore(board.EvalutionFunction(side) *(Side.SILVER == side ? -1 : 1));						
					}
				} 
				else
				{
					move.setMoveScore(evaluateNegaMax(depth-1, alpha, beta).getMoveScore());
					//System.out.println("Move Score : "+move.getMoveScore());			
				}
				alpha_beta_counter--;	
			}
			else
			{	
				int old_counter = alpha_beta_counter;
				alpha_beta_counter=0;
				previousPiece = null;
				side = side.opposite();
				
				if (depth-1 == 0 || IsTerminalNode()!=0) 
				{
					if(IsTerminalNode()!=0)
						move.setMoveScore(12345678);
					else
					{
						move.setMoveScore(board.EvalutionFunction(side) *(Side.SILVER == side ? -1 : 1));	
					}
				}
				else
				{
					move.setMoveScore(-evaluateNegaMax(depth-1,-beta,-alpha).getMoveScore());
					//System.out.println("Move Score : "+move.getMoveScore());
				}
				side = side.opposite();
				alpha_beta_counter = old_counter;
			}
	        historyMoves.remove(move);//just testing
	        undoMove(move);
	        currentDepth--;

	        
	        if(move.getMoveScore() > rating) 
			{
				rating = move.getMoveScore();
				bestMove = move;	
				
				/*
				 * int oldAlpha = alpha; long zobristEncoding = TT.ZobristHash(board); //check
				 * orderMoveList.put(zobristEncoding, move.getMoveScore()); sortedList =
				 * (Map<Long, Integer>) orderingFunction(orderMoveList);
				 */
				/*
				 * for(int i = 0; i < 2 - depth; i++) { System.out.print("\t"); }
				 * System.out.println("Best Move :"+bestMove.getMoveScore()
				 * +" Legal Property :"+bestMove.getTargetSquare().isLegal());
				 */
			}
	        
	        if(rating>alpha)
	        	alpha = rating;
	        
	        if(alpha>= beta)
	        {
				/*
				 * for(int i = 0; i < 2 - depth; i++) { System.out.print("\t"); }
				 * System.out.println("Pruning Here");
				 */	 
	        	break;
	        }	       
  
	    }
		return bestMove;
	}
	

	//Fills in the list with all the terminal positions of the flagship
	private  void FillInTerminalNodes()
	{
		listOfTerminalMoves = new ArrayList<Square>();

		for(int r=0;r<11;r+=10)
		{
			for(int c=0;c<11;c++)
				listOfTerminalMoves.add(board.getSquare(r, c));			
		}
		
		for(int c=0;c<11;c+=10)
		{
			for(int r=0;r<11;r++)
			{
				listOfTerminalMoves.add(board.getSquare(r, c));
			}
		}
	}
	
	
	private  <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> orderingFunction(Map<K,V> map) 
	{
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
	                return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	//This is the function that we will implement inside if(depth == 0)
	private  int IsTerminalNode()
	{	
		int gameOver=0;
		
		for(Piece p : Board.goldArmy.getAlivePieces())
		{
			if(p.toString()=="F")
				if(listOfTerminalMoves.contains(p.getSquare())) {gameOver=1;}		
		}
		
		for(Piece p :Board.goldArmy.getDeadPieces())
		{
			if(p.toString()=="F") {gameOver=2;}
		}
		
		return gameOver;
	}

}
