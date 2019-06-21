
public class Wumpus extends GameItem {
	
	public Wumpus(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	public void display(){
		System.out.print(this.getGameItemCharacter());
	}
	
	
	public void senseNearBy()
	{
		System.out.println("Vile Smell");
	}

}
