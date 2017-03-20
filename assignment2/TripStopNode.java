//Yousef Khan

package assignment2;

public class TripStopNode {
	private TripStop data;
	private TripStopNode next;
	private TripStopNode prev;
	
	public TripStopNode(TripStop initData){
//		Preconditions:
//			initData is not null.
//			Postconditions:
//			This TripStopNode has been initialized to wrap the indicated TripStop, and prev and next have been set to null.
//			Throws: 
//			IllegalArgumentException 
//			Thrown if initData is null.
		try{
			if(initData == null){
				throw new IllegalArgumentException("Cannot be null value");
			}
			else{
				this.data = initData;
				this.next = null;
				this.prev = null;
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("Cannot be null value");
		}
	}
	
	
	public TripStop getData(){
//		Returns the reference to the data member variable of the list node.
		return this.data;
	}
	
	public void setData(TripStop newData){
//		newData is not null.
//		Throws:
//		IllegalArgumentException - Thrown if newData is null.

		try{
			if(newData == null){
				throw new IllegalArgumentException("Cannot be null value");
			}
			else{
				this.data = newData;
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("Cannot be null value");
		}
	}
	
	public TripStopNode getNext(){
//		Returns the reference to the next member variable of the list node. Can be null, means there's no next TripStopNode.

		return this.next;
	}
	
	
	public void setNext(TripStopNode newNext){
//		Updates the next member variable with a new TripStopNode reference.
		this.next = newNext;
	}
	
	public TripStopNode getPrev(){
//		Gets the reference to the prev member variable of the list node.
//		Returns:
//		The reference of the prev member variable.
		return this.prev;
	}
	
	public void setPrev(TripStopNode newPrev){
//		Updates the prev member variable with a new TripStopNode reference.
		this.prev = newPrev;
	}
	
	
	
	
}


