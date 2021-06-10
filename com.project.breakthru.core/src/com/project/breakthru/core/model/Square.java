package com.project.breakthru.core.model;

import com.project.breakthru.core.model.piece.Piece;

public class Square 
{
	
	private final Board board;
	public final int rows;
	public final int columns;
	private Piece piece;
	private boolean legal;
	


	public Square(Board board,int rows,int columns) 
	{
		this.board = board; 
		this.rows = rows;
		this.columns = columns;
		this.legal=false; //Maybe it was setting to null for some unknown reason
	}
	
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	public Piece getPiece() 
	{
		return piece;
	}
	
	public boolean isLegal() 
	{
		return legal;
	}

	public void setLegal(boolean legal) 
	{
		this.legal = legal;
	}

	
	public Square getAdjacentSquare(int horizontal , int vertical)
	{
		return board.getSquare(vertical + rows,horizontal + columns);
	}

	//Function Ready to use
	public static int returnPiece(Square square)
	{
		if(square.getPiece()!=null)
		{
			if(square.getPiece().getScore()==7)
				return 1;
			
			else if (square.getPiece().getScore()==5)
				return 2;
			
			else if (square.getPiece().getScore()==16)
				return 3;
		}
		return 0;
	}
}
