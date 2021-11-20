package week17;

import java.util.Scanner;
// https://yabmoons.tistory.com/536 여기 설명 되게 자세함
public class BOJ2133_타일채우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];


		if(N%2==1) {
			//홀수면 만들수가 없음
			System.out.println(0);
		}
		else {
			dp[0] = 1;
			dp[1] = 1;
			dp[2] = 3;

			for (int i = 4; i <= N; i += 2) { 
				dp[i] = dp[i - 2] * 3; 
				for (int j = i - 4; j >= 0; j -= 2) { 
					dp[i] += dp[j] * 2; 
				} 
			}


		}
		System.out.println(dp[N]);
	}

}
