package com.project.breakthru.core.model.piece;

import java.util.ArrayList;
import java.util.List;

import com.project.breakthru.core.icon.IconHandler;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;

public class Flagship extends Piece
{

	public Flagship(Square square, Side side, int score) 
	{
		super(square, side, score,IconHandler.getIcon(side,"flagship"));//Maybe i need to fix it with providing the name on it 
	}

	@Override
	public String toString()
	{
		return "F";
	}

	//TODO for sure here
	@Override
	public List<Square> computeLegalMoves(int AlphaBetaCounter) 
	{
		List<Square> legalMoves = new ArrayList<Square>();
		Square targetSquare;
		
		if(AlphaBetaCounter==1)return legalMoves;//If the counter is 1 we already know that we do not have to re calculate the moves of the Flagship
		
		//Linear/Motion Moves squares that we will include to our list:
		legalMoves.addAll(computeLinearMoves(1,0));
		legalMoves.addAll(computeLinearMoves(-1,0));
		legalMoves.addAll(computeLinearMoves(0,1));
		legalMoves.addAll(computeLinearMoves(0,-1));

		//attacking moves only
		for(int h = -1; h<2; h+=2)
		{
			for(int v = -1; v<2; v+=2)
			{
				
				 targetSquare = square.getAdjacentSquare(h,v);
				 checkSquare(targetSquare,legalMoves);
			}
		}
		return legalMoves;
	}
}
