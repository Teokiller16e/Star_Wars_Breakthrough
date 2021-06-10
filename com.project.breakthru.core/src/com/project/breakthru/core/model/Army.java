package com.project.breakthru.core.model;

import java.util.ArrayList;
import java.util.List;

import com.project.breakthru.core.model.piece.Piece;

public class Army 
{

	private List<Piece> alivePieces;
	private List<Piece> deadPieces;
	
	public Army()
	{
		this.alivePieces = new ArrayList<Piece>();
		this.deadPieces = new ArrayList<Piece>();
	}
	
	public void addPiece(Piece piece)
	{
		alivePieces.add(piece);
		
	}
	
	public void revivePiece(Piece piece)
	{
		deadPieces.remove(piece);
		alivePieces.add(piece);
	}

	public List<Piece> getAlivePieces() {
		return alivePieces;
	}

	public void setAlivePieces(List<Piece> alivePieces) {
		this.alivePieces = alivePieces;
	}

	public List<Piece> getDeadPieces() {
		return deadPieces;
	}

	public void setDeadPieces(List<Piece> deadPieces) {
		this.deadPieces = deadPieces;
	}
	
	public void buryPiece(Piece piece)
	{
		alivePieces.remove(piece);
		deadPieces.add(piece);
	}
}
