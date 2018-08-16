package solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiPredicate;

public class Solution {
	
	private static final Scanner scanner = new Scanner(System.in);
	
	static int[] solve(int[] a) {
		Set<Integer> busSize = new TreeSet<>();
		int sum = Arrays.stream(a).sum();
		BiPredicate<Integer,Integer> predicate = (n,i) -> n%i==0;

		for (int i = 1; i * i <= sum; i++) {
			if (predicate.test(sum,i)) {
				if (canFill(a, i)) {
					busSize.add(i);
				}

				if (i * i != sum && canFill(a, sum / i)) {
					busSize.add(sum / i);

				}
			}
		}

		return busSize.stream().mapToInt(Integer::intValue).toArray();

	}

	static boolean canFill(int[] a, int size) {        
		int capacity = size;       
		for (int oneA : a) {
			if (capacity == oneA) {
				capacity = size;
			} else if (capacity < oneA) {
				capacity = 0;
			} else {
				capacity -= oneA;
			}
		}
		return capacity == size;
	}

	

	public static void main(String[] args) throws IOException {		
		System.out.println("Choose an array length:");
		int aCount = scanner.nextInt();

		System.out.println("Choose the array's values:");
		int[] a = new int[aCount];		

		for (int i = 0; i < aCount; i++) {
			a[i] = scanner.nextInt();
		}

		int[] result = solve(a);

		Arrays.stream(result).forEach(System.out::println);
		
	}
}