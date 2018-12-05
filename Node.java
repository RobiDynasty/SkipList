

public class Node {

	public int levels;
	public int key;
	public int data;
	public Node headerNext;
	public Node[] next;
	
	Node(int levels, int key, int data) {	// Constructor to make nodes after the header
		this.levels = levels;
		this.key = key;
		this.data = data;
		next = new Node[levels];
	}
	
	
	
	Node(int levels){	// Constructor to make header Nodes
		this.levels = levels;
		headerNext = null;
	}
	
	Node(){
		this.headerNext = null;
	}
	
	
	
	
}
