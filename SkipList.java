import java.util.Random;

public class SkipList {
	
	public static Node[] list;
	public static int currentLevels;
	private final static int maxLevels = 6;
	

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
			System.out.println("new level " + newLevel);
			System.out.println("updates[0] " + updates[0].next);
			
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
			return pos.next[0].data;
		}
		else {
			return 0;
		}
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
	
	public static void main(String[] args) {
		initializeList();
		insert(list, 3, 4);
		System.out.println(list[0].next[0].key);
		System.out.println(list[0].next[0].data);
		System.out.println("current level is " + currentLevels);
		insert(list, 5, 5);
		//insert(list, 3, 5);
		insert(list, 1, 10);
		insert(list, 9, 5);
		System.out.println("current level is " + currentLevels);
		System.out.println(list[0].next[0].key);
		System.out.println(list[0].next[0].data);
		System.out.println(search(list, 9));
		delete(list, 1);
		delete(list, 3);
		System.out.println(list[0].next[0].key);
	}
	
}
