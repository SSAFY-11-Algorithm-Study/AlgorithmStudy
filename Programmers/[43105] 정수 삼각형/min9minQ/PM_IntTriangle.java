// 1. 처음 생각한 것은 dfs 
// 2. (분류가 동적계획법이라...) -> 점화식

package time9;

public class PM_IntTriangle {

	static int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };

	public static void main(String[] args) {
		int answer = 0;

		int[][] dp = new int[triangle.length][triangle.length];
		dp[0][0] = triangle[0][0];

		for (int i = 1; i < triangle.length; i++) {

			dp[i][0] = triangle[i][0] + dp[i - 1][0];

			for (int j = 1; j < i + 1; j++) {
				dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}

		int max = 0;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(dp[dp.length - 1][i], max);
		}

		answer = max;
		System.out.println(answer);
		// return answer;
	}

}
