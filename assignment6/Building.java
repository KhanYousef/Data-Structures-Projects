//Yousef Khan

package assignment6;
import java.util.*;
import java.io.*;
public class Building implements Serializable{
	int roomNumber; //references the classroom the user is trying to get, as an int
	HashMap<Integer, Classroom> tempHash = new HashMap<Integer, Classroom>(); // the HashMap object
	
//	This method adds a Classroom into the Building using the specified room number as the key.
//	Throws an IllegalArgumentException if the given roomNumber = null or if the room number is already in the Building.
//	@param roomNumber and classroom 
	public void addClassroom(int roomNumber, Classroom classroom){
		try {
			if(tempHash.containsKey(roomNumber) || roomNumber == 0){
				throw new IllegalArgumentException();
			}
			else{
			tempHash.put(roomNumber, classroom);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Classroom name already exists or is not valid");
		}
	}
	
	
//	Retrieves the Classroom from the table having the indicated room number. If the requested room number does not exist in the Building, return null.
//	@param roomNumber
//	@return (Classroom)tempHash.get(roomNumber)
	public Classroom getClassroom(int roomNumber){
		this.roomNumber = roomNumber;
		if(!tempHash.containsKey(roomNumber)){
			return null;
		}
		else{
			return (Classroom) tempHash.get(roomNumber);
		}
	}
	
	
//	This method removes a classroom from the Building.
//	Throws an IllegalArgumentException if the given roomNumber = null or if it doesn't exist in the building
// @param roomNumber
	public void removeClassroom(int roomNumber){
		try {
			if(tempHash.containsKey(roomNumber)){
				tempHash.remove(roomNumber);
				System.out.println("Room " + roomNumber + " deleted.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Specified building does not exist");
		}
	}

}
