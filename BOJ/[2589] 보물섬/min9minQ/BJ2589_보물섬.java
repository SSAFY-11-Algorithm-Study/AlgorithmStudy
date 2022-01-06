package time22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589_보물섬 {

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int N, M;
	private static char[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int result = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[N][M];
					bfs(i, j);
					result = Math.max(result, time);
					time = 0;
				}
			}
		}
		
		System.out.println(result-1);

	}

	private static void bfs(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(x,y));
		visited[x][y] = true;
		
		while (!que.isEmpty()) {
			int size = que.size();
			time++;
			for (int i = 0; i < size; i++) {
				Point temp = que.poll();

				for (int j = 0; j < 4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];

					if (nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == false && map[nx][ny] == 'L') {
						que.offer(new Point(nx,ny));
						visited[nx][ny] = true;
					}
				}

			}
		}

	}

}
