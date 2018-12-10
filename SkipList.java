import java.util.Random;
import java.util.*;

class Node {

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

public class SkipList {
	
	public static Node[] list;
	public static int currentLevels;
	private final static int maxLevels = 11;
	

	public static void initializeList() {
		list = new Node[maxLevels];
		for(int i = 0; i < maxLevels; i++) {
			Node n = new Node("header");
			list[i] = n;
			list[i].next[0] = new Node("null");
		}
		currentLevels = 1;
	}
	
	
	public static void insert(Node[] list, int searchKey, int newValue) {
		Node[] updates = new Node[maxLevels];
		Node pos = list[currentLevels-1];	// Position starts from the head of the list
		
		
		for(int i = currentLevels; i > 0; i--) {
			pos = list[i-1];
			while (!pos.next[0].isNull && pos.next[0].key < searchKey) {
				pos = pos.next[0];
			}
			updates[i - 1] = pos;
		}
		
		pos = pos.next[0];
		
		if(pos.key == searchKey) {
			pos.data = newValue;
		}
		
		else {
			int newLevel = randomLevel(); 	// produces a random level randomLevel();
			int listLevel = currentLevels;	// Number of levels currently on head of the list
			
			if(newLevel > listLevel) {
				for(int i = listLevel + 1; i <= newLevel; i++) {
					updates[i - 1] = list[i-1];
					
				}
				currentLevels = newLevel;
			}
			Node newNode = new Node(newLevel, searchKey, newValue);
			
			for (int i = 0; i < newLevel; i++) {
				newNode.next[i] = updates[i].next[0];
				updates[i].next[0] = newNode;
			}
		}
		
	}
	
	public static void delete(Node[] list, int searchKey) {
		Node[] updates = new Node[maxLevels];
		Node pos = list[currentLevels-1];
		
		for(int i = currentLevels; i > 0; i--) {
			pos = list[i-1];
			while (!pos.next[0].isNull && pos.next[0].key < searchKey) {
				pos = pos.next[0];
			}
			updates[i - 1] = pos;
		}
		
		pos = pos.next[0];
		
		if(pos.key == searchKey) {
			for(int i = 0; i < currentLevels; i++) {
				if(updates[i].next[0] != pos) {
					break;
				}
				updates[i].next[0] = pos.next[0];
			}
			while(currentLevels > 1 && list[currentLevels-1].next[0].isNull) {
				currentLevels = currentLevels - 1;
			}
		}
	}
	
	public static int search(Node[] list, int searchKey) {
		Node pos = list[currentLevels-1];
		
		for(int i = currentLevels; i > 0; i--) {
			pos = list[i-1];
			while (!pos.next[0].isNull && pos.next[0].key < searchKey) {
				pos = pos.next[0];
			}
		}
		if(pos.next[0].key == searchKey) {
			System.out.println(pos.next[0].data);
			return pos.next[0].data;
		}
		else {
			System.out.println(0);
			return 0;
		}
	}
	
	public static int getFromStart(int num) {
		
		Node pos = list[0];
		
		for(int i = 0; i < num; i++) {
			pos = pos.next[0];
		}
		return pos.key;
	}
	
	public static int randomLevel() {
		int newLevel = 1;
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		
		while(randNum == 0) {
			newLevel = newLevel + 1;
			randNum = rand.nextInt(2);
		}
		
		return Math.min(newLevel, maxLevels);
	}
	
	
	public static void print() {
		

		Node pos = list[0].next[0];
		String str = "(";
		
		while(!pos.isNull) {
			if(pos.next[0].isNull) {
				str += pos;
			}
			else {
				str += pos + ",";
			}
			pos = pos.next[0];
		}
		str += ")";
		System.out.println(str);
		
		return;
		
		
	}
	
	public static void main(String[] args) {
		
		initializeList();
		Scanner in = new Scanner(System.in);
		String cmd = in.nextLine().toLowerCase();
		
		while(!cmd.equals("quit")){
			String[] func = cmd.split("\\s+");
			if(func[0].equals("add")) {
				insert(list, Integer.parseInt(func[1]), Integer.parseInt(func[2]));
			}
			
			else if(func[0].equals("search")) {
				search(list, Integer.parseInt(func[1]));
			}
			
			else if(func[0].equals("delete")) {
				delete(list, Integer.parseInt(func[1]));
			}
			
			else if(func[0].equals("print")) {
				print();
			}
			cmd = in.nextLine();
		}
		in.close();
		return;
		
	}
	
}
