import java.util.Arrays;

class Solution {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;

		int[][] map = new int[n + 1][n + 1];
		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int cost = fares[i][2];
			map[from][to] = cost;
			map[to][from] = cost;
		}

		int[] distanceS = dijkstra(s, n, map);
		int[] distanceA = dijkstra(a, n, map);
		int[] distanceB = dijkstra(b, n, map);

		answer = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, distanceS[i] + distanceA[i] + distanceB[i]);
		}

		return answer;
	}

	private static int[] dijkstra(int start, int n, int[][] map) {
		boolean[] visited = new boolean[n + 1];
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		int min = 0, cur = 0;
		for (int i = 1; i <= n; i++) {
			min = Integer.MAX_VALUE;
			for (int j = 1; j <= n; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					cur = j;
				}
			}
			visited[cur] = true;

			for (int j = 1; j <= n; j++) {
				if (!visited[j] && map[cur][j] != 0 && distance[j] > min + map[cur][j]) {
					distance[j] = min + map[cur][j];
				}
			}
		}

		return distance;
	}
}
