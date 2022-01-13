// 블로그 보고 이해 못했는데 성재형 코드 보고 이해 
// 테스트 케이스는 맞는데 틀렸습니다... ?

package time23;

import java.util.Scanner;

public class BJ10844_쉬운계단수 {

	private static int  N;
	private static int[][] dp = new int[101][10];
	private static long answer;
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		//1 자리수는 1개
		for(int i = 1; i < 10; i ++) { //여기서 1로 시작해야함 0은 수가 계단수로 안 침. 여기서 자꾸 틀렸음.
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i ++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) {
					dp[i][j] = dp[i-1][1];
				} else if(j == 9) {
					dp[i][j] = dp[i-1][8];
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				}
			}
		}
		
		for(int i = 0; i < 10; i++) {
			answer += dp[N][i];
		}
		answer %= 1000000000;
		System.out.println(answer); 
		

	}

}
