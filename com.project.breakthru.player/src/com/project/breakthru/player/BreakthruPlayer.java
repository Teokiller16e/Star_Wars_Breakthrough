package com.project.breakthru.player;

import java.util.ArrayList;
import java.util.List;

import com.project.breakthru.core.model.Army;
import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.BreakthruMove;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;
import com.project.breakthru.core.model.piece.Piece;

public class BreakthruPlayer 
{
	protected Board board;
	protected Side side;
	public  boolean actionMove,motionMove;
	public  int counter_moves;
	public static Piece previousPiece;
	
	public BreakthruPlayer(Board board, Side side)
	{	
		this.board = board;
		this.side = side;
	}
	//These two are Overrided methods so i can have access from them in every different AI technique/approach
	public BreakthruMove decideMove(int initialCounter)
	{
		return null;
	}
	
	public BreakthruMove evaluateNegaMax(int depth, int alpha, int beta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Make Move function 
	public void MakeMove (BreakthruMove move)
	{
		if(move.getTargetPiece() !=null)
		{
			Army army = board.getArmy(side.opposite());
			army.buryPiece(move.getTargetPiece());
		}
		Piece movingPiece = move.getInitialSquare().getPiece();
		movingPiece.setSquare(move.getTargetSquare());
		move.getTargetSquare().setPiece(movingPiece);
		move.getInitialSquare().setPiece(null);
	}
	
	
	//UnMake Move Function
	public void undoMove (BreakthruMove move)
	{
		Square initialSquare = move.getInitialSquare();
		Square targetSquare = move.getTargetSquare();
		Piece targetPiece = move.getTargetPiece();
		Piece movingPiece = targetSquare.getPiece();
		
		movingPiece.setSquare(initialSquare);
		initialSquare.setPiece(movingPiece);
		targetSquare.setPiece(null);
		
		if(targetPiece != null)
		{
			targetPiece.setSquare(targetSquare);
			targetSquare.setPiece(targetPiece);
			Army opponentArmy = board.getArmy(side.opposite());
			opponentArmy.revivePiece(targetPiece);
		}
	}

	//Store in arrayList all possible moves that alivePieces can do 
	public List<BreakthruMove> NegaMaxComputeLegalMoves()
	{
		List<Piece> alivePieces = board.getArmy(side).getAlivePieces();
		List<BreakthruMove> legalMoves = new ArrayList<BreakthruMove>();
		
		for(Piece p: alivePieces) 
		{
			//If it is the previous piece or the flagship do not consider the move
			if((previousPiece ==p || AlphaBetaPlayer.previousPiece == p || p.toString()=="F")&& AlphaBetaPlayer.alpha_beta_counter==1) 
				{continue;}
		
			for(Square s : p.computeLegalMoves(AlphaBetaPlayer.alpha_beta_counter))
			{
				BreakthruMove move = new BreakthruMove(p.getSquare(),s,s.getPiece());
				checkActionMoves(p.getSquare(),s);
				if(actionMove) 
				{
					move.setActionMove(true);
					move.setMotionMove(false);
					move.setMoveScore(100);
				}
				else
				{
					move.setActionMove(false);
					move.setMotionMove(true);
					move.setMoveScore(5);
				}
				move.getTargetSquare().setLegal(true);
				//if(move.getTargetSquare().equals(null))move.getTargetSquare().setLegal(true);
				legalMoves.add(move);
			}
		}
		
	
		return legalMoves;
	}
	
	
	
	
	public void checkActionMoves(Square initialSquare, Square targetSquare)
	{
		boolean test = false;
		//scanning if the given square is a capture move
		for(int h = -1; h<2; h+=2)
		{
			for(int v = -1; v<2; v+=2)
			{
				if(initialSquare.getAdjacentSquare(h, v)==targetSquare)
				{
					test=true;
					break;
				}			
			}
		}	
		
		if(test)
		{
			actionMove=true;
			motionMove=false;
		}
		else
		{
			actionMove=false;
			motionMove=true;
		}	
	}
	
	
	
	
	@Override
	public String toString()
	{
		return "Human Player User";
	}

	
}
