

public class Node {

	public int levels;
	public int key;
	public int data;
	public Node[] next;
	public boolean isNull;
	public boolean isHeader;
	
	Node(int levels, int key, int data) {	// Constructor to make nodes after the header
		this.levels = levels;
		this.key = key;
		this.data = data;
		next = new Node[levels];
		isHeader = false;
		isNull = false;
	}
	
	Node(String str){
		if(str.equals("null")){
			isNull = true;
			next = null;
		}
		else if(str.equals("header")) {
			isHeader = true;
			next = new Node[1];
		}
	}
	
	 @Override
	 public String toString() {
		 return "(" + key + "," + data + ")";
	 }
}
