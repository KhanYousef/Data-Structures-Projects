//Yousef Khan

package assignment7;
import java.util.*;
public class titleComparator implements Comparator<Movie>{
	//@param 2 Movie objects
	//@returns integer indicating which movie is of higher value depending on title
	public int compare(Movie left, Movie right) {
		Movie m1 = (Movie) left;
        Movie m2 = (Movie) right;
        return (m1.getTitle().compareTo(m2.getTitle()));
	}

}
