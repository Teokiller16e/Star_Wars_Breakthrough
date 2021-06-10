package com.project.breakthru.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.project.breakthru.core.model.Army;
import com.project.breakthru.core.model.Side;

public class GraveyardPart 
{
	protected Side side ;
	private Label[] deadPiecesLabel;
	
	public GraveyardPart()
	{
		//if(side == Side.GOLD)
			deadPiecesLabel = new Label [33];
		//else
		//	deadPieces = new Label [Piece.SILVER_PAWNS];
	}

	@PostConstruct
	public void createComposite(Composite parent) 
	{
		parent.setLayout(new GridLayout(2,false));
		GridData gridData = new GridData(SWT.CENTER,SWT.CENTER,true,false);
		gridData.widthHint = 50;
		gridData.heightHint = 100;
		
		for(int p=0; p<33;p++)
		{  
			deadPiecesLabel[p] = new Label(parent,SWT.NONE);
			deadPiecesLabel[p].setLayoutData(gridData);
		}
		PartRefresher.refresh();
	}

	@Focus
	public void setFocus() 
	{
			Army army = breakthruBoardPart.getBreakthruRoom().getBoard().getArmy(side);
		
			for(int p=0; p<army.getDeadPieces().size();p++)
			{  
				deadPiecesLabel[p].setImage(army.getDeadPieces().get(p).getIcon());
			}	
	}
}
