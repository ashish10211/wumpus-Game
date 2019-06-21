
public class Gold extends GameItem {

		
	public Gold(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	public void display(){
		System.out.print(this.getGameItemCharacter());
	}

	
	public void senseNearBy()
	{
		System.out.println("Faint Glitter");
	}

}
