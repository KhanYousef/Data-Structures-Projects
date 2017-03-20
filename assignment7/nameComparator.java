//Yousef Khan

package assignment7;
import java.util.*;
public class nameComparator implements Comparator<Actor>{
	//@param 2 Actor objects
	//@returns integer indicating which movie is of higher value depending on name
	public int compare(Actor left, Actor right) {
		Actor a1 = (Actor) left;
        Actor a2 = (Actor) right;
        return (a1.getName().compareTo(a2.getName()));
	}
	
	

}
