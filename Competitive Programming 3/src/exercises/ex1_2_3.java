package exercises;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ex1_2_3 {
	public static void main(String[] args) {
		//ex1(1.4732);
		//ex2(5);
		ex3("9 August 2010");
	}
	
	public static void ex1() {
		Scanner s = new Scanner(System.in);
		Double d = s.nextDouble();
		System.out.println(String.format("%7.3f", d));
		s.close();
	}
	
	public static void ex2(int n) {
		System.out.println(String.format("%." + n + "f", Math.PI));
	}
	
	public static void ex3(String date) {
		//Sakamoto's algorithm https://quizlet.com/270356986/sakamatos-algorithm-flash-cards/
		String[] dateA = date.split(" ");
		int m = Month.valueOf(dateA[1].toUpperCase()).getValue();
		int d = Integer.parseInt(dateA[0]);
		int y = Integer.parseInt(dateA[2]);
		int[] t = new int[] {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
		y -= m < 3 ? 1 : 0;
		System.out.println((y + y/4 - y/100 + y/400 + t[m-1] + d)%7);
	}
}
