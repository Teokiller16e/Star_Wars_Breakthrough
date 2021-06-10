package com.project.breakthru.core.model.piece;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;

public abstract class Piece 
{
	public static final int GOLD_PAWNS = 12;
	public static final int SILVER_PAWNS = 20;
	
	private  int score;
	public Square square;
	protected final Side side;
	protected final Image icon;

	
	public abstract List<Square> computeLegalMoves(int AlphaBetaCounter);
	
	protected Piece(Square square , Side side, int score,Image icon)
	{
		this.square = square;
		this.square.setPiece(this);
		this.score = score;
		this.side = side;
		this.icon = icon;
	}
		
	public void setSquare(Square square)
	{
		this.square = square;
	}
	
	public Square getSquare()
	{
		return square;
	}
	 
	public Image getIcon()
	{
		return icon;
	}
	
	
	public Side getSide() {
		return side;
	}

	protected void checkSquare(Square targetSquare,List<Square> legalMoves)
	{
		//if the square does not return null and square has enemy pawn on it, then I am allowed to make a move
		if(targetSquare != null && targetSquare.getPiece()!=null && targetSquare.getPiece().side != side )
		{
			legalMoves.add(targetSquare);
		}
	}
	
	
	public int getScore()
	{
		return this.score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	
	public int LinearMoves(int horizontal, int vertical)
	{
		Square targetSquare = square.getAdjacentSquare(horizontal, vertical);
		boolean boost = false;
		int h=0;
		int v = 0;
		//int i=0;
		while(targetSquare!=null)
		{
			//check if targetSquare contains piece in order to move vertical&horizontal(motions moves)
			try 
			{
				if(targetSquare.getPiece().getScore()==16)
				{
					h = targetSquare.rows;
					v = targetSquare.columns;
					
					for(int i=0;i<h;i++)
					{
						for(int j=0;j<v;j++)
						{
							//if()
						}
					}
					boost = true;
					break;
				}	
			}
				catch(Exception e)
				{e.getCause();}
				targetSquare = targetSquare.getAdjacentSquare(horizontal, vertical);
			}
		
		if(boost == true)
			return 1;
		else
			return 0;
	}
	
	
	
	protected List<Square> computeLinearMoves(int horizontal, int vertical)
	{
		List<Square>legalMoves = new ArrayList<Square>();
		Square targetSquare = square.getAdjacentSquare(horizontal, vertical);
		//int i=0;
		while(targetSquare!=null)
		{
			//check if targetSquare contains piece in order to move vertical&horizontal(motions moves)
			if(targetSquare.getPiece()==null)
			{
				legalMoves.add(targetSquare); 
			}
			else if (targetSquare.getPiece().side == Side.GOLD || targetSquare.getPiece().side == Side.SILVER)
			{
				break;
			}
			targetSquare = targetSquare.getAdjacentSquare(horizontal, vertical);
		}
		
		return legalMoves;
	}
	
}
