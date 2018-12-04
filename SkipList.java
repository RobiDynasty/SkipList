import java.util.ArrayList;
import java.util.Random;

public class SkipList {
	
	public static ArrayList<Node> list;
	final static int maxLevel = 6;
	

	public static void initializeList() {
		list = new ArrayList<Node>();
		Node n = new Node(1);
		list.add(n);
		list.get(0).next = null;
		System.out.println(list.get(0).levels);
	}
	
	
	public static void insert(ArrayList<Node> list, int searchKey, int newValue) {
		Node[] updates = new Node[maxLevel];
		Node pos = list.get(0);	// Position starts from the head of the list.
		
		for(int i = pos.levels; i >= 1; i--) {
			while(pos.next[i].key < searchKey || pos.next == null) {
				pos = pos.next[i];
			}
			updates[i] = pos;
		}
		
		if(pos.key == searchKey) {
			pos.data = newValue;
		}
		
		else {
			int newLevel = randomLevel();
			int listLevel = list.get(0).levels;
			
			if(newLevel > listLevel) {
				for(int i = listLevel; i < newLevel; i++) {
					updates[i] = list.get(0);
				}
				list.get(0).levels = newLevel;
			}
			Node n = new Node(newLevel, searchKey, newValue);
			
			for (int i = 1; i <= newLevel; i++) {
				n.next[i].next = updates[i].next;
				updates[i] = n;
			}
		}
		
		
		
	}
	
	public static int randomLevel() {
		int newLevel = 1;
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		System.out.println(randNum + " first randNum");
		
		while(randNum == 0) {
			newLevel = newLevel + 1;
			randNum = rand.nextInt(2);
		}
		
		return Math.min(newLevel, 5);
	}
	
	public static void main(String[] args) {
		initializeList();
		System.out.println(list.get(0).next);
		
	}
	
}
