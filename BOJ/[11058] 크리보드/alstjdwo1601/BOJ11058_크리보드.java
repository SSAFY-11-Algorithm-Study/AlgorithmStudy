package week10;

import java.util.Scanner;

public class BOJ11058_크리보드 {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);

		int N = sc.nextInt();
		long [] dp = new long[N+1];  // long으로 해야되는거 참고했음

		for(int i = 1 ; i <=N ; i++) {
			dp[i] = i;
		}
		for(int i = 1 ; i <= N; i ++) {
			if(i<=6) {
				//기본적인 A 출력
				dp[i] = dp[i-1] + 1;
			}
			else {
				for(int j = 2 ; j < 5 ; j++) {
					dp[i] = Math.max(dp[i], dp[i-(j+1)]*j);
				}
			}
		}

		System.out.println(dp[N]);
	}

}
