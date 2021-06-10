package com.project.breakthru.app.parts;


import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import com.project.breakthru.app.listener.BreakthruMoveListener;
import com.project.breakthru.app.room.BreakthruRoom;
import com.project.breakthru.core.icon.IconHandler;
import com.project.breakthru.core.model.Board;
import com.project.breakthru.core.model.piece.Piece;
import com.project.breakthru.core.model.Square;

public class breakthruBoardPart 
{	
    private static final String[] COL_LETTERS = {"A","B","C","D","E","F", "G","H","I","J","K"};
	private static BreakthruRoom breakthruRoom;
	private Label[][] squares;
		
	public breakthruBoardPart() 
	{
			super();
		
			breakthruRoom = new BreakthruRoom();
			squares = new Label[Board.LENGTH+1][Board.LENGTH+1];	
	}


	
	@PostConstruct
	public void createComposite(Composite parent) 
	{
		parent.setLayout(new GridLayout(Board.LENGTH + 1, true));
        GridData tagGridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		GridData squareGridData = new GridData(SWT.CENTER,SWT.CENTER,true,false);
		
	  		for(int r = Board.LENGTH - 1; r >= 0; r--)
	  		{	
	  			squares[r][0] = new Label(parent, SWT.NONE);
	            squares[r][0].setLayoutData(tagGridData);
	            squares[r][0].setText(String.format("%s", r + 1));
	  			for(int c=0;c<Board.LENGTH;c++)
	  			{
	  				squares[r][c+1] = new Label(parent,SWT.BORDER);
	  				squares[r][c+1].setLayoutData(squareGridData);
	  				squares[r][c+1].setData(breakthruRoom.getBoard().getSquare(r, c));
		  			squares[r][c+1].addMouseListener(new BreakthruMoveListener(squares[r][c+1]));	  					
	  			}
	  		}
	  		
	  		new Label(parent, SWT.NONE);
	         
	         for (int c = 0; c < Board.LENGTH; c++)
	         {
	             squares[Board.LENGTH][c + 1] = new Label(parent, SWT.NONE);
	             squares[Board.LENGTH][c + 1].setText(COL_LETTERS[c]);
	             squares[Board.LENGTH][c + 1].setLayoutData(tagGridData);
	         }
	  		
			setFocus();
			PartRefresher.setBreakthruPart(this);
	}

	@Focus
	public void setFocus() 
	{
		//Construction of an 2x2 Label array:
			for(int r = Board.LENGTH - 1; r >= 0; r--)
			{	
					for(int c=0;c<Board.LENGTH;c++)
					{	
						squares[r][c+1].setBackground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
											
						if(((Square) squares[r][c+1].getData()).isLegal())//It returns the legal attribute wrong
						{squares[r][c+1].setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));}
						
						Piece piece = ((Square) squares[r][c+1].getData()).getPiece();
						
						if(piece == null)	
							squares[r][c+1].setImage(IconHandler.getBlankIcon());							
						else
							squares[r][c+1].setImage(piece.getIcon());								
					}
			}		
	}
	
	
	public static BreakthruRoom getBreakthruRoom()
	{

		return breakthruRoom;
	}
	
	
	
}