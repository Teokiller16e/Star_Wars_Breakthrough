package com.project.breakthru.core.model;



import com.project.breakthru.core.model.piece.Piece;


public class Board 
{
	public static final int LENGTH = 11;//how to initialize a constant, in which no one can modify
	public Square[][] squares;
	public static Army goldArmy;
	public static Army silverArmy;
	public static int gameOver;
	public static int boostGold,boostSilver;

	public Board(Army GoldArmy, Army SilverArmy)
	{
		goldArmy = GoldArmy;
		silverArmy = SilverArmy;
		squares = new Square[LENGTH][LENGTH]; //initialize 2d array
				
		for(int r=0; r<LENGTH ; r++)
		{
			for(int c=0;c<LENGTH;c++)
			{
				squares[r][c] = new Square(this,r,c);
			}
		}
	}
	
	
	//Evaluation function Generic:
	public int EvalutionFunction(Side side)//calculating the score of the game
	{
		//Weights for gold and weights for silver
		int goldArmyScore=0,silverArmyScore=0;
		int f1=0,f2=0,f4=0,f3=0,f5=0,f6=0;
		boolean flag_path;
		//weights:
		int wg1=20,wg2=-15,wg3=10,wg4=-10,wg5=20,wg6=0;
		int sg1=-20,sg2=15,sg3=10,sg4=10,sg5=-10,sg6=10;
		
		for(Piece p : goldArmy.getAlivePieces())
		 {	 
			if(p.toString()=="F")
			{
				f5 = ProtectFlag(p);
				f2 = ThreatFlag(p);
				flag_path= FlagHasPath(p);
				f4=PiecesInPath(p);
				if(flag_path)
					{f1=50;}
			} 
				goldArmyScore += p.getScore();
		 }	
		
		for(Piece p: silverArmy.getAlivePieces())
		{
			silverArmyScore += p.getScore();
		}
		
		f3 = goldArmyScore - silverArmyScore;
		f6 = SilverOnCorner();
		

		
		if(side == Side.GOLD)
		{
			return ((f1*wg1)+(f2*wg2) + (f3*wg3)+(f4*wg4) + (f5 * wg5) + (f6 * wg6))/25;
		}
		else
			return ((f1*sg1)+(f2*sg2) + (f3*sg3) +(f4*sg4)+ (f5 * sg5) + (f6 * sg6))/25;
				
		
	}
	
	public void PrintFeatures (Side side)
	{
		//Weights for gold and weights for silver
				int goldArmyScore=0,silverArmyScore=0;
				int f1=0,f2=0,f4=0,f3=0,f5=0,f6=0;
				boolean flag_path;
				//weights:
				int wg1=20,wg2=-15,wg3=10,wg4=-10,wg5=20,wg6=0;
				int sg1=-20,sg2=15,sg3=10,sg4=0,sg5=-10,sg6=20;
				
				for(Piece p : goldArmy.getAlivePieces())
				 {	 
					if(p.toString()=="F")
					{
						f5 = ProtectFlag(p);
						f2 = ThreatFlag(p);
						flag_path= FlagHasPath(p);
						f4=PiecesInPath(p);
						if(flag_path)
							{f1=50;}
					} 
						goldArmyScore += p.getScore();
				 }	
				
				for(Piece p: silverArmy.getAlivePieces())
				{
					silverArmyScore += p.getScore();
				}
				
				f3 = goldArmyScore - silverArmyScore;
				f6 = SilverOnCorner();
				
		
		System.out.println("Flag Protected : "+f5+" , Flag Threatened :"+f2+" , Substract g - s : "+f3+ " , Silver On Corner :"+f6);
	
		if(side == Side.GOLD)
		{
			System.out.println("Gold :"+side+"Score : "+((f1*wg1)+(f2*wg2) + (f3*wg3)+(f4*wg4) + (f5 * wg5) + (f6 * wg6)) /35); 
		}
		else 
			System.out.println("Silver :"+side+"Score :"+((f1*sg1)+(f2*sg2) + (f3*sg3) +(f4*sg4)+(f5 * sg5) + (f6 * sg6)) /15); 
		
	}
	
	public Army getArmy(Side side)
	{
		return side == Side.GOLD ? goldArmy:silverArmy;
	}
	
	public Square getSquare(int row, int col)
	{
		return (row < 0 || row >= LENGTH || col<0 || col>=LENGTH)? null :squares[row][col];//just in case the square is out of bounds
	}

