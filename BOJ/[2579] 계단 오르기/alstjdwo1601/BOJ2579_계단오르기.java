package week11;

import java.util.*;

public class BOJ2579_계단오르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] stair = new int[n+2];
		int[] dp = new int[n+2];

	
		for(int i = 1; i <= n; i++)
			stair[i] = sc.nextInt();

		dp[1] = stair[1];
		dp[2] = stair[1] + stair[2];
		for(int i = 3; i <= n; i++) {
			//점프하는 경우 : stair[i] + stair[i-1] + dp[i-3]
			//점프 안하는 경우 : stair[i] + dp[i-2]
			dp[i] = Math.max(stair[i] + stair[i-1] + dp[i-3], stair[i] + dp[i-2]);
		}
		
		System.out.println(dp[n]);
	}
}
