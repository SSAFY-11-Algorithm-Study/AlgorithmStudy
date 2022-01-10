package week2;

import java.util.Scanner;

//문제 이해자체를 못해서 풀이 참고했음..
//https://jaemin8852.tistory.com/161
public class BOJ10844_쉬운계단수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//N의 길이를 가지는 수 중에서 '계단 수' 를 찾아야됨
		int N = sc.nextInt();
		
		//dp[n][i] : 길이가 n인 수의 마지막 수가 i인 경우
		//ex) dp[2][2] : 2로 끝나는 2자리수 -> 12 , 32 (02는 불가해서 42도 안됨)
		int [][] dp = new int [101][10];
		
		//길이가 1이면 1로 초기화 -> 0으로 시작하는 숫자가 없기때문 -> n=2부터 
		for(int i = 1 ; i < 10 ; i++)
			dp[1][i] = 1;
		
		for(int i = 2; i <= N ; i++) {
			for(int j = 0 ; j < 10 ; j ++) {
				
				//지금 길이 N인 수가 0으로 끝나면 N-1길이인 수의 마지막이 1인 경우에서만 가능(0이랑 9는 차이)
				if(j==0)
					dp[i][j] = dp[i-1][j+1]; 
				
				//지금 길이 N인 수가 9로 끝나려면 N-1길이인 수의 마지막이 8인 경우에만 가능
				else if(j==9)
					dp[i][j] = dp[i-1][j-1];
				
				//길이가 N인 수가 3으로 끝난다 치면 N-1인수가 2나 4로 끝나야함 
				else
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				
				dp[i][j] %= 1000000000;
			}
		}
		
		long answer = 0;
		for(int i = 0 ; i <10 ; i ++)
			answer += dp[N][i];
			
		
		System.out.println(answer%1000000000);
		
	}

}
