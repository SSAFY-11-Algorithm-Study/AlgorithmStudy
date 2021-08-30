// 출처: 백준저지 2606번 바이러스 https://www.acmicpc.net/problem/2606

import java.util.Scanner;

public class BOJ2606 {
    static int N, M, cnt;
	static boolean[][] adjMatrix;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adjMatrix = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = adjMatrix[to][from] = true;
		}
		boolean[] visited = new boolean[N + 1];
		dfs(1, visited);
		System.out.println(cnt - 1);
	}

	private static void dfs(int cur, boolean[] visited) {
		visited[cur] = true;
		cnt++;

		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adjMatrix[cur][i]) {
				dfs(i, visited);
			}
		}
	}
}
