//Yousef Khan

package assignment6;
import java.util.*;
import java.io.*;
public class SeawolvesDriver  {
	public static void main(String[] args){	// main method where program runs from
		Scanner input = new Scanner(System.in);
		String operation = "";
		String save = "";
		boolean programOver = false;
		boolean secondMenu = false;
		boolean miniMenu = false;
		System.out.println("Welcome to SBGetARoom, Stony Brook's premium room lookup system.");
		Campus c;
		Building tempBuilding = new Building();
		String tempName = "";
		try {
			FileInputStream fis = new FileInputStream("saveFile");	// creating the save file process and creating new file if there is none
			ObjectInputStream ois = new ObjectInputStream(fis);
			c = (Campus)ois.readObject();
			System.out.println("Saved file loaded...");
			ois.close();
		} catch (Exception e) {
			System.out.println("No save file found. Creating an empty campus.");
			c = new Campus();
		}
		
		while(programOver == false){	// while loop that controls when the menu and everything is displayed
			System.out.println("Main Menu:");
			System.out.println("\tA) Add a building");
			System.out.println("\tD) Delete a building");
			System.out.println("\tE) Edit a building");
			System.out.println("\tF) Find a room");
			System.out.println("\tS) Search for rooms");
			System.out.println("\tC) List all buildings on Campus");
			System.out.println("\tL) List building details");
			System.out.println("\tQ) Quit");
			System.out.println("\nPlease select an option: ");
			operation = input.nextLine(); //takes input from user to execute command
			while(operation.equals("")){
				operation = input.nextLine();
			}
			
			
			if(operation.equalsIgnoreCase("A")){ //creates a new building object
				System.out.println("Please enter a building name: ");
				String buildingName = input.nextLine();
				Building newBldg = new Building();
				c.addBuilding(buildingName, newBldg);
				System.out.println("Building " + buildingName + " added.");
			}
			
			
			if(operation.equalsIgnoreCase("D")){ //deletes a building object
				System.out.println("Please enter a building name: ");
				String buildingName = input.nextLine();
				c.removeBuilding(buildingName);
			}
			
			
			if(operation.equalsIgnoreCase("E")){ //allows users to edits rooms in a building or create new ones
				System.out.println("Please enter a building name: ");
				String buildingName = input.nextLine();
				tempBuilding = c.getBuilding(buildingName);
				tempName = buildingName;
				System.out.println("Building " + c.buildingName + " selected.");
				secondMenu = true; //starts the submenu for editing buildings
			}
			
			
			if(operation.equalsIgnoreCase("F")){ //finds a specific room and prints its details
				System.out.println("Please enter a room name: ");
				String[] roomName = input.nextLine().split(" ");
				String buildingName = roomName[0];
				String roomNum = roomName[1];
				System.out.println("Room Details: ");
				System.out.println("\tSeats: " + c.getBuilding(buildingName).getClassroom(Integer.parseInt(roomNum)).getNumSeats());
				if(c.getBuilding(buildingName).getClassroom(Integer.parseInt(roomNum)).isHasWhiteboard() == true){
					System.out.println("\tHas Whiteboard");
				}
				else{
					System.out.println("\tDoesn't have Whiteboard");
				}
				if(c.getBuilding(buildingName).getClassroom(Integer.parseInt(roomNum)).isHasChalkboard() == true){
					System.out.println("\tHas Chalkboard");
				}
				else{
					System.out.println("\tDoesn't have Chalkboard");
				}
				System.out.println("\tAV Amenities: " + c.getBuilding(buildingName).getClassroom(Integer.parseInt(roomNum)).printAVEquipmentList());
				
			}
			
			
			if(operation.equalsIgnoreCase("S")){ //opens the submenu for searching for a room based on certain specs
				miniMenu = true;
			}
			
			
			if(operation.equalsIgnoreCase("C")){ //lists the entire campus with all buildings and their rooms
				for(String buildingName : c.tempHash.keySet()){
					Building b = c.getBuilding(buildingName);
					System.out.print(buildingName + ": ");
					for(Integer room : b.tempHash.keySet()){
						System.out.print(room + ", ");
					}
					System.out.println();
				}
				System.out.println();
			}
			
			
			if(operation.equalsIgnoreCase("L")){ //describes all specs of a building and prints them out
				System.out.println("Please enter a building name: ");
				String buildingName = input.nextLine();
				tempBuilding = c.getBuilding(buildingName);
				int totalSeats = 0;
				int numRooms = 0;
				int white = 0;
				int chalk = 0;
				List<String> avList = new ArrayList<String>();
				System.out.println("Details: ");
				System.out.print("\tRooms: ");
				for(Integer roomN : tempBuilding.tempHash.keySet()){
					Classroom classroom = tempBuilding.getClassroom(roomN);
					System.out.print(roomN + ", ");
					numRooms++;
					totalSeats += classroom.getNumSeats();
					if(classroom.isHasWhiteboard() == true){
						white++;
					}
					if(classroom.isHasChalkboard() == true){
						chalk++;
					}
					for(String avl : classroom.getAVEquipmentList()){
						if(!avList.contains(avl)){
							avList.add(avl);
						}
					}
					
				}
				System.out.println();
				System.out.println("\tTotal seats: " + totalSeats);
				if(numRooms == 0){
					System.out.println("\t 0 % of rooms have whiteboards");
					System.out.println("\t 0 % of rooms have chalkboards");
				}
				else{
					System.out.println("\t" + ((double)white/(double)numRooms)*100 + " % of rooms have whiteboards");
					System.out.println("\t" + ((double)chalk/(double)numRooms)*100 + " % of rooms have chalkboards");
				}
				System.out.print("\tAV Equipment present: ");
				for(String s : avList) System.out.print(s + ", ");
				System.out.println();
				
			}
			
			
			if(operation.equalsIgnoreCase("Q")){ //quits the program and chooses whether to save the file or not
				System.out.println("\tS - Save");
				System.out.println("\tD - Don't Save");
				save = input.next();
				if(save.equalsIgnoreCase("S")){
					try {
						FileOutputStream file = new FileOutputStream("saveFile");
						ObjectOutputStream outStream = new ObjectOutputStream(file);
						outStream.writeObject(c);
						outStream.close();
					} catch (Exception e) {}
					System.out.println("Saving and closing...");
					programOver = true;
				}
				if(save.equalsIgnoreCase("D")){
					System.out.println("Closing without saving...");
					programOver = true;
				}
			}
			
			
			
			
			while(secondMenu == true){ //submenu for editing a building
				System.out.println("\tA) Add room");
				System.out.println("\tD) Delete room");
				System.out.println("\tE) Edit room");
				operation = input.nextLine();
				
				
				
				if(operation.equalsIgnoreCase("A")){ //adds a classroom to a building, setting all its specs
					System.out.println("Please enter a room number: ");
					int roomNumber = input.nextInt();
					Classroom newClssrm = new Classroom();
					tempBuilding.addClassroom(roomNumber, newClssrm);
					System.out.println("Please enter number of seats: ");
					int numSeats = input.nextInt();
					newClssrm.setNumSeats(numSeats);
					System.out.println("Please enter AV Equipment (separated by commas): ");
					String[] equipment = input.next().split(",");
					newClssrm.setAVEquipmentList(equipment);
					System.out.println("Does it have a whiteboard? (Y/n): ");
					input.nextLine();
					String whiteboard = input.nextLine();
					if(whiteboard.equalsIgnoreCase("Y")){
						newClssrm.setHasWhiteboard(true);
					}
					else if(whiteboard.equalsIgnoreCase("N")){
						newClssrm.setHasWhiteboard(false);
					}
					System.out.println("Does it have a chalkboard? (Y/n): ");
					String chalkboard = input.nextLine();
					if(chalkboard.equalsIgnoreCase("Y")){
						newClssrm.setHasChalkboard(true);
					}
					else if(chalkboard.equalsIgnoreCase("N")){
						newClssrm.setHasChalkboard(false);
					}
					System.out.println("Room " + c.buildingName + " " + roomNumber + " Added.");
					secondMenu = false;
				}
				
				
				
				if(operation.equalsIgnoreCase("D")){ //removes a classroom from a building
					System.out.println("Please enter room number: ");
					int roomNumber = input.nextInt();
					tempBuilding.removeClassroom(roomNumber);
					secondMenu = false;
				}
				
				
				
				
				if(operation.equalsIgnoreCase("E")){ //allows the specs of a room to be changed
					System.out.println("Please enter room number: ");
					int roomNum = input.nextInt();
					System.out.println("Old number of seats: " + tempBuilding.getClassroom(roomNum).getNumSeats());
					System.out.println("Please enter number of seats or press enter to skip: ");
					String numSeats = input.next();
					if(numSeats == ""){	
					}
					else{
						tempBuilding.getClassroom(roomNum).setNumSeats(Integer.parseInt(numSeats));
						System.out.println("Number of seats Updated");
					}
					System.out.println("Old AV Equipment: " + tempBuilding.getClassroom(roomNum).printAVEquipmentList());
					System.out.println("\nPlease enter new AV Equipment (separated by commas) or press enter to skip: ");
					if(input.nextLine() == ""){	
					}
					else{
						String[] newAV = input.nextLine().split(",");
						tempBuilding.getClassroom(roomNum).setAVEquipmentList(newAV);
						System.out.println("AV Equipment Updated");
					}
					System.out.println("Does it have a whiteboard?(Y/n) or press enter to skip: ");
					String whiteboard = input.nextLine();
					if(whiteboard == ""){	
					}
					else{
						if(whiteboard.equalsIgnoreCase("Y")){
							tempBuilding.getClassroom(roomNum).setHasWhiteboard(true);
							System.out.println("Whiteboard status Updated");
						}
						else if(whiteboard.equalsIgnoreCase("N")){
							tempBuilding.getClassroom(roomNum).setHasWhiteboard(false);
							System.out.println("Whiteboard status Updated");
						}
					}
					System.out.println("Does it have a chalkboard?(Y/n) or press enter to skip: ");
					String chalkboard = input.nextLine();
					if(chalkboard == ""){	
					}
					else{
						if(chalkboard.equalsIgnoreCase("Y")){
							tempBuilding.getClassroom(roomNum).setHasChalkboard(true);
							System.out.println("Chalkboard status Updated");
						}
						else if(chalkboard.equalsIgnoreCase("N")){
							tempBuilding.getClassroom(roomNum).setHasChalkboard(false);
							System.out.println("Chalkboard status Updated");
						}
					}
					c.getBuilding(tempName);
					tempBuilding.getClassroom(roomNum);
					System.out.println(c.buildingName + " " + tempBuilding.roomNumber + " updated.");
					secondMenu = false;
				}
				
				
			}
			
			while(miniMenu == true){ //submenu for searching for rooms that meet the users asked of specs
				System.out.println("\tOptions: ");
				System.out.println("\tC) Chalkboard");
				System.out.println("\tW) Whiteboard");
				System.out.println("\tA) AV Equipment");
				operation = input.nextLine();
				
				
				if(operation.equalsIgnoreCase("C")){ //prints all rooms with chalkboards
					for(String buildingName : c.tempHash.keySet()){
						Building b = c.getBuilding(buildingName);
						System.out.print(buildingName + ": ");
						for(Integer room : b.tempHash.keySet()){
							Classroom clss = b.getClassroom(room);
							if(clss.isHasChalkboard()){
								System.out.print(room + ", ");
							}
						}
						System.out.println();
					}
					System.out.println();
					
					miniMenu = false;
				}
				
				
				if(operation.equalsIgnoreCase("W")){ //prints all rooms with whiteboards
					for(String buildingName : c.tempHash.keySet()){
						Building b = c.getBuilding(buildingName);
						System.out.print(buildingName + ": ");
						for(Integer room : b.tempHash.keySet()){
							Classroom clss = b.getClassroom(room);
							if(clss.isHasWhiteboard()){
								System.out.print(room + ", ");
							}
						}
						System.out.println();
					}
					System.out.println();
					
					miniMenu = false;
				}
				
				
				if(operation.equalsIgnoreCase("A")){ //prints all rooms with a certain av device as said by user
					System.out.println("Please enter a keyword: ");
					String keyWord = input.nextLine();
					for(String buildingName : c.tempHash.keySet()){
						Building b = c.getBuilding(buildingName);
						System.out.print(buildingName + ": ");
						for(Integer room : b.tempHash.keySet()){
							Classroom clss = b.getClassroom(room);
							boolean hasKeyWord = false;
							for (int i = 0; i < clss.getAVEquipmentList().length; i++){
								if (clss.getAVEquipmentList()[i].equals(keyWord))
								hasKeyWord = true;
							}
							if(hasKeyWord == true){
								System.out.print(room + ", ");
							}
						}
						System.out.println();
					}
					System.out.println();
					
					miniMenu = false;
				}
			}
			
		}
		
		input.close();
		
	}
}
