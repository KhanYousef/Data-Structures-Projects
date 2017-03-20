//Yousef Khan

package assignment2;

public class TripStop {
	private String Location;
	private int Distance;
	private String Activity;
	
	public TripStop(){
		Location = null;
		Distance = 0;
		Activity = null;
	}
	
	public TripStop(String newLoc, int newDis, String newAct){
		this.Location = newLoc;
		this.Distance = newDis;
		this.Activity = newAct;
	}
	
	public void setLocation(String location){
		this.Location = location;
	}
	
	public String getLocation(){
		return this.Location;
	}
	
	public void setDistance(int distance){
		try{
			if(distance < 0){
				throw new IllegalArgumentException("Distance cannot be negative");
			}
			else{
				this.Distance = distance;
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("Distance cannot be negative");
		}
	}
	
	public int getDistance(){
		return this.Distance;
	}
	
	public void setActivity(String activity){
		this.Activity = activity;
	}
	
	public String getActivity(){
		return this.Activity;
	}

}
