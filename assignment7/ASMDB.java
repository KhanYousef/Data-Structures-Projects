//Yousef Khan

package assignment7;
import java.util.*;

import big.data.DataInstantiationException;
import big.data.DataSource;
public class ASMDB {
	public static void main(String[] args){ //Driver class that implements methods and runs the program
		Scanner input = new Scanner(System.in);
        String title="";
        String operation = "";
        boolean programEnd = false;
        MovieManager filmManage = new MovieManager();
        
        
	   System.out.println("Welcome to A Smiley Movie Data Base, the happiest way to manage your DVDs.");
	   
	   while(programEnd == false){   
	        System.out.println("Menu:");
	        System.out.println("\tI) Import a Movie");
	        System.out.println("\tD) Delete a Movie");
	        System.out.println("\tA) Sort Actors");
	        System.out.println("\tM) Sort Movies");
	        System.out.println("\tQ) Quit");
	        System.out.println("Please select an option: ");
	        operation = input.nextLine();
	        
	        
	        
	        
	        if(operation.equalsIgnoreCase("I")){
		        try {
					System.out.print("Enter a movie title: ");
					String prefix = "http://www.omdbapi.com/?t=";
					title = input.nextLine();
					String postfix = "&y=&plot=short&r=xml";
					if(title.length()>0){
					    DataSource ds = DataSource.connectXML(prefix + title.replace(' ','+') + postfix);
					    ds.load();
					    String url = prefix + title.replace(' ','+') + postfix;
					    Movie newMovie = new Movie(url);
					    boolean dupe = false;
					    for(int i = 0; i < filmManage.getMovies().size(); i++){
					    	if(newMovie.checkDuplicate(filmManage.getMovies().get(i))){
					    		dupe = true;
					    	}
					    }
					    if(dupe){
					    	throw new InputMismatchException();
					    }
					    filmManage.getMovies().add(newMovie);
					    for(int i = 0; i < newMovie.getActList().size(); i++){
					    	if(filmManage.hasActors(newMovie.getActList().get(i))){
					    		filmManage.getActorFromName(newMovie.getActList().get(i).getName()).setCount(newMovie.getActList().get(i).getCount() + 1);
					    	}
					    	else{
					    		filmManage.getActors().add(newMovie.getActList().get(i));
					    	}
					    }
					    System.out.println("Added movie: " + ds.fetchString("movie/title"));
					}
					
				} catch (InputMismatchException e) {
					System.out.println("Movie already exists");
				}
		        catch(DataInstantiationException e){
		        	System.out.println("Movie is invalid");
		        }
		        
        	}
	        
	        
	        
	        if(operation.equalsIgnoreCase("D")){
	        	try {
					System.out.println("Please enter the movie title to be deleted: ");
					String deleteTitle = input.nextLine();
					String prefix = "http://www.omdbapi.com/?t=";
					String postfix = "&y=&plot=short&r=xml";
					Movie newMovie = null;
					if(title.length()>0){
					    DataSource ds = DataSource.connectXML(prefix + deleteTitle.replace(' ','+') + postfix);
					    ds.load();
					    String url = prefix + deleteTitle.replace(' ','+') + postfix;
					    newMovie = new Movie(url);
					    if(!filmManage.getMovies().contains(newMovie)){
					    	throw new NullPointerException();
					    }
					    filmManage.getMovies().remove(newMovie);
					}
					for(int i = 0; i < newMovie.getActList().size(); i++){
						for(int j = 0; j < filmManage.getActors().size(); j++){
							if(newMovie.getActList().get(i).getName().equals(filmManage.getActors().get(j).getName())){
								if(filmManage.getActors().get(j).getCount() == 1){
									filmManage.getActors().remove(filmManage.getActors().get(j));
								}
								else{
									filmManage.getActors().get(j).setCount(filmManage.getActors().get(j).getCount() - 1);
								}
							}
						}
						
					}
					System.out.println("Removed movie: " + newMovie.getTitle());
				} catch (Exception e) {
					System.out.println("Movie not found in manager");
				}
	        }
	        
	        
	        if(operation.equalsIgnoreCase("M")){
	        	System.out.println("Movie Sorting Options:");
	        	System.out.println("\tTA) Title Ascending (A-Z)");
	        	System.out.println("\tTD) Title Descending (Z-A)");
	        	System.out.println("\tYA) Year Ascending");
	        	System.out.println("\tYD) Year Descending");
	        	System.out.println("Please Select A Sort Method:");
	        	operation = input.nextLine();
	        	
	        	
	        	
	        	if(operation.equalsIgnoreCase("TA")){
	        		titleComparator tC = new titleComparator();
	        		Collections.sort(filmManage.getMovies(), tC);
	        		System.out.println("Title                           Year  Actors");
	        		System.out.println("------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getMovies().size(); i++){
	        			String actorsString = "";
	        			for(int j = 0; j < filmManage.getMovies().get(i).getActList().size(); j++){
	        				actorsString += filmManage.getMovies().get(i).getActList().get(j).getName();
	        				if(j != filmManage.getMovies().get(i).getActList().size() - 1){
	        					actorsString += ", ";
	        				}
	        			}
	        			System.out.printf("%-32s%-6d%-20s", filmManage.getMovies().get(i).getTitle(), 
	        					filmManage.getMovies().get(i).getYear(), actorsString);
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	
	        	if(operation.equalsIgnoreCase("TD")){
	        		titleComparator tC = new titleComparator();
	        		Collections.sort(filmManage.getMovies(), tC.reversed());
	        		System.out.println("Title                           Year  Actors");
	        		System.out.println("------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getMovies().size(); i++){
	        			String actorsString = "";
	        			for(int j = 0; j < filmManage.getMovies().get(i).getActList().size(); j++){
	        				actorsString += filmManage.getMovies().get(i).getActList().get(j).getName();
	        				if(j != filmManage.getMovies().get(i).getActList().size() - 1){
	        					actorsString += ", ";
	        				}
	        			}
	        			System.out.printf("%-32s%-6d%-20s", filmManage.getMovies().get(i).getTitle(), 
	        					filmManage.getMovies().get(i).getYear(), actorsString);
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	
	        	if(operation.equalsIgnoreCase("YA")){
	        		yearComparator yC = new yearComparator();
	        		Collections.sort(filmManage.getMovies(), yC);
	        		System.out.println("Title                           Year  Actors");
	        		System.out.println("------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getMovies().size(); i++){
	        			String actorsString = "";
	        			for(int j = 0; j < filmManage.getMovies().get(i).getActList().size(); j++){
	        				actorsString += filmManage.getMovies().get(i).getActList().get(j).getName();
	        				if(j != filmManage.getMovies().get(i).getActList().size() - 1){
	        					actorsString += ", ";
	        				}
	        			}
	        			System.out.printf("%-32s%-6d%-20s", filmManage.getMovies().get(i).getTitle(), 
	        					filmManage.getMovies().get(i).getYear(), actorsString);
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	
	        	if(operation.equalsIgnoreCase("YD")){
	        		yearComparator yC = new yearComparator();
	        		Collections.sort(filmManage.getMovies(), yC.reversed());
	        		System.out.println("Title                           Year  Actors");
	        		System.out.println("------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getMovies().size(); i++){
	        			String actorsString = "";
	        			for(int j = 0; j < filmManage.getMovies().get(i).getActList().size(); j++){
	        				actorsString += filmManage.getMovies().get(i).getActList().get(j).getName();
	        				if(j != filmManage.getMovies().get(i).getActList().size() - 1){
	        					actorsString += ", ";
	        				}
	        			}
	        			System.out.printf("%-32s%-6d%-20s", filmManage.getMovies().get(i).getTitle(), 
	        					filmManage.getMovies().get(i).getYear(), actorsString);
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        }
	        
	        
	        
	        if(operation.equalsIgnoreCase("A")){
	        	System.out.println("Actor Sorting Options:");
	        	System.out.println("\tAA) Alphabetically Ascending");
	        	System.out.println("\tAD) Alphabetically Descending");
	        	System.out.println("\tNA) By Number of Movies They Are In Ascending");
	        	System.out.println("\tND) By Number of Movies They Are In");
	        	System.out.println("Please Select A Sort Method:");
	        	operation = input.nextLine();
	        	
	        	
	        	
	        	if(operation.equalsIgnoreCase("AA")){
	        		nameComparator nC = new nameComparator();
	        		Collections.sort(filmManage.getActors(), nC);
	        		System.out.println("Actor                        Number of Movies");
	        		System.out.println("-------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getActors().size(); i++){
	        			System.out.printf("%-30s%-15d", filmManage.getActors().get(i).getName(), filmManage.getActors().get(i).getCount());
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	if(operation.equalsIgnoreCase("AD")){
	        		nameComparator nC = new nameComparator();
	        		Collections.sort(filmManage.getActors(), nC.reversed());
	        		System.out.println("Actor                        Number of Movies");
	        		System.out.println("-------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getActors().size(); i++){
	        			System.out.printf("%-30s%-15d", filmManage.getActors().get(i).getName(), filmManage.getActors().get(i).getCount());
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	if(operation.equalsIgnoreCase("NA")){
	        		countComparator cC = new countComparator();
	        		Collections.sort(filmManage.getActors(), cC);
	        		System.out.println("Actor                        Number of Movies");
	        		System.out.println("-------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getActors().size(); i++){
	        			System.out.printf("%-30s%-15d", filmManage.getActors().get(i).getName(), filmManage.getActors().get(i).getCount());
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        	
	        	if(operation.equalsIgnoreCase("ND")){
	        		countComparator cC= new countComparator();
	        		Collections.sort(filmManage.getActors(), cC.reversed());
	        		System.out.println("Actor                        Number of Movies");
	        		System.out.println("-------------------------------------------------------------------------------------------");
	        		for(int i = 0; i < filmManage.getActors().size(); i++){
	        			System.out.printf("%-30s%-15d", filmManage.getActors().get(i).getName(), filmManage.getActors().get(i).getCount());
	        			System.out.println();
	        		}
	        		System.out.println();
	        	}
	        	
	        }
	        
	        
	        
	        
	        if(operation.equalsIgnoreCase("Q")){
	        	System.out.println("That's all folks!");
	        	programEnd = true;
	        }
	        
	        
	        
	        
	        
        }
        
        
        
        
        
        
        
        
        input.close();
	}

}
