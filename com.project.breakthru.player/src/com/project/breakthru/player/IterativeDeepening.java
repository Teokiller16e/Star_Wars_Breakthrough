package com.project.breakthru.player;


import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.BreakthruMove;
import com.project.breakthru.core.model.Side;

public class IterativeDeepening extends BreakthruPlayer {
	private static final int INITIAL_DEPTH = 4;
	private static final int TIMEOUT_MILISECONDS = 10000;
	private static int currentDepth;
	//private BreakthruMove bestMove;
	private BreakthruMove globalBestMove;
	private  AlphaBetaPlayer alphaBeta;
	private long start;

	public IterativeDeepening(Board board, Side side,AlphaBetaPlayer test) {
		super(board, side );
		this.alphaBeta = test;

	}

	@Override
	public BreakthruMove decideMove(int initialCounter)
	{
		start = System.currentTimeMillis();
		long end = start + TIMEOUT_MILISECONDS;
		
		globalBestMove = null;
		
		for (int d = 1;d<INITIAL_DEPTH &&System.currentTimeMillis()<=end;d++) 
		{
			currentDepth = d;
			Thread t = new Thread(()->{globalBestMove = alphaBeta.evaluateNegaMax(currentDepth,-100000, 100000);});
			t.start();
			
			
			while(t.isAlive() && System.currentTimeMillis()<=end)
			{}
			if(t.isAlive())
			{	
				t.interrupt();
				while(alphaBeta.currentDepth>0) 
				{ 
					int index = alphaBeta.historyMoves.size();
					alphaBeta.undoMove(alphaBeta.historyMoves.get(index - 1));
					alphaBeta.currentDepth--;
				}
			}
			
		}
		return globalBestMove;
	}

	

}
