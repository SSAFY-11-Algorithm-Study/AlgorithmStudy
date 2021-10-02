import java.util.Scanner;

//long형으로 안 하면 틀린다! (N이 100이면 int범위 훨씬 넘어감)

public class BOJ11058_KriBoard {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N<=6) {
			System.out.println(N);
			return;
		}
		
		long[] dp = new long[N+1]; //n번 눌렀을 때 A의 최대 갯수 저장
		
		//초기값 설정
		for (int i = 0; i <=6; i++) {
			dp[i] = i;
		}
		
		for (int i = 7; i <= N; i++) {
			// dp[N]에 들어갈 수 계산하기
			
			//1. case1 : 마지막이 전체선택, 복사, "붙여넣기" 형태
			// N-2, N-1, N번째에 차례대로 전체선택, 복사, 붙여넣기 해서 N번째엔 그 전까지 전체 A만큼 복붙됨
			long case1 = dp[i-3] * 2; 
			
			//2. case2 : 마지막이 전체선택, 복사, 붙여넣기, "붙여넣기" 형태
			long case2 = dp[i-4] * 3;
			
			//3. case 3: 마지막이 전체선택, 복사, 붙여넣기, 붙여넣기, "붙여넣기" 형태
			// 맨 뒤에 붙여넣기가 한번 더 들어가면, 그럴 바엔 2 -3- 4- 2 -3- 4 갯수가 더 많으므로 하지 않음.
			long case3 = dp[i-5] * 4;
			
			dp[i] = Math.max(Math.max(case1, case2), case3);
		}
		System.out.println(dp[N]);
	}
}
