//Yousef Khan

package assignment2;
import java.util.*;
public class TripPlanner {
	public static void main(String[] args){
		System.out.println("Welcome to TripPlanner!  \n");
		Scanner input = new Scanner(System.in);
		String operation = " ";
		boolean current = true;
		
		
		
		while(!operation.equals("Q")){
			
			System.out.println("Menu: ");
			System.out.println("F-Cursor Forward");
			System.out.println("B-Cursor Backward");
			System.out.println("I-Insert before cursor");
			System.out.println("A-Append to tail");
			System.out.println("D-Delete and move cursor forward");
			System.out.println("H-Cursor to head");
			System.out.println("T-Cursor to tail");
			System.out.println("E-Edit Cursor");
			System.out.println("S-Switch itinerary");
			System.out.println("O-Insert cursor from other itinerary after cursor from this itinerary");
			System.out.println("R-Replace this itinerary with a copy of the other itinerary");
			System.out.println("P-Print current itinerary, including summary");
			System.out.println("C-Clear current itinerary");
			System.out.println("G-Get total distance");
			System.out.println("Q-Quit");
			
			Itinerary itin1 = new Itinerary();
			Itinerary itin2 = new Itinerary();
			
			operation = input.next().toUpperCase();
			
			if(operation.equals("F")){
				if(current == true){
					itin1.cursorForward();
					System.out.println("Cursor moved forward");
				}
				else{
					itin2.cursorForward();
					System.out.println("Cursor moved forward");
				}
			}
			
			
			
			else if(operation.equals("B")){
				if(current == true){
					itin1.cursorBackward();
					System.out.println("Cursor moved backward");
				}
				else{
					itin2.cursorBackward();
					System.out.println("Cursor moved backward");
				}
			}
			
			
			else if(operation.equals("I")){
				System.out.println("Enter location: ");
				String location = input.next();
				System.out.println("Enter Activity: ");
				String activity = input.next();
				System.out.println("Enter distance: ");
				int distance = input.nextInt();
				TripStop newStop = new TripStop(location, distance, activity);
				if(current == true){
					itin1.insertBeforeCursor(newStop);
					System.out.println("Added");
				}
				else{
					itin2.insertBeforeCursor(newStop);
					System.out.println("Added");
				}
			}
			
			
			
			else if(operation.equals("A")){
				System.out.println("Enter location: ");
				String location = input.next();
				System.out.println("Enter Activity: ");
				String activity = input.next();
				System.out.println("Enter distance: ");
				int distance = input.nextInt();
				TripStop newStop = new TripStop(location, distance, activity);
				if(current == true){
					itin1.appendToTail(newStop);
					System.out.println("Added");
				}
				else{
					itin2.appendToTail(newStop);
					System.out.println("Added");
				}
			}
			
			
			
			else if(operation.equals("D")){
				if(current == true){
					itin1.removeCursor();
					System.out.println("Deleted cursor");
				}
				else{
					itin2.removeCursor();
					System.out.println("Deleted cursor");
				}
			}
			
			
			
			else if(operation.equals("H")){
				if(current == true){
					itin1.resetCursorToHead();
					System.out.println("Cursor moved to head");
				}
				else{
					itin2.resetCursorToHead();
					System.out.println("Cursor moved to head");
				}
			}
			
			
			
			else if(operation.equals("T")){
				if(current == true){
					itin1.resetCursorToTail();
					System.out.println("Cursor moved to tail");
				}
				else{
					itin2.resetCursorToTail();
					System.out.println("Cursor moved to tail");
				}
			}
			
			
			
			else if(operation.equals("E")){
				
			}
			
			
			
			else if(operation.equals("S")){
				if(current == true){
					current = false;
				}
				else{
					current = true;
				}
			}
			
			else if(operation.equals("P")){
				if(current == true){
					System.out.println(itin1);
				}
				else{
					System.out.println(itin2);
				}
			}
			
			else if(operation.equals("G")){
				if(current == true){
					System.out.println(itin1.getTotalDistance());
					System.out.print("  miles \n");
				}
				else{
					System.out.println(itin2.getTotalDistance());
					System.out.print("  miles \n");
				}
			}
			
			
			
			
		}
	}

}
