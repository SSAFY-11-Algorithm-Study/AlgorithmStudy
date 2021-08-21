package time4;

import java.util.Scanner;

public class BJ2559_numbers {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int max = Integer.MIN_VALUE;
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i = 0; i <= N-K; i++) {
			int sum = 0;
			for(int j = i; j < i+K; j++) {
				sum += arr[j];
			}
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}

}
