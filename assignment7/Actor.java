//Yousef Khan

package assignment7;

public class Actor {
	private String name; //Field that holds the name of the Actor.
	private int count = 0; //Field that represents the number of movies this actor is in.
	
	public Actor(String name){ //Overloaded constructor for creating a new Actor object with the specified name.
		this.name = name;
		count = 1;
	}
	//Getters and setters for the above two member fields.
	//@param string/int
	//@returns name/count variable

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	

}
