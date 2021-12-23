// DFS ??... 시간초과..
// 2^63-1 -> long 사용..
// dp 방법 블로그 참조..
package time21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5557_1학년 {

	private static int N;
	private static int[] arr;
	private static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new long[N + 1][21];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[1][arr[1]] = 1;

		for (int i = 2; i < N; ++i) { // N-2번
			for (int j = 0; j <= 20; ++j) {
				if (dp[i - 1][j] > 0) { 
					if (arr[i] + j <= 20) { // 더할땐20보다 작으면
						// 결과 더해주기
						dp[i][arr[i] + j] += dp[i - 1][j];
					}
					if (j - arr[i] >= 0) { // 뺄 때는 0보다 크면
						// 결과 더해주기
						dp[i][j - arr[i]] += dp[i - 1][j];
					}
				}
			}
		}

		// 마지막 숫자 출력
		System.out.println(dp[N - 1][arr[N]]);
	}

}
