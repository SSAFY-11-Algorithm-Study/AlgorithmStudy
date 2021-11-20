// https://mizzo-dev.tistory.com/entry/baekjoon2133 바탕으로 풀었습니다

import java.util.Scanner;

public class BOJ2133 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 2];
		dp[0] = 1;
		dp[2] = 3;
		if (N % 2 == 1) {
			System.out.println(0);
		} else {
			for (int i = 4; i <= N; i = i + 2) {
				dp[i] = dp[i] + dp[i - 2] * 3;
				for (int j = 4; j <= i; j = j + 2) {
					dp[i] = dp[i] + dp[i - j] * 2;
				}
			}
			System.out.println(dp[N]);
		}
	}
}
