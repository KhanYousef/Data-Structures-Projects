//Yousef Khan

package assignment3;
import java.util.*;
public class JavascriptFormatterRunner { // main method to allow user to read their own files
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		JavascriptFormatter jf = new JavascriptFormatter();
		
		System.out.println("Welcome to the Javascript Formatter.");
		System.out.println("Please enter a filename: ");
		String filename = input.nextLine();
		System.out.println("------ Properly formatted program ----------");
		System.out.println(jf.format(filename));
		System.out.println("--Thank you for making your code readable!---");
		
		input.close();
	}

}
