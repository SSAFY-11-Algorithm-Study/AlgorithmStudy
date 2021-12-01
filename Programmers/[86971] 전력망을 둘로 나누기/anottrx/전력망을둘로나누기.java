class Solution {

	static int cnt = 0;
	static boolean[] visited;

	public int solution(int n, int[][] wires) {
		int answer = -1;

		answer = Integer.MAX_VALUE;
		boolean[][] wireList = new boolean[n + 1][n + 1];
		for (int i = 0; i < wires.length; i++) {
			int from = wires[i][0];
			int to = wires[i][1];
			wireList[from][to] = true; // 연결하기
			wireList[to][from] = true;
		}

		for (int i = 0; i < wires.length; i++) {
			int from = wires[i][0];
			int to = wires[i][1];
			wireList[from][to] = false; // 연결 끊기
			wireList[to][from] = false;
			cnt = 1;
			visited = new boolean[n + 1];
			dfs(1, wireList, n); // dfs
			int otherCnt = n - cnt;
			answer = Math.min(answer, Math.abs(cnt - otherCnt));
			wireList[from][to] = true; // 다시 연결하기
			wireList[to][from] = true;
		}

		return answer;
	}

	private static void dfs(int from, boolean[][] wireList, int n) {
		visited[from] = true;
		for (int i = 1; i <= n; i++) {
			if (wireList[from][i] && !visited[i]) {
				visited[i] = true;
				cnt++;
				dfs(i, wireList, n);
			}
		}
	}
}
