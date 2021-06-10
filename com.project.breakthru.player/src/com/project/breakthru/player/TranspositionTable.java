package com.project.breakthru.player;

import java.util.Random;

import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.Side;
import com.project.breakthru.core.model.Square;
import com.project.breakthru.core.model.TranspositionTableEntry;

public class TranspositionTable 
{
	public long randomPosition[][][];
	private final TranspositionTableEntry TT[];
	private final Random rd;

	public TranspositionTable() 
	{
		randomPosition = new long[11][11][4];
		rd = new Random();
		for (int i = 0; i < Board.LENGTH; i++) 
		{
			for (int j = 0; j < Board.LENGTH; j++) 
			{
				for (int p = 0; p <= 3; p++) 
				{
					randomPosition[i][j][p] = Math.abs(rd.nextLong());
				}
			}
		}
        	TT = new TranspositionTableEntry[1048576];//I don't get it

            for (int i = 0; i < TT.length - 1; i++)
            {
                TT[i] = null;
            }

	}
	
	public void setEntry (long ZobristHash, TranspositionTableEntry Entry) 
	{
		long index = ZobristHash & (0xFFFFF);
		TT[(int) index] = Entry;
	}
	
	public TranspositionTableEntry getEntry (long ZobristHash) 
	{
		long index = ZobristHash & (0xFFFFF);
		return TT[(int) index] != null && TT[(int) index].hashValue ==ZobristHash ? TT[(int) index] : null;
		
	}
	
	
	public  long ZobristHash(Board board) 
	{
		//int [][]p = new int [11][11];
		
		
		
		long code = randomPosition[0][0][0];

		for (int i = 0; i < 11; i++) 
		{ 
			for (int j = 0; j < 11; j++) 
			{ 
				if(board.getSquare(i, j).getPiece()==null)
					{code ^= randomPosition[i][j][0];}
				if(board.getSquare(i, j).getPiece().toString()=="S")
					{code ^= randomPosition[i][j][1];}
				if(board.getSquare(i, j).getPiece().toString()=="G")
					{code ^= randomPosition[i][j][2];}
				if(board.getSquare(i, j).getPiece().toString()=="F")
					{code ^= randomPosition[i][j][3];}
			} 
		}
		return code; 
	}
	

}
