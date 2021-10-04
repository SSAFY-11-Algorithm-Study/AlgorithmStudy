import java.util.Scanner;

public class BOJ2579_SteppingStairs {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //계단의 개수
		int[] stair = new int[N+1]; //1~N칸까지이므로
		int[] dp = new int[N+1]; //n번째 칸 도착했을 때 얻을 수 있는 최대의 점수 저장
		
		for (int i = 1; i < N+1; i++) {
			stair[i] = sc.nextInt();
		}//입력 완료
		
		//이 조건 넣어줘야 런타임 오류 안남!
		if(N==1) {
			System.out.println(stair[1]);
			return;
		} else if(N==2) {
			System.out.println(stair[1]+ stair[2]);
			return;
		}
		
		//초기값
		dp[1] = stair[1];
		dp[2] = stair[1] + stair[2];

		for (int i = 3; i < N+1; i++) {
			// 한 계단 올라온 경우(단, n-1은 n-3에서 올라온 것이어야 함) vs 두 계단 올라온 경우
			dp[i] = Math.max(dp[i-3] + stair[i-1], dp[i-2]) + stair[i];
		}
		
		System.out.println(dp[N]);
	}
}
