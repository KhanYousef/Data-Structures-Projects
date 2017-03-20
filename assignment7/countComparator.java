//Yousef Khan

package assignment7;
import java.util.*;
public class countComparator implements Comparator<Actor>{
	//@param 2 Actor objects
	//@returns integer indicating which movie is of higher value depending on how many times they appear in a movie
	public int compare(Actor left, Actor right) {
		Actor a1 = (Actor) left;
        Actor a2 = (Actor) right;
        if (a1.getCount() == a2.getCount())
            return 0;
        else if (a1.getCount() > a2.getCount())
            return 1;
        else
            return -1;
	}

}
