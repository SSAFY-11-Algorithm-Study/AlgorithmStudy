//블로그 참고해서 풀었음ㅠㅠ
// A를 이용해서 N을 만드는 모든 경우의 수는, N-A의 총 경우의 수와 동일하다!

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293_Coin {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //각 동전의 가치
		int k = Integer.parseInt(st.nextToken()); //만들 값
		
		int[] arr = new int[n]; //각 동전의 가치를 순서대로 입력받음
		int[] dp = new int[k+1]; // dp[i]=N : i원을 만들 수 있는 총 경우의 수는 N개
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		} //입력 완료
		
		dp[0]=1; //초기값
		//각 동전마다 그 동전의 값부터 k까지 그 동전을 이용해서 만들 수 있는 경우의 수를 누적해 나감
		for (int i = 0; i < n; i++) {
			for (int j = arr[i]; j < k+1; j++) {
				dp[j] = dp[j] + dp[j-arr[i]];
			}
		}
		
		System.out.println(dp[k]);
	}
}
