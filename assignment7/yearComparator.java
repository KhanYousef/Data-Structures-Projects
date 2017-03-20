//Yousef Khan

package assignment7;
import java.util.*;
public class yearComparator implements Comparator<Movie>{
	//@param 2 Movie objects
	//@returns integer indicating which movie is of higher value depending on year
	public int compare(Movie left, Movie right) {
		Movie m1 = (Movie) left;
        Movie m2 = (Movie) right;
        if (m1.getYear() == m2.getYear())
            return 0;
        else if (m1.getYear() > m2.getYear())
            return 1;
        else
            return -1;
	}

}
