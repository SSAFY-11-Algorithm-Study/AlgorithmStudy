import java.util.Scanner;

//dp문제 (https://yabmoons.tistory.com/536 참고)

public class BOJ2133_Tile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N%2==1) { //N이 홀수이면 답은 0
			System.out.println(0);
			return;
		}
		//N이 짝수라면
		int[] dp = new int[N+1]; //dp[a]=b : 3*a인 타일을 채우는 방법의 수가 b개
		
		//점화식 : dp[N] = (dp[N-2] * dp[2]) + (dp[N-4] * 2) + (dp[N-6] * 2) + ... + (dp[0] * 2)
		
		//초깃값
		dp[0]=1;
		dp[2]=3;
		for(int i=4; i<=N; i+=2) {
			
			dp[i] += dp[i-2] * dp[2];
			
			for(int j=i-4; j>=0; j-=2) {
				dp[i]+=dp[j]*2;
			}
		}
		System.out.println(dp[N]);
  }
}
