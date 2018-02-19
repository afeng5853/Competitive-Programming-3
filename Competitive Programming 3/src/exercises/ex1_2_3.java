package exercises;

import java.time.Month;
import java.util.Arrays;
import java.util.Scanner;

public class ex1_2_3 {
	public static void main(String[] args) {
		//ex1();
		//ex2();
		//ex3();
		ex4();
	}
	
	public static void ex1() {
		Scanner s = new Scanner(System.in);
		Double d = s.nextDouble();
		System.out.printf("%7.3f%n", d);
	}
	
	public static void ex2() {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		System.out.printf("%." + n + "f%n", Math.PI);
	}
	
	public static void ex3() {
		// Example input : "9 August 2010"
		// Sakamoto's algorithm https://quizlet.com/270356986/sakamatos-algorithm-flash-cards/
		Scanner s = new Scanner(System.in);
		String date = s.nextLine();
		String[] dateA = date.split(" ");
		int m = Month.valueOf(dateA[1].toUpperCase()).getValue();
		int d = Integer.parseInt(dateA[0]);
		int y = Integer.parseInt(dateA[2]);
		int[] t = new int[] {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
		y -= m < 3 ? 1 : 0;
		System.out.println((y + y/4 - y/100 + y/400 + t[m-1] + d)%7);
	}
	
	public static void ex4() {
		Scanner s = new Scanner(System.in);
		int[] nums = Arrays.stream(s.nextLine().split(" ")).mapToInt(Integer::parseInt).distinct().sorted().toArray();
		for (int num : nums) {
			System.out.print(num + " ");
		}
	}
}
