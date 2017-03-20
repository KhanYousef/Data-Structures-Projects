//Yousef Khan

package assignment3;
import java.io.*;
public class JavascriptFormatter {
	private JSStack Stack;  //the stack where you will push and pop parentheses and braces.
	private int indentLevel; //where you store the number of tab ('\t') characters to print before a line
	
	
	public String format(String input) { // this method takes the data from the file and formats it.
		File f = new File(input);
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			for(int i = 0; i < line.length(); i++){
				char checker = line.charAt(i);
				
				
				if(checker == 'f'){ //checks for presence of a for loop and adds to stack accordingly
					char checker2 = line.charAt(i + 1);
					char checker3 = line.charAt(i + 2);
					char checker4 = line.charAt(i + 3);
					if(checker2 == 'o' && checker3 == 'r' && checker4 == '('){
						Stack.push(BlockType.FOR);
					}
				}
				
				
				
				else if(checker == ';'){ // check for ; and adds newline if so
					int inc = i + 1;
					if(line.charAt(inc) == ' '){
						
					}
					else{
						String start = line.substring(0, inc);
						String finish = line.substring(inc, line.length());
						line = start + "\n" + finish;
					}
				}
				
				
				
				else if(checker == '{'){ //checks for { and special for loop conditions
					if(Stack.peek() == BlockType.FOR){
						Stack.pop();
					}
					Stack.push(BlockType.BRACE);
					int inc = i + 1;
					String newline = "\n"; // newlines and indents as needed
					String newtab = "\t";
					String start = line.substring(0, inc);
					String finish = line.substring(inc, line.length());
					start += newline;
					indentLevel++;
					for(int j = 0; j < indentLevel; j++){
						start += newtab;
					}
					line = start + finish;
				}
				
				
				
				
				else if(checker  == '('){ // pushes paren onto stack
					Stack.push(BlockType.PAREN);
				}
			
				
				
				else if(checker == ')'){
					if(Stack.isEmpty()){
						line += "\n  //ERROR: Extra closing parenthesis detected";
						i = line.length();
					}
					else if(!Stack.peek().equals('(')){ // removes paren from stack
						Stack.pop();
					}
					else{
						line += "\n  //ERROR: Closing parenthesis at incorrect position";
					i = line.length();
					}
				}
				
				
				else if(checker == '}'){ //checks for close brace and decrements indent
					if(Stack.isEmpty()){
						line += "\n  //ERROR: Extra closing brace detected";
						i = line.length();
					}
					else if(!(Stack.peek().equals('}'))){
						line += "\n  //ERROR: Closing brace at incorrect position";
						i = line.length();
					}
					Stack.pop();
					String newline = "\n";
					String newtab = "\t";
					String start = line.substring(0, i);
					String finish = line.substring(i, line.length());
					start += newline;
					indentLevel--;
					for(int j = 0; j < indentLevel; j++){
						start += newtab;
					}
					line = start + finish;
				}
			
			}
			return line;
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		} catch (IOException e) {
			System.out.println("An IO Exception was thrown");
		} catch (EmptyStackException e) {
			System.out.println("Stack cannot be empty");
		}
		
		while(!(Stack.isEmpty())){
			if(Stack.peek().equals('(')){
				line += "\n  //ERROR: Missing closed parenthesis detected";
			}
			else if(Stack.peek().equals('{')){
				line += "\n //ERROR: Missing closed brace detected";
			}
			else if(Stack.peek().equals(BlockType.FOR)){
				line += "\n //ERROR: Unfinished for loop detected";
			}
			try {
				Stack.pop();
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		return " ";
		
	}
	
	public JavascriptFormatter(){ //constructor. Initialize your stack.
		Stack = new JSStack();
	}
	
	

}
