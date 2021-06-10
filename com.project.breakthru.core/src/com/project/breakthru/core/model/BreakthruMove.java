package com.project.breakthru.core.model;

import com.project.breakthru.core.model.piece.Piece;

public class BreakthruMove implements Comparable<BreakthruMove>
{

	private Square initialSquare;
	private Square targetSquare;
	private Piece targetPiece;
	private int moveScore;
	private boolean actionMove,motionMove;
	
	public BreakthruMove()
	{
		
	}
	
	public BreakthruMove(Square initialSquare, Square targetSquare, Piece targetPiece) 
	{
		super();
		this.initialSquare = initialSquare;
		this.targetSquare = targetSquare;
		this.targetPiece = targetPiece;
	}
	
	
	public int getMoveScore() {
		return moveScore;
	}


	public void setMoveScore(int moveScore) {
		this.moveScore = moveScore;
	}


	public Square getInitialSquare() {
		return initialSquare;
	}
	public void setInitialSquare(Square initialSquare) {
		this.initialSquare = initialSquare;
	}
	public Square getTargetSquare() {
		return targetSquare;
	}
	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
	}
	public Piece getTargetPiece() {
		return targetPiece;
	}
	public void setTargetPiece(Piece targetPiece) {
		this.targetPiece = targetPiece;
	}
	
	@Override
	public String toString()
	{
		//This method prints x when we have actionMove and - when we have motionMove
		return initialSquare.getPiece()+ (this.motionMove ? "-" : "x") + (targetSquare.getPiece() == null ? "E" : targetSquare.getPiece()); 
	}


	public boolean isActionMove() {
		return actionMove;
	}


	public void setActionMove(boolean actionMove) {
		this.actionMove = actionMove;
	}


	public boolean isMotionMove() {
		return motionMove;
	}


	public void setMotionMove(boolean motionMove) {
		this.motionMove = motionMove;
	}

	@Override
	public int compareTo(BreakthruMove o) 
	{
		int compare = Integer.compare( this.moveScore,o.getMoveScore());
		return compare;
		//moveScore.compareTo(o.getMoveScore());
	}
	
	
}
