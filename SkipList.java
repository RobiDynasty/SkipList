import java.util.ArrayList;
import java.util.Random;

public class SkipList {
	
	public static ArrayList<Node> list;
	private final static int maxLevels = 6;
	public static int currentLevels;
	

	public static void initializeList() {
		list = new ArrayList<Node>();
		Node n = new Node();
		list.add(n);
		currentLevels = list.size();
		//System.out.println(list.get(0));
		//System.out.println(list.get(0).levels);
	}
	
	public static Node getHead(ArrayList<Node> list) {
		return list.get(0);
	}
	
	
	
	public static void insert(ArrayList<Node> list, int searchKey, int newValue) {
		Node[] updates = new Node[maxLevels];
		Node pos = getHead(list);	// Position starts from the head of the list
		
		System.out.println("pos =" + (pos.headerNext == null));
		
		for(int i = list.size(); i > 0; i--) {
			if(pos.headerNext == null) {
				updates[i-1] = pos;
			}
			else {
				while(pos.headerNext == null) {
					pos = pos.next[i-1];
				}
				updates[i-1] = pos;
			}
		}
		
		if(pos.key == searchKey) {
			pos.data = newValue;
		}
		
		else {
			int newLevel = 1; 	// produces a random level randomLevel();
			int listLevel = list.size();	// Number of levels currently on head of the list
			System.out.println("new level " + newLevel);
			System.out.println("updates[0] " + updates[0].next);
			
			if(newLevel > listLevel) {
				for(int i = listLevel; i <= newLevel; i++) {
					Node n = new Node(listLevel);
					list.add(n);
					updates[i - 1] = list.get(listLevel).headerNext;
				}
				newLevel = list.size();
			}
			Node newNode = new Node(newLevel, searchKey, newValue);
			
			for (int i = 0; i < newLevel; i++) {
				newNode.next[i] = updates[i].headerNext;
				updates[i].headerNext = newNode;
			}
		}
		
		
		
	}
	
	public static int randomLevel() {
		int newLevel = 1;
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		// System.out.println(randNum + " first randNum");
		
		while(randNum == 0) {
			newLevel = newLevel + 1;
			randNum = rand.nextInt(2);
		}
		
		return Math.min(newLevel, maxLevels);
	}
	
	public static void main(String[] args) {
		initializeList();
		System.out.println(list.get(0).headerNext);
		insert(list, 3, 4);
		System.out.println(list.get(0).headerNext.key);
		//System.out.println(list.get(0).next)
		//System.out.println(randomLevel());
		
	}
	
}
