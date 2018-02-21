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
import java.util.Stack;

public class ex1_2_3 {
	public static void main(String[] args) throws IOException {
		//ex1();
		//ex2();
		//ex3();
		//ex4();
		//ex5();
		//ex6();
		//ex7();
		//ex8();
		//ex9();
		ex10();
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
	
	private static void swap(char[] str, int i, int j) {
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	private static void swap(int[] str, int i, int j) {
		int temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	private static void printPermutations(char[] str, int n) {
		if (n == 1) {
			System.out.println(Arrays.toString(str));
		} else {
			for (int i = 0; i < n-1; i++) {
				printPermutations(str, n-1);
				if (i % 2 == 0) {
					swap(str, i, n - 1);
				}
				else {
					swap(str, 0, n - 1);
				}
			}
			printPermutations(str, n-1);
		}
	}
    /*
     * Alternative to Heap's
	private static int getLargestMovable(int[] str, int[] dir) {
		int largest_moveable = -1;
	    for(int i = 0;i < str.length;i++) {
	        int moveto = i + dir[i];
	        if((moveto >= 0 && moveto < str.length) && (str[i] > str[moveto])) {
	            if(largest_moveable == -1 || str[largest_moveable] < str[i]) {
	                largest_moveable = i;
	            }
	        }
	    }
	    return largest_moveable;
	}
	
	private static void sjt(int[] str) {
		int movingIdx;
		int[] dir = new int[str.length];
		
		for (int i = 0; i < dir.length; i++) {
			dir[i] = -1;
		}
		System.out.println(Arrays.toString(str));
		while ((movingIdx = getLargestMovable(str, dir)) != -1 ) {
			for (int i = 0; i < dir.length; i++) {
				if (str[i] > str[movingIdx]) {
					dir[i] = dir[i] * -1;
				}
			}
			move(str, dir, movingIdx);
			System.out.println(Arrays.toString(str));
		}
	}
	
	private static void move(int[] a, int[] dirs, int moving) {
	    int tmp;
	    int moveto = moving + dirs[moving];

	    tmp = a[moving];
	    a[moving] = a[moveto];
	    a[moveto] = tmp;

	    tmp = dirs[moving];
	    dirs[moving] = dirs[moveto];
	    dirs[moveto] = tmp;
	}
     */
	
	public static void ex7() {
		char[] arr = new char[] {'1', '2', '3'};
		//sjt(arr);
		printPermutations(arr, arr.length);
	}
	
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
	
	public static void ex9() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = r.readLine().split("\\s");
		int currentBase = Integer.parseInt(inputs[0]);
		int toBase = Integer.parseInt(inputs[1]);
		String num = inputs[2];
		int currentNum = Integer.parseInt(num, currentBase);
		System.out.println(Long.toString(currentNum, toBase));
	}
	
	public static void ex10() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(r.readLine().replaceAll("\\b[a-z]\\d{2}\\b", "***"));
	}
	/*
	public static void ex11() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] ops = r.readLine().split("\\s");
		Stack<String> stk = new Stack<String>();
		for (String s : ops) {
			if (s.equals(")")) {
				too much work
			}
		}
	}
	*/
}
