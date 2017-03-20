//Yousef Khan


package assignment3;
import java.util.*;
public class JSStack extends ArrayList{ // Used arraylist to implement stack 
	
	
	public JSStack(){ //Constructor
		super();
	}
	
	public void push(BlockType b){  //pushes b onto the front of the backing data structure
		super.add(b);
	}
	
	public BlockType pop() throws EmptyStackException{ //takes the BlockType that is on top of the backing data structure, saves that value,
		if(isEmpty()){									// removes that BlockType from the backing data structure, and returns that BlockType.
			throw new EmptyStackException("Stack cannot be empty"); //  If the stack was empty, throw an EmptyStackException.
			}
		BlockType x = (BlockType) super.get(super.size() - 1);
			super.remove(super.get(super.size() - 1));
			return x;
	}
	
	public BlockType peek(){  //takes the BlockType that is on top of the backing data structure, 
		BlockType peek;		//and returns that value to the caller. 
		if(super.size() > 0){ //Does NOT remove that BlockType from the backing data structure.
			peek = (BlockType) super.get(super.size() -1 );
			return peek;
		}
		return null;
	}
	
	public boolean isEmpty(){ //returns true if stack is empty, false otherwise.
		return super.isEmpty();
	}
	
	
	

}
