package time18;
//테케는 맞는데 런타임 에러 발생...
//플로이드 와샬 연습
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1389_케빈베이컨6단계 {

	static int N;
	static int M;
	static int[][] map;
	static int sum[];
	static int min = Integer.MAX_VALUE;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		sum = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				map[i][j] = 5000000;
				map[i][i] = 0;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			map[num1][num2] = 1;
			map[num2][num1] = 1;
		}
		// 플로이드와샬
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}


		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum[i] = sum[i] + map[i][j];
			}
			if (min > sum[i]) {
				min = sum[i];
				answer = i;
			}
		}
		System.out.println(answer);
	}

}
