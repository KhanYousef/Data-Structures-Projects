//Yousef Khan

package assignment5;

public class TreeNode { // creates the individual tree nodes that go in the tree
	private String[] keywords; // This field holds the message only if it is a leaf, otherwise this is a list of words to trigger going down this path.
	private TreeNode left; // This field holds the left subtree
	private TreeNode right; //This field holds the right subtree
	
	public String[] getKeywords() { //returns the array of keywords
		return keywords;
	}
	public void setKeywords(String[] keywords) { //sets the array of keywords
		this.keywords = keywords;
	}
	public TreeNode getLeft() { // returns the left subtree
		return left;
	}
	public void setLeft(TreeNode left) { // sets the left subtree
		this.left = left;
	}
	public TreeNode getRight() { // returns the right subtree
		return right;
	}
	public void setRight(TreeNode right) { // sets the right subtree
		this.right = right;
	}
	
	public boolean isLeaf(){ //function that returns true if the node is a leaf and its left and right subtrees are null, otherwise false.
		if(left == null && right == null){		//Preconditions: This node is initialized
			return true;						//Postconditions: The tree remains unchanged
		}
		else{
			return false;
		}
	}
	

}
