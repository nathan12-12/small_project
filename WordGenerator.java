package hangman;
import java.util.*;

public class WordGenerator {
	
	public enum themes {BIBLE, FOOD, SPORTS};
	 
	static final String bibleSm [] = {"lamb","adam","eve","eden", "god","john","fig","dove","jesus","faith"};
	static final String bibleBg [] = {"cherubim", "tabernacle", "atonement", "temple", "revelation", "baptism", "genesis", "redemption", "hallelujah", "worship"};
	static final String foodSm [] = {"egg", "rice", "bean", "pear", "kiwi", "salt", "plum", "corn", "milk", "beef"};
	static final String foodBg [] = {"broccoli", "pineapple", "blueberry", "spaghetti", "avocado", "cucumber", "asparagus", "mushroom", "lasagna", "eggplant"};
	static final String sportsSm [] = {"golf", "surf", "judo", "yoga", "crew", "dive", "race", "ski", "pong", "dart"};
	static final String sportsBg [] = {"baseball", "football", "cricket", "hockey", "gymnastics", "badminton", "swimming", "volleyball", "wrestling", "basketball"};
	static String newWord(themes theme, int length) {
		//length: 0=short 1=long
		Random rand = new Random();
		int randIndex = rand.nextInt(10);
		switch(theme) {
		case themes.BIBLE:
			if (length==0)
				return bibleSm[randIndex];
			else 
				return bibleBg[randIndex];
		case themes.FOOD:
			if (length==0)
				return foodSm[randIndex];
			else 
				return foodBg[randIndex];
		case themes.SPORTS:
			if (length==0)
				return sportsSm[randIndex];
			else 
				return sportsBg[randIndex];
		default:
			System.out.println("invalid input for newWord method of class WordGenerator");
			return "error";
		}
	}
	
	
}
