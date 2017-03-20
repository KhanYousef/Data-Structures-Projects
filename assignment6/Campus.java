//Yousef Khan

package assignment6;
import java.util.*;
import java.io.*;
public class Campus implements Serializable{
	String buildingName; //references the building the user is trying to get, as a string
	HashMap <String, Building> tempHash = new HashMap<String, Building>(); // the HashMap object
	
//	This method adds a Building into the Campus using the specified building name as the key.
//	Throws an IllegalArgumentException if the given buildingName = null or if the building name is already in the Campus.
//	@param buildingName and building
	public void addBuilding(String buildingName, Building building){
		try {
			if(tempHash.containsKey(buildingName) || buildingName == null){
				throw new IllegalArgumentException();
			}
			else{
			tempHash.put(buildingName, building);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Building name already exists or is not valid");
		}
		
	}
	
	
//	Retrieves the Building from the table having the indicated building name. If the requested building name does not exist in the Campus, return null.
//	@param buildingName
//	@return (Building)tempHash.get(buildingName)
	public Building getBuilding(String buildingName){
		this.buildingName = buildingName;
		if(!tempHash.containsKey(buildingName)){
			return null;
		}
		else{
			return (Building) tempHash.get(buildingName);
		}
	}
	
	
//	This method removes a Building from the Campus.
//	Throws an IllegalArgumentException if the given buildingName = null or if the building name is not in the Campus.
//	@param buildingName
	public void removeBuilding(String buildingName){
		try {
			if(tempHash.containsKey(buildingName)){
				tempHash.remove(buildingName);
				System.out.println("Building " + buildingName + " removed.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Specified building does not exist");
		}
	}
	

}
