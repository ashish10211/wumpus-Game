import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private int playerRow; //Player Row Position
	private int playerColumn; //Player Column Position
	
	final int TOP_CORNER =0;
	final int DOWN_CORNER =3;
	final int LEFT_CORNER = 0;
	final int RIGHT_CORNER = 3;
    int TARGET_CORNER;
	int FLAG;
	int GOLD_COLLECT;
	final int SIZE=4;
	final int LENGTH =4;
	public GameItem[][] board = new GameItem[SIZE][LENGTH];
	int TOTAL_GOLD;
	final int TOTAL_PIT = 3;
	final int TOTAL_WUMPUS = 1;
	Random random = new Random();
	int TOTAL_CG;
	final int POSITION_X= 0;
	final int POSITION_Y =1;
	
	final char GOLD_CHARACTER ='g';
	final char PIT_CHARACTER='p';
	final char WUMPUS_CHARACTER='W';
	final char CLEAR_GROUND_CHARACTER='.';
	final char PLAYER_CHARACTER = '*' ; 
	
	/**
	 * Setting Location the Player [Row and Column]
	 * Getting Location of the Player [Row and Column]
	 */
	public int getPlayerRow() {
		return playerRow;
	}

	public void setPlayerRow(int row) {
		this.playerRow = row;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	public void setPlayerColumn(int column) {
		this.playerColumn = column;
	}
	
	
/**
 * Method to Run the Game
 */
	public void runGame()
	{
		setBoard();
	    reRun();
	}

	
	/**
	 * Method to Print the Board
	 * Player is Displayed
	 */
	private void display(){
		for(int i=0;i<SIZE;i++)
		{
			  System.out.println("");
		      System.out.println("--------");
			for(int j=0;j<LENGTH;j++)
			{
				if(getPlayerRow()==i && getPlayerColumn()==j)
					System.out.print(PLAYER_CHARACTER);
				else
				board[i][j].display();
				System.out.print("|");
			}
		}
		System.out.println();
		System.out.println("--------");
	}

	
	/**
	 * Setting Gold in the Board using the Reference of GameItem board Array
	 * Setting Wumpus in the Board using the Reference of GameItem board Array
	 * Setting Pit in the Board using the Reference of GameItem board Array
	 * Setting Clear Ground items in the remaining items in the board
	 * Initializing the player postion to start with
	 */
	private void setBoard(){
		TOTAL_GOLD = goldGenerator();
		TOTAL_CG=cgGenerator();
		//Initializing the Gold Items
		for(int i=0;i<TOTAL_GOLD;i++)
		{
			int position[] = getPosition();
			
			board[position[POSITION_X]][position[POSITION_Y]] = new Gold(GOLD_CHARACTER);			
		}
		
		//Initializing Wumpus
		for(int i=0;i<TOTAL_WUMPUS;i++)
		{
			int position[] = getPosition();
			board[position[POSITION_X]][position[POSITION_Y]] = new Wumpus(WUMPUS_CHARACTER);					
		}
		

		//Initializing Pit Game Items
		for(int i=0;i<TOTAL_PIT;i++)
		{
			int position[] = getPosition();
			board[position[POSITION_X]][position[POSITION_Y]] = new Pit(PIT_CHARACTER);					
		}
		
		//Initializing Clear Ground Game Items
		assignCG();
		
		setInitialPosition(); //Set Initial position of the player
		
		
	}

/**
 * Assigning the ClearGrounds in the Board
 */
	private void assignCG(){
		
		for(int i=0;i<SIZE;i++)
		{	
			for(int j=0;j<LENGTH;j++){
				if(!(board[i][j] instanceof Gold)){
					if(!(board[i][j] instanceof Pit)){
						if(!(board[i][j] instanceof Wumpus)){
							if(!(board[i][j] instanceof ClearGround)){
								board[i][j] = new ClearGround(CLEAR_GROUND_CHARACTER);						
								
							}		
						}
					}
				}
					
			}	
					
		}
	}
	
	
	/**
	 *  Generating the random position to assign Game Items
	 *  @return array of position to assign the Game item in the board
	 *  
	 *  
	 */
	private int[] getPosition()
	{
		int randomRow = random.nextInt(4);
		int randomColumn = random.nextInt(4);
		boolean isItemSelected= false;
		
		while(!isItemSelected){
			randomRow = random.nextInt(4);
			randomColumn = random.nextInt(4);
			if(!(board[randomRow][randomColumn] instanceof Gold)){
				if(!(board[randomRow][randomColumn] instanceof Pit)){
					if(!(board[randomRow][randomColumn] instanceof Wumpus)){
						if(!(board[randomRow][randomColumn] instanceof ClearGround)){
							isItemSelected = true;						
							
						}		
					}
				}
			}
			
		}
		int position[] = {randomRow,randomColumn};
		return position;
		
	}
	
	
	/**
	 * Method to generate Random number of Gold
	 */
	private int goldGenerator(){
		int numberOfGold=random.nextInt(3)+1;
		return numberOfGold;		
	}

	/**
	 * Method to calculate the initial number of Clear Ground Game items
	 */
	private int cgGenerator(){
		int numberOfCG=12-TOTAL_GOLD;
		return numberOfCG;
	}
	
	/**
	 * Method to get the number of Gold
	 */
	public int getGold() {
		return TOTAL_GOLD;
	}

	
	/**
	 * Displaying Controls for the Player to Navigate.
	 */
	private void menu(){
		System.out.println("");
		System.out.println("===== WUMPUS GAME =====\n");
		System.out.println("[1] Move Player Left");
		System.out.println("[2] Move Player Right");
		System.out.println("[3] Move Player Up");
		System.out.println("[4] Move Player Down");
		System.out.println("[5] Quit");
		try{
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		if(a>0 && a<=5){
			switch(a){		
		case 1:
			moveLeft();
			break;
		case 2:
			moveRight();
			break;
		case 3:
			moveUp();
			break;
		case 4:
		   moveDown();
			break;
		case 5:
			gameEnd();
			break;
		default :
			System.out.println("\n ***** PLEASE ENTER VALID INPUT *****");
			runGame();
		}
		}
		else{
			System.out.println("\n***** PLEASE ENTER NUMBER BETWEEN 0 and 9 *****");
			reRun();
		}
		}
		catch(Exception e){
			System.out.println("\n***** ENTER A VALID INPUT *****");
			reRun();
		}
	}
	
	/**
	 * Method to Collect the Gold
	 * Displaying the number of Gold collected during Navigation
	 */
	  public void goldMethod(){
		FLAG++;
    	GOLD_COLLECT++;
    	goldCount(GOLD_COLLECT);
    	System.out.println("***** CONGRATS YOU JUST GOT GOLD *****");
      	reRun();
  }
  
  /**
   * Method called whenever the player gets a Gold or CLearGround
   */
    public void reRun(){
		display();
      	senseNearBy();
      	menu();
  }
  
  
  /**
   * Method to move the player left one position and check for the Game Item in the targeted position
   * 
   */
    public void moveLeft(){
	      if(getPlayerColumn()==LEFT_CORNER)
	  {
		TARGET_CORNER=RIGHT_CORNER;
	   }
	  else
	   {
		TARGET_CORNER =getPlayerColumn()-1;
	   }
	      //checking for the gold item and to replace the gold with Clear Ground
	    if(board[getPlayerRow()][TARGET_CORNER] instanceof Gold){
	    	board[getPlayerRow()][TARGET_CORNER] =new ClearGround(CLEAR_GROUND_CHARACTER);
	    	 updatePlayer(getPlayerRow(),TARGET_CORNER);
	    	goldMethod(); //increases the gold item after collecting it
	     
	    }
	    //checking for the PIT 
	    else if(board[getPlayerRow()][TARGET_CORNER] instanceof Pit){
	    	System.out.println("SORRY PLAYER FALLEN IN PIT\n");
	    	gameEnd();
	    }
	    
	    //checking for Wumpus
	    else if(board[getPlayerRow()][TARGET_CORNER] instanceof Wumpus){
	    	System.out.println("SORRY PLAYER EATEN BY WUMPUS\n");
	    	gameEnd();
	    	
	    }
         else if(board[getPlayerRow()][TARGET_CORNER] instanceof ClearGround){
	    	updatePlayer(getPlayerRow(),TARGET_CORNER);
	    	reRun();
	    }
   }
 	
    
    /**
     * Method to move the player right one position and check for the Game item in the targeted position
     */
     public void moveRight(){
    		if(getPlayerColumn()==RIGHT_CORNER)
    		{
    			TARGET_CORNER=LEFT_CORNER;
    		}
    		else
    		{
    			TARGET_CORNER =getPlayerColumn()+1;
    		}
	    if(board[getPlayerRow()][TARGET_CORNER] instanceof Gold){
	    	board[getPlayerRow()][TARGET_CORNER] =new ClearGround(CLEAR_GROUND_CHARACTER);
	    	updatePlayer(getPlayerRow(),TARGET_CORNER);
	        goldMethod();
	      	
	    }
	    else if(board[getPlayerRow()][TARGET_CORNER] instanceof Pit){
	    	System.out.println("SORRY PLAYER FALLEN IN PIT\n");
	    	gameEnd();
	    }
	    else if(board[getPlayerRow()][TARGET_CORNER] instanceof Wumpus){
	    	System.out.println("SORRY PLAYER EATEN BY WUMPUS\n");
	    	gameEnd();
	    	
	    }
         else if(board[getPlayerRow()][TARGET_CORNER] instanceof ClearGround){
	    	updatePlayer(getPlayerRow(),TARGET_CORNER);
	    	reRun();
	    }
   }

     
     /**
      * Method to move the player up one position and check for the Game item in the targeted position
      */
     public void moveUp(){
    	 if(getPlayerRow()==TOP_CORNER)
 		{
 			TARGET_CORNER=DOWN_CORNER;
 		}
 		else
 		{
 			TARGET_CORNER =getPlayerRow()-1;
 		}
	    if(board[TARGET_CORNER][getPlayerColumn()] instanceof Gold){
	    	board[TARGET_CORNER][getPlayerColumn()] =new ClearGround(CLEAR_GROUND_CHARACTER);
	    	updatePlayer(TARGET_CORNER,getPlayerColumn());
			goldMethod();
	    	
	      
	    }
	    else if(board[TARGET_CORNER][getPlayerColumn()] instanceof Pit){
	    	System.out.println("SORRY PLAYER FALLEN IN PIT\n");
	    	gameEnd();
	    }
	    else if(board[TARGET_CORNER][getPlayerColumn()] instanceof Wumpus){
	    	System.out.println("SORRY PLAYER EATEN BY WUMPUS\n");
	    	gameEnd();
	    	
	    }
         else if(board[TARGET_CORNER][getPlayerColumn()] instanceof ClearGround){
	    	updatePlayer(TARGET_CORNER,getPlayerColumn());
	    	reRun();
	    }
   }
     
     /**
      * Method to move the player down one position and check for the Game item in the targeted position
      */
     public void moveDown(){
    	 if(getPlayerRow()==DOWN_CORNER)
 		{
 			TARGET_CORNER=TOP_CORNER;
 		}
 		else
 		{
 			TARGET_CORNER =getPlayerRow()+1;
 		}
    	 if(board[TARGET_CORNER][getPlayerColumn()] instanceof Gold){
 	    	board[TARGET_CORNER][getPlayerColumn()] =new ClearGround(CLEAR_GROUND_CHARACTER);
 	     	updatePlayer(TARGET_CORNER,getPlayerColumn());
 	    	goldMethod();
 	  
 	      	
 	    }
 	    else if(board[TARGET_CORNER][getPlayerColumn()] instanceof Pit){
 	    	System.out.println("***** SORRY PLAYER FALLEN IN PIT *****");
 	    	gameEnd();
 	    }
 	    else if(board[TARGET_CORNER][getPlayerColumn()] instanceof Wumpus){
 	    	System.out.println("***** SORRY PLAYER EATEN BY WUMPUS *****");
 	    	gameEnd();
 	    	
 	    }
          else if(board[TARGET_CORNER][getPlayerColumn()] instanceof ClearGround){
 	    	updatePlayer(TARGET_CORNER,getPlayerColumn());
 	    	reRun();
 	    }
   }
    
     
     /**
      * update the position of the player for every movement
      * @param playerRow2 - Target Row
      * @param playerColumn2 - Target Column
      */
     private void updatePlayer(int playerRow2, int playerColumn2) {
		setPlayerRow(playerRow2);
		setPlayerColumn(playerColumn2);
	}

 
     /**
      * Method to end the game
      */
     public void gameEnd(){
	    if(FLAG == 0){   
		System.out.println("============ GAME END ============");
		System.exit(0); 
	   }
	   else{
		System.out.println("\n======= Gold Collected is "+GOLD_COLLECT+" ======");
		System.out.println("\n******* Congrats you played well *******");
		System.out.println("\n============ GAME END ============");
		System.exit(0); 
	  }
   }
    

    /**
     *  
     * To determine the complete Gold have been collected
     * @param gold - Number of collected gold
     */
    private void goldCount(int gold){
	int goldGen = getGold();
	if(gold == goldGen){
	gameEnd();
}
}
    
    /**
     * Method to set the initial position of the player
     */
	private void setInitialPosition(){
		int rowPosition =random.nextInt(4);
		int columnPosition = random.nextInt(4);
		while(!(board[rowPosition][columnPosition].getGameItemCharacter() == CLEAR_GROUND_CHARACTER))
		{
			rowPosition =random.nextInt(4);
			columnPosition = random.nextInt(4);
		}
		setPlayerRow(rowPosition);
		setPlayerColumn(columnPosition);
	}
	

	/**
	 * Method to sense the Nearby Game items
	 */
	private void senseNearBy(){
		System.out.println("Player is sensing now:");
		
		//Sensing one movement up
		if(getPlayerRow()==TOP_CORNER){
			
			board[DOWN_CORNER][getPlayerColumn()].senseNearBy();
		}
		else{ 
			
			board[getPlayerRow()-1][getPlayerColumn()].senseNearBy();
		
		}
		
		//sensing one movement down
		if(getPlayerRow()==DOWN_CORNER){
			board[TOP_CORNER][getPlayerColumn()].senseNearBy();
		}
		else{ 
			
			board[getPlayerRow()+1][getPlayerColumn()].senseNearBy();
		}
		
		
		//sensing one movement left
		if(getPlayerColumn()==LEFT_CORNER){
			board[getPlayerRow()][RIGHT_CORNER].senseNearBy();
		}
		else {
			
			board[getPlayerRow()][getPlayerColumn()-1].senseNearBy();
				
		}
				
				
		
		//sensing one movement right
		if(getPlayerColumn()==RIGHT_CORNER){
			board[getPlayerRow()][LEFT_CORNER].senseNearBy();
		}
		else{
			
			
			board[getPlayerRow()][getPlayerColumn()+1].senseNearBy();
			
		}
		
		
	}
	
}
