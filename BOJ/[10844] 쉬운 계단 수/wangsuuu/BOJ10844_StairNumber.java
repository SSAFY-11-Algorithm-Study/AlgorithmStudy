import java.util.Scanner;

//자연수의 자릿수는 소수와 반대임을 잊음.
// ex. 54321 : 첫번째 자리 - 1, 두번째 자리 - 2 ... 다섯번째 자리 - 5

//의문점 : dp[2][1] = 1이지만, dp[N][0]부터 해서 어쩄든 답은 나옴

public class BOJ10844_StairNumber {

    public static void main(String[] args) {
    	
    	long mod = 1000000000;
    	
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	
    	long[][] dp = new long[N+1][10]; //dp[자릿수][마지막 자릿수에 오는 수. 0~9]
    	//dp[i][j] : 총 자릿수 i에서 마지막 자릿수가 j일 때 가능한 총 계단수
    	
    	//초기화
    	//dp[0][0~9] = 0
    	for(int i=1; i<=9; i++) {
    		dp[1][i]=1;
    	}
    	
    	for(int i=2; i<=N; i++) {
    		for(int j=0; j<10; j++) {
    			if(j==0) {
    				dp[i][j] = dp[i-1][j+1] % mod;
    			} else if(j==9) {
    				dp[i][j] = dp[i-1][j-1] % mod;
    			} else {
    				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
    			}
    		}
    	}
    	
    	long answer = 0;
    	for(int i=0; i<10; i++) {
    		answer += dp[N][i] % mod;
    	}
    	System.out.println(answer % mod); //맨 끝에도 mod해줘야 함! 100 넣으면 50억 나옴..
    }
}
