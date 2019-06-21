
public abstract class GameItem {    //this is a abstract class have to use this keyword.....
	
	private int row;
	private int column;
	
	
	private char gameItemCharacter;
	public GameItem(){}
	
	
	public GameItem(char c){
		
		gameItemCharacter =c;
		
	}
	
	public char getGameItemCharacter()
	{
		return gameItemCharacter;
	}
	
	
	
	public void setRowPosition(int x){
		
		this.row = x;
		
	}
	
	
	public void setColumnPosition(int y){
		
		this.column = y;
	}
	
	
	public void display(){}
	
	public void senseNearBy(){}

	
}