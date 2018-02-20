package exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ex1_2_3 {
	public static void main(String[] args) throws IOException {
		//ex1();
		//ex2();
		//ex3();
		//ex4();
		//ex5();
		//ex6();
		//ex7("", 0);
		ex8();
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
	
	public static void ex5() {
		// input as DD MM YYYY
		// sort by month, then day, then age
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(s.nextLine());
		int[][] birthdayContainer = new int[n][];
		for (int i = 0; i < n; i++) {
			String line = s.nextLine();
			int[] nums = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			birthdayContainer[i] = nums;
		}
		Arrays.sort(birthdayContainer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		Arrays.sort(birthdayContainer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		Arrays.sort(birthdayContainer, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int age1 = Period.between(LocalDate.of(o1[2], o1[1], o1[0]), LocalDate.now()).getYears();
				int age2 = Period.between(LocalDate.of(o2[2], o2[1], o2[0]), LocalDate.now()).getYears();
				return Integer.compare(age1, age2);
			}
		});
		for (int[] date : birthdayContainer) {
			System.out.println(date[0] + " " + date[1] + " " + date[2]);
		}
	}
	
	public static void ex6() throws IOException {
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		BitSet bm = new BitSet();
		Arrays.stream(s.readLine().split("\\s")).mapToInt(Integer::parseInt).forEach(e -> bm.set(e));
		System.out.println(bm.get(Integer.parseInt(s.readLine())));
	}
	
	/* Wrong, it said to generate all permutations of the first 10 letters, not combinations
	private static String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	public static String ex7(String hitherto, int length) {
		if (length == 10) {
			return hitherto;
		}
		for (String s : letters) {
			String res = ex7(hitherto + s, length + 1);
			if (res != null)
				System.out.println(res);
		}
		return null;
	}
	*/
	
	private static List<Set<Integer>> powerset(int[] nums) {
		int binaryRep = 1 << nums.length;
		List<Set<Integer>> powerset = new ArrayList<Set<Integer>>();
		for (int i = 0; i < binaryRep; i++) {
			Set<Integer> set = new HashSet<Integer>();
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) == 0) {
					set.add(nums[j]);
				}
			}
			powerset.add(set);
		}
		return powerset;
	}
	
	public static void ex8() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(r.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = i;
		}
		for (Set<Integer> set : powerset(nums)) {
			System.out.println(set);
		}
	}
}
