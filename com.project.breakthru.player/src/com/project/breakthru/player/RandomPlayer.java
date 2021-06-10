package com.project.breakthru.player;

import java.util.List;
import java.util.Random;

import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.BreakthruMove;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;
import com.project.breakthru.core.model.piece.Piece;

public class RandomPlayer extends BreakthruPlayer{

	private Random random;
	
	public RandomPlayer(Board board, Side side) 
	{
		super(board, side);
		random = new Random();
	}

	public BreakthruMove decideMove()
	{
		List<Piece> pieces = board.getArmy(side).getAlivePieces();
		List<Square> randomLegalMoves;
		Piece randomPiece;
		do
		{
			int randomPieceIndex = random.nextInt(pieces.size());
			randomPiece = pieces.get(randomPieceIndex);
			randomLegalMoves = randomPiece.computeLegalMoves(0);
			
			
		}
		while(randomLegalMoves.size() == 0);
		
		int randomMoveIndex = random.nextInt(randomLegalMoves.size());
		Square randomTargetSquare = randomLegalMoves.get(randomMoveIndex);
		
		
		return new BreakthruMove(randomPiece.getSquare(),randomTargetSquare,randomTargetSquare.getPiece());
	}
	
	@Override
	public String toString()
	{
		return "Random Player";
	}
}
