// 하다가 계속 틀려서... 
// 블로그 참조..!
// 그런데 83 번쨰 줄에서 arrayindexoutofbounds 에러 나는데 왜나는지 모르겟...
package time18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636_치즈 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer = 0, time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//System.out.println(Arrays.deepToString(map));

		while (true) {
			if (Empty()) {
				break;
			}
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			check();
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			answer = melt();
			time++;
		}
		System.out.println(time);
		System.out.println(answer);
	}

	private static int melt() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					map[i][j] = -1;// 녹은 치즈
					count += 1;
				}
			}
		}
		return count;
	}

	private static void check() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { 0, 0 });
		visited[0][0] = true;
		map[0][0] = -1;// 공기
		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int x = temp[0];
			int y = temp[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[nx][ny])
					continue;
				if (map[nx][ny] == 0 || map[nx][ny] == -1) { // 공기
					visited[nx][ny] = true;
					que.offer(new int[] { nx, ny });
				} else {
					map[nx][ny] = 2; // 녹을 치즈
				}
			}
		}
	}

	private static boolean Empty() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}
}