	public void display()
	{
		for(int r=0;r<Board.LENGTH;r++)
		{
			for(int c=0;c<Board.LENGTH;c++)
			{
				if(squares[r][c].getPiece() != null)
				{
					System.out.print(squares[r][c].getPiece());
				}
				else
				{
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	
	public void ToString()
	{
		for(int r=0;r<Board.LENGTH;r++)
		{
			for(int c=0;c<Board.LENGTH;c++)
			{
				System.out.print(this.getSquare(r, c).getPiece() != null ? this.getSquare(r, c).getPiece().toString() : "E");

			}
			System.out.println();
		} 
	}
	
	
	
	public int PiecesInPath(Piece p) 
	  {
		  
		int pieceSum=0;
		for(int i=p.getSquare().columns+1;i<=10;i++)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(p.getSquare().rows,i).getPiece()!=null)
				{
					pieceSum+=5;
				}
				
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().columns-1;i>=0;i--)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(p.getSquare().rows,i).getPiece()!=null)
				{
					pieceSum+=5;
				}
				
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().rows+1;i<=10;i++)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(i,p.getSquare().columns).getPiece()!=null)
				{
					pieceSum+=5;
				}
				
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().rows-1;i>=0;i--)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(i,p.getSquare().columns).getPiece()!=null)
				{
					pieceSum+=5;
				}
				
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		return pieceSum;
	  }
	
	
	
	
	  public boolean FlagHasPath(Piece p) 
	  {
		  
		boolean flag_has_path = false;
		for(int i=p.getSquare().columns+1;i<=10;i++)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(p.getSquare().rows,i).getPiece()!=null)
				{
					break;
				}
				if(i==10)
				{
					flag_has_path = true;
					return flag_has_path;
				}
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().columns-1;i>=0;i--)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(p.getSquare().rows,i).getPiece()!=null)
				{
					break;
				}
				if(i==0)
				{
					flag_has_path = true;
					return flag_has_path;
				}
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().rows+1;i<=10;i++)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(i,p.getSquare().columns).getPiece()!=null)
				{
					break;
				}
				if(i==10)
				{
					flag_has_path = true;
					return flag_has_path;
				}
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		
		for(int i=p.getSquare().rows-1;i>=0;i--)
		{
			try
			{
				if(p.getSquare().getAdjacentSquare(i,p.getSquare().columns).getPiece()!=null)
				{
					break;
				}
				if(i==0)
				{
					flag_has_path = true;
					return flag_has_path;
				}
			}
			catch(Exception e)
			{ e.getCause();}
			
		}
		return flag_has_path;
	  }
	 
	
	
	//Checks if silver 
	public int SilverOnCorner()
	{
		int silverCorner=0;
		for(Piece p: silverArmy.getAlivePieces())
		{
			if(p.getSquare().rows==0 && p.getSquare().columns==0)
			{silverCorner+=25;}
			if(p.getSquare().rows==0 && p.getSquare().columns==10)
			{silverCorner+=25;}
			if(p.getSquare().rows==10 && p.getSquare().columns==0)
			{silverCorner+=25;}
			if(p.getSquare().rows==10 && p.getSquare().columns==10)
			{silverCorner+=25;}
		}
		return silverCorner;
	}
	
	//Feature 5 that checks if flagship is protected by his army pieces
	public int ProtectFlag(Piece p )
	{
		int flag_protected = 0;
		
		for(int h = -1; h<2; h+=2)
		{
			for(int v = -1; v<2; v+=2)
			{
				try
				{	
					if(p.getSquare().getAdjacentSquare(h, v).getPiece().getSide()==Side.GOLD) // Flagship is covered
						{flag_protected += 25;}
				}
				catch(Exception e)
				{e.getCause();}
			}
		}
		 return flag_protected;
	}

	//F2 that checks if flagship is threatened by the opponent  army pieces
	public int ThreatFlag(Piece p)
	{
		int flag_threatened = 0;
		
		for(int h = -1; h<2; h+=2)
		{
			for(int v = -1; v<2; v+=2)
			{
				try
				{
					
					if(p.getSquare().getAdjacentSquare(h, v).getPiece().getSide()==Side.SILVER) // Flagship is covered

						{
							flag_threatened += 50;
							break;
						}
				}
				catch(Exception e)
				{e.getCause();}
			}
		}
		
		 return flag_threatened;
	}
	
	public  Square[][] getSquares() {
		return squares;
	}

	public  void setSquares(Square[][] squares) {
		this.squares = squares;
	}
	
}
