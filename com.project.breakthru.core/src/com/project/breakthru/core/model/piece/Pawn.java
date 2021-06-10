package com.project.breakthru.core.model.piece;

import java.util.ArrayList;
import java.util.List;

import com.project.breakthru.core.icon.IconHandler;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;

public class Pawn extends Piece
{

	public Pawn(Square square, Side side, int score) 
	{
		super(square, side, score, IconHandler.getIcon(side,"pawn"));

	}

	@Override
	public String toString()
	{
		return this.side == Side.GOLD ? "G":"S";
	}

	//@Override
	public List<Square> computeLegalMoves(int AlphaBetaCounter) 
	{
		List<Square> legalMoves = new ArrayList<Square>();
		Square targetSquare;
		
		
		if(AlphaBetaCounter==0)
		{
			//attacking moves only implemented:
			for(int h = -1; h<2; h+=2)
			{
				for(int v = -1; v<2; v+=2)
				{
					 targetSquare = square.getAdjacentSquare(h,v);
					 checkSquare(targetSquare,legalMoves);
				}
			}
			
		}
	
		
		//Linear/Motion Moves squares that we will include to our list:
		legalMoves.addAll(computeLinearMoves(1,0)); 
		legalMoves.addAll(computeLinearMoves(-1,0));
		legalMoves.addAll(computeLinearMoves(0,1));
		legalMoves.addAll(computeLinearMoves(0,-1));
		
		
		return legalMoves;
	}
	
	
	
}
