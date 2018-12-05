import java.util.Random;

public class randTest {
	
	public static int randomLevel(int maxLevels) {
		int newLevel = 1;
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		System.out.println(randNum + " first randNum");
		
		while(randNum == 0) {
			newLevel = newLevel + 1;
			randNum = rand.nextInt(2);
		}
		
		
		
		return Math.min(newLevel, maxLevels);
	}
	
	

}
