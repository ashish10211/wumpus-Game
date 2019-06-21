
public class ClearGround extends GameItem {   //Its a child class inheriting the GameItem Class
	
	public ClearGround(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	
	public void display(){            //to display Clear Ground character
		System.out.print(this.getGameItemCharacter());
	}
	
	
	public void senseNearBy()
	{
		
	}

}
