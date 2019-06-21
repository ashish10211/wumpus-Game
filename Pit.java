
public class Pit extends GameItem {    //Its a child class inheriting the GameItem Class

	public Pit(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public void display(){
		System.out.print(this.getGameItemCharacter());
	}
	
	public void senseNearBy()
	{
		System.out.println("Breeze");
	}
	
	
}
