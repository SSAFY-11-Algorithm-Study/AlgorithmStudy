//DFS -> 시간초과 

package time27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1261_알고스팟 {

	static class Point implements Comparable<Point> {
		int x, y;
		int wall;

		public Point(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}

		@Override
		public int compareTo(Point o) {
			return this.wall - o.wall;
		}
	}

	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(answer);

	}

	private static void bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, 0));
		visited[0][0] = true;

		while (!pq.isEmpty()) {

			Point temp = pq.poll();

			if (temp.x == N - 1 && temp.y == M - 1) {
				answer = Math.min(temp.wall, answer);
				return;
			}

			for (int i = 0; i < 4; i++) {

				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == false) {
					if (map[nx][ny] == 0) {
						pq.offer(new Point(nx, ny, temp.wall));
					}
					if (map[nx][ny] == 1) {
						pq.offer(new Point(nx, ny, temp.wall + 1));
					}
					visited[nx][ny] = true;
				}
			}
		}
	}

}
