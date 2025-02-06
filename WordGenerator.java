package hangman;
import java.util.*;

public class WordGenerator {
	
	public enum themes {BIBLE, FOOD, SPORTS};
	 
	static final String bibleSm [] = {"lamb","adam","eve","eden", "god","john","fig","dove","son","faith"};
	static final String bibleBg [] = {"cherubim", "tabernacle", "atonement", "temple", "revelation", 
							"baptism", "genesis", "redemption", "hallelujah", "worship"};
	static final String foodSm []= {"f1","f2","f3","f4","f5","f6","f7","f8","f9","f10"};
	static final String foodBg []={"Longf1","Longf2","Longf3","Longf4","Longf5","Longf6","Longf7","Longf8","Longf9","Longf10"};
	static final String sportsSm []={"s1","s2","s3","s4","s5","s6","s7","s8","s9","s10"};
	static final String sportsBg []={"Longs1","Longs2","Longs3","Longs4","Longs5","Longs6","Longs7","Longs8","Longs9","Longs10"};
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
