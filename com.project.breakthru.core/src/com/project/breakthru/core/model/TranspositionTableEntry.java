package com.project.breakthru.core.model;

public class TranspositionTableEntry 
{
	private final BreakthruMove bestMove;
	private final int value,type,searchDepth;
	public  long hashValue;
	
	public TranspositionTableEntry(BreakthruMove CbestMove, int Cvalue, int Ctype, int CsearchDepth, long ChashValue)
	{
		super();
		this.bestMove = CbestMove;
		this.value = Cvalue;
		this.type = Ctype;
		this.searchDepth = CsearchDepth;
		this.hashValue = ChashValue;
	}
	
}
