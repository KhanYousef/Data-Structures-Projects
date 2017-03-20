//Yousef Khan

package assignment7;
import java.util.*;
public class MovieManager {
	private List<Movie> movies; //Field that holds all movies handled by this MovieManager.
	private List<Actor> actors; //Field that holds all actors handled by this MovieManager.
	
	public MovieManager(){	// Constructs a MovieManager object with an empty, non-null actors and movies list.
		movies = new ArrayList<Movie>();
		actors = new ArrayList<Actor>();
	}
	
	public List<Movie> getMovies() { //Returns the movies list held by this MovieManager.
		return movies;
	}

	public void getSortedMovies(Comparator<Movie> comp) { //Returns a sorted list of movies based on the given comparator
		Collections.sort(this.getMovies(), comp);
	}

	public List<Actor> getActors() { //Returns the actors list held by this MovieManager.
		return actors;
	}

	public void getSortedActors(Comparator<Actor> comp) { //Returns a sorted list of actors based on the given comparator
		Collections.sort(this.getActors(), comp);
	}
	
	//checks if actors are in the movie within a movie manager
	//@param Actor
	//@returns boolean
	public boolean hasActors(Actor a1){
		for(int i = 0; i < this.getActors().size(); i++){
			if(a1.getName().equals(this.getActors().get(i).getName())){
				return true;
			}
		}
		return false;
	}
	
	//@param String
	//@returns Actor if actor with that name is within a list
	public Actor getActorFromName(String name){
		for(int i = 0; i < this.getActors().size(); i++){
			if(name.equals(this.getActors().get(i).getName())){
				return this.getActors().get(i);
			}
		}
		return null;
	}
	
	
}
