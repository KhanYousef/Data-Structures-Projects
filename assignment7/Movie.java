//Yousef Khan

package assignment7;
import java.util.*;

import big.data.DataSource;
public class Movie {
	private String title; //Field that holds the title of the movie.
	private int year; //Field that holds the year the movie was released.
	private List<Actor> actList; //Field that holds the list of actors who were in the movie, represented as actors.
	public Movie(String title, int year){ //Overloaded constructor for creating a new movie with given details and an empty, non-null actors list.
		this.title = title;
		this.year = year;
		actList = new ArrayList<Actor>();
	}
	public Movie(String url){ // Overloaded constructor, loads (using BigData) a movie from the passed URL and makes a new Movie object from it.
		actList = new ArrayList<Actor>();
	    DataSource ds = DataSource.connectXML(url);
	    ds.load();
	    title = ds.fetchString("movie/title");
		String[] actName = ds.fetchString("movie/actors").split(", ");
		for(int i = 0; i < actName.length; i++){
		    String actorAlias = actName[i];
		    Actor a1 = new Actor(actorAlias);
		    actList.add(i, a1);
		}
		year = ds.fetchInt("movie/year");
	}
	
	//Getters and Setters for the above three member variables.
	//@param strng/int
	//@returns title/year
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Actor> getActList() {
		return actList;
	}
	public void setActList(List<Actor> actList) {
		this.actList = actList;
	}
	
	//checks if movies have the same act list or not
	//@param Movie object 
	//@returns boolean
	public boolean actListEquality(Movie miscMovie){
		if(miscMovie.getActList().size() != this.getActList().size()){
			return false;
		}
		for(int i = 0; i < this.getActList().size(); i++){
			if(!this.getActList().get(i).getName().equals(miscMovie.getActList().get(i).getName())){
				return false;
			}
		}
		return true;
	}
	//checks if duplicate movies exist in manager
	//@param Movie object
	//@returns boolean
	public boolean checkDuplicate(Movie m){
		if(!m.getTitle().equals(this.getTitle())){
			return false;
		}
		if(m.getYear() != this.getYear()){
			return false;
		}
		if(!this.actListEquality(m)){
			return false;
		}
		return true;
	
	}
	
	
	
	

}
