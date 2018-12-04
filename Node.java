

public class Node {

	public int levels;
	public int key;
	public int data;
	public Node[] next;
	
	Node(int levels, int key, int data) {
		this.levels = levels;
		this.key = key;
		this.data = data;
		next = new Node[levels];
	}
	
	
	
	Node(int levels){
		this.levels = levels;
	}
	
	Node(){
		this.next = null;
	}
	
	
	
	
}
