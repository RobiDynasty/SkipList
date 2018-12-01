

public class Node {

	public int level;
	public int key;
	public int data;
	public Node[] next;
	
	Node(int level, int key, int data) {
		this.level = level;
		this.key = key;
		this.data = data;
		Node[] next = new Node[level];
	}
	
	Node(int level){
		this.level = level;
		next = null;
	}
	
	
	
	
	
}
