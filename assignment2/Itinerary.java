//Yousef Khan

package assignment2;

public class Itinerary {
	private TripStopNode head;
	private TripStopNode tail;
	private TripStopNode cursor;
	private int stopCount = 0;
	private int totalDistance = 0;
	
	public Itinerary(){
		head = null;
		tail = null;
		cursor = null;
	}
	
	public int getStopsCount(){
		return stopCount;
//		Returns the total number of TripStopNodes in the Itinerary.
	}
	
	public int getTotalDistance(){
		return totalDistance;
//		Returns the sum of distances over all TripStopNodes
	}
	
	public TripStop getCursorStop(){
//		Returns a reference to the TripStop wrapped by the TripStopNode that cursor points to.
		if(this.cursor == null){
			return null;
		}
		else{
			return cursor.getData();
		}
	}
	
	public void resetCursorToHead(){
//		Postconditions:
//			If head is not null, the cursor now references the first TripStopNode in this list.
//			If head is null, the cursor is set to null as well (there are no TripStop objects in this list).
		if(head == null){
			cursor = null;
		}
		else{
			cursor = head;
		}
	}
	
	public void resetCursorToTail(){
//		Postconditions:
//			If tail is not null, the cursor now references the last TripStopNode in this list.
//			If tail is null, the cursor is set to null as well (there are no TripStop objects in this list).
		
		
		if(tail == null){
			cursor = null;
		}
		else{
			cursor = tail;
		}
	}
	
	public void cursorForward(){
//		Moves the cursor to select the next TripStopNode in this list. If cursor == tail, throw an exception.
//			Throws:
//				EndOfItineraryException - Thrown if cursor is at the tail of the list.

		try{
			if(cursor == tail){
				throw new EndOfItineraryException("End of itinerary already reached");
			}
			else{
				cursor = cursor.getNext();
			}
		}
		catch(EndOfItineraryException e){
			System.out.println("End of itinerary already reached");
		}
	}
	
	
	public void cursorBackward(){
//		Moves the cursor to select the previous TripStopNode in this list. If cursor == head, 
//		throw an exception (this includes the case where cursor and head are both null).
//			Throws:
//				EndOfItineraryException- Thrown if cursor is at the head of the list.
		try{
			if(cursor == head){
				throw new EndOfItineraryException("Start of itinerary already reached");
			}
			else{
				cursor = cursor.getPrev();
			}
		}
		catch(EndOfItineraryException e){
			System.out.println("Start of itinerary already reached");
		}
	}
	
	
	public void insertBeforeCursor(TripStop newStop){
//		Preconditions:
//			newStop is not null.
//		Postconditions:
//			newStop has been wrapped in a new TripStopNode object
//			If cursor was previously not null, the newly created TripStopNode has been inserted into the list before the cursor.
//			If cursor was previously null, the newly created TripStopNode has been set as the new head of the list (as well as the tail).
//			The cursor now references the newly created TripStopNode .
		TripStopNode tsn = new TripStopNode(newStop);
		try{
			if(newStop == null){
				throw new IllegalArgumentException("New stop cannot be null");
			}
			else if(cursor == null){
				head = tsn;
				tail = tsn;
				cursor = tsn;
				stopCount++;
				totalDistance += newStop.getDistance();
			}
			else{
				tsn.setNext(cursor);
				tsn.setPrev(cursor.getPrev());
				cursor.getPrev().setNext(tsn);
				cursor.setPrev(tsn);
				cursor = tsn;
				stopCount++;
				totalDistance += newStop.getDistance();
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("New stop cannot be null");
		}
	}
	
	
	public void appendToTail(TripStop newStop){
//		Preconditions:
//			newStop is not null.
//			Postconditions:
//				newStop has been wrapped in a new TripStopNode object
//				If tail was previously not null, the newly created TripStopNode has been inserted into the list after the tail.
//				If tail was previously null, the newly created TripStopNode has been set as the new head of the list (as well as the tail).
//				The tail now references the newly created TripStopNode.
		TripStopNode tsn = new TripStopNode(newStop);
		TripStopNode cursorTemp;
		try{
			if(newStop == null){
				throw new IllegalArgumentException("New stop cannot be null");
			}
			else if(tail == null){
				head = tsn;
				tail = tsn;
				cursor = tsn;
				stopCount++;
				totalDistance += newStop.getDistance();
			}
			else{
				cursorTemp = cursor;
				cursor = tail;
				cursor.setNext(tsn);
				tsn.setPrev(tail);
				tail = tsn;
				cursor = cursorTemp;
				stopCount++;
				totalDistance += newStop.getDistance();
				
			}
			
		}
		catch(IllegalArgumentException e){
			System.out.println("New stop cannot be null");
		}
	}
	
	
	public TripStop removeCursor(){
//		Preconditions
//			cursor != null
//		Postconditons
//			The TripStopNode referenced by cursor has been removed from the list.
//			All other TripStopNodes in the list exist in the same order as before.
//			The cursor now references the next TripStopNode
//			Exceptions: If the cursor was originally the tail, the cursor will now reference the current tail.
//		Returns
//			The TripStop that was removed
//		Throws
//			EndOfListException if cursor is null.
		TripStopNode removed = new TripStopNode(cursor.getData());
		try{
			if(cursor == null){
				throw new EndOfListException("Cursor is already null");
			}
			else if(cursor == tail){
				cursor = null;
				cursor = tail.getPrev();
				tail = cursor;
				stopCount--;
				totalDistance -= (cursor.getData()).getDistance();
			}				
			else{
				cursor = cursor.getNext();
				cursor.setPrev(null);
				cursor.setPrev(cursor.getPrev().getPrev());
				cursor = cursor.getPrev();
				cursor.setNext(cursor.getNext().getNext());
				stopCount--;
				totalDistance -= (cursor.getData()).getDistance();
			}
		}
		catch(EndOfListException e){
			System.out.println("Cursor is already null");
		}
		return removed.getData();
	}
	
	
	public String toString(){
//		Converts the itinerary to a string and prints it
		String words = "";
		TripStopNode printNode = head;
		try {
			if(head == null){
				throw new IllegalArgumentException("List cannot be null");
			}
			else{
				while(printNode != null){
					words += String.format("%-40s %-40s %06d", printNode.getData().getLocation(),
							printNode.getData().getActivity(), printNode.getData().getDistance());
					words += "\n";
					printNode = printNode.getNext();
				}
			}
			return words;
		} catch (IllegalArgumentException e) {
			System.out.println("List cannot be null");
		}
		return "";
	}
	
	
	public void clearItinerary(){
		head = null;
		tail = null;
		cursor = null;
		stopCount = 0;
		totalDistance = 0;
	}
	
	

}
