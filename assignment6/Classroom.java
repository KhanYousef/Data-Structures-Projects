//Yousef Khan

package assignment6;

import java.io.*;

public class Classroom implements Serializable{
	private int numSeats; //This field holds the number of seats the classroom has.
	private String[] AVEquipmentList; //This field holds the names of the AV Equipment that are supported in the room.
	private boolean hasWhiteboard; //This field is true if the classroom has a white board, false otherwise.
	private boolean hasChalkboard; //This field is true if the classroom has a chalk board, false otherwise.
	
	
	public int getNumSeats() { //returns number of seats in a classroom @return numSeats
		return numSeats;
	}
	public void setNumSeats(int numSeats) { //sets number of seats in a classroom @param numSeats from user
		this.numSeats = numSeats;
	}
	public String[] getAVEquipmentList() { //returns AV Equipment in a classroom @return AVEquipmentList
		return AVEquipmentList;
	}
	public void setAVEquipmentList(String[] aVEquipmentList) { //sets AV Equipment in a classroom @param aVquipmentList
		AVEquipmentList = aVEquipmentList;
	}
	public boolean isHasWhiteboard() { // returns the value of the boolean hasWhiteboard @return hasWhiteboard
		return hasWhiteboard;
	}
	public void setHasWhiteboard(boolean hasWhiteboard) { // sets the value of the boolean hasWhiteboard @param hasWhiteboard 
		this.hasWhiteboard = hasWhiteboard;
	}
	public boolean isHasChalkboard() { // returns the value of the boolean hasChalkboard @return hasChalkboard
		return hasChalkboard;
	}
	public void setHasChalkboard(boolean hasChalkboard) { // sets the value of the boolean hasChalkboard @param hasChalkboard
		this.hasChalkboard = hasChalkboard;
	}
	
	public String printAVEquipmentList(){	// prints the String array AVEquipmentList @return list
		String list = "";
		for(int i = 0; i < AVEquipmentList.length; i++){
			if(i != AVEquipmentList.length - 1){
				list += AVEquipmentList[i] + ", ";
			}
			else{
			list += AVEquipmentList[i];
			}
		}
		return list;
	}
	
	
	


}
