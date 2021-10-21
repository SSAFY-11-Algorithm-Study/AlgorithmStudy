package week13;

import java.util.Scanner;


public class BOJ2293_동전1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int [] coins = new int[n];
		for(int i=0 ; i < n ; i ++ )
			coins[i] = sc.nextInt();
		
		//합계 담을 배열
		int [] dp = new int[k+1];
		
		dp[0] = 1;
		
		/*
		for(int i = 1 ; i <= k ; i ++) {
			for(int j = 0 ; j <n ; j++) {
				//10원을 만들려면 dp[9] + 1 , dp[8]+2 , dp[5] + 5 이니까 
				//코인 배열 돌면서 하나씩 뺀 값을 가져야함
				if(i-coins[j]>=0)
					dp[i] += dp[i - coins[j]];
			}
		}*/
		
		for(int i = 0; i < n; i++) {
			for(int j = coins[i]; j <= k; j++) { 
				dp[j] += dp[j - coins[i]];
			}
		}
		
		System.out.println(dp[k]);
	}

}
