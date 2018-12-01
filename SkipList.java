import java.util.LinkedList;
import java.util.Random;

public class SkipList {
	
	public static LinkedList<Node> list;

	public static void initializeList() {
		LinkedList<Node> list = new LinkedList<Node>();
		Node n = new Node(1);
		list.add(n);
		System.out.println(list.get(0).level);
	}
	
	public static void insert(LinkedList<Node> list, int searchKey, int newValue) {
		Node[] updates = new Node[6];
		Node x = list.get(0);
		
		for(int i = x.level; i >= 1; i--) {
			while(x.next[i].key < searchKey || x.next == null) {
				x = x.next[i];
			}
			updates[i] = x;
		}
		
		if(x.key == searchKey) {
			x.data = newValue;
		}
		
		else {
			int newLevel = randomLevel();
			int listLevel = list.get(0).level;
			
			if(newLevel > listLevel) {
				for(int i = listLevel; i < newLevel; i++) {
					updates[i] = list.get(0);
				}
				list.get(0).level = newLevel;
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
		System.out.println(list.size());
		//System.out.println(list.get(0).level);
	}
	
}
