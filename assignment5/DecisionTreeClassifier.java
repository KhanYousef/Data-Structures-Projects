//Yousef Khan

package assignment5;
import java.util.*;
public class DecisionTreeClassifier { // this class is the driver class and runs the program itself
	/*
	 This will drive the program and present a menu like shown below.
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String operation = "";
		boolean programEnd = false;
		boolean secondMenu = false;
		System.out.println("Welcome to the DecisionTree Classifier");
		TreeNavigator tn = new TreeNavigator();
		while(programEnd == false){
			System.out.println("Menu:");
			System.out.println("\tI)Import a tree from a file");
			System.out.println("\tE)Edit current tree");
			System.out.println("\tC)Classify a Description");				
			System.out.println("\tP)Show decision path for a Description");
			System.out.println("\tQ) Quit");
			System.out.println("Please select an option: ");
			operation = input.nextLine();
				
			if(operation.equalsIgnoreCase("E")){
				tn.resetCursor();
				System.out.println("Cursor is at root.");
				secondMenu = true;
			}
				
				
			if(operation.equalsIgnoreCase("I")){
				System.out.println("Please enter a filename:");
				String file = input.nextLine();
				tn = tn.buildTree(file);
					
			}
				
				
			if(operation.equalsIgnoreCase("C")){
				System.out.println("Please enter some text:");
				String keyText = input.nextLine();
				System.out.println(tn.classify(keyText));
					
			}
				
				
			if(operation.equalsIgnoreCase("P")){
				System.out.println("Please enter some text:");
				String description = input.nextLine();
				System.out.println(description);
					
			}
				
				
			if(operation.equalsIgnoreCase("Q")){
				System.out.println("Goodbye!");
				programEnd = true;
			}
			
			while(secondMenu == true){
				System.out.println("Please selection an Edit option: ");
				System.out.println("\t\tE)Edit Keywords");
				System.out.println("\t\tC)Add Children");
				System.out.println("\t\tD)Delete Children, and Make Leaf");
				System.out.println("\t\tN)Cursor to No Child");
				System.out.println("\t\tY)Cursor to Yes Child");
				System.out.println("\t\tR)Cursor to Root");
				System.out.println("\t\tM)Main Menu");
				operation = input.nextLine();
				
				if(operation.equalsIgnoreCase("E")){
					System.out.println("Please enter keywords for this node, separated by a comma:");
					String words = input.nextLine();
					tn.editCursor(words);
					System.out.println("Keywords updated to: " + words);
				}
				
				
				if(operation.equalsIgnoreCase("C")){
					System.out.println("Please enter terminal text for the no leaf:");
					String tempNo = input.nextLine();
					String[] noTemp = new String[1];
					noTemp[0] = tempNo;
					TreeNode newNodeLeft = new TreeNode();
					tn.getCursor().setLeft(newNodeLeft);
					tn.getCursor().getLeft().setKeywords(noTemp);
					System.out.println("Please enter terminal text for the yes leaf:");
					String tempYes = input.nextLine();
					String[] yesTemp = new String[1];
					yesTemp[0] = tempYes;
					TreeNode newNodeRight = new TreeNode();
					tn.getCursor().setRight(newNodeRight);
					tn.getCursor().getRight().setKeywords(yesTemp);
					System.out.println("Children are: yes - '" + tempYes + "' and no - '" + tempNo + "'");
					
				}
				
				
				if(operation.equalsIgnoreCase("D")){
					System.out.println("Please enter a terminal text for this node: ");
					String terminalText = input.nextLine();
					tn.editCursor(terminalText);
					tn.getCursor().setLeft(null);
					tn.getCursor().setRight(null);
					System.out.println("Current node is leaf. Text is: '" + terminalText + "'.");
					
				}
				
				
				if(operation.equalsIgnoreCase("N")){
					tn.cursorLeft();
					System.out.println("Cursor moved. Cursor is at leaf, message is " + tn.getCursor().getKeywords()[0]);
					
				}
				
				
				if(operation.equalsIgnoreCase("Y")){
					tn.cursorRight();
					System.out.println("Cursor moved. Cursor is at leaf, message is " + tn.getCursor().getKeywords()[0]);
					
				}
				
				
				if(operation.equalsIgnoreCase("R")){
					tn.resetCursor();
					System.out.println("Cursor moved. Cursor is at root.");
					
				}
				
				
				if(operation.equalsIgnoreCase("M")){
					secondMenu = false;
				}
				
			}
			
			
		}
		
		
		
		
		
		
		
	}

}
