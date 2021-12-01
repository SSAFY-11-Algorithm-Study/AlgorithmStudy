// https://sihyungyou.github.io/boj-2636/ 참고했습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {

	static int N, M, map[][];
	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0; // 치즈가 녹아 없어지는 데 걸리는 시간
		int lastCheeseCnt = 0; // 모두 녹기 한 시간 전에 남아있는 치즈조각

		while (true) {
			int curCheeseCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						curCheeseCnt++;
					}
				}
			}
			if (curCheeseCnt == 0) {
				break;
			} else {
				lastCheeseCnt = curCheeseCnt;
			}

			time++;

			boolean[][] checkVisited = new boolean[N][M];
			checkAir(checkVisited); // 바깥 공기 위치를 true로 바꾸기

			ArrayList<int[]> cheeseMelt = new ArrayList<>();
			checkCheese(cheeseMelt, checkVisited); // 바깥 공기와 맞닿은 치즈 위치 모두 구하기

			meltCheese(cheeseMelt); // 구한 치즈 모두 녹이기
		}

		System.out.println(time);
		System.out.println(lastCheeseCnt);
	}

	private static void checkAir(boolean[][] checkAirVisited) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0 });
		checkAirVisited[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			for (int i = 0; i < 4; i++) {
				int dx = x + d[i][0];
				int dy = y + d[i][1];
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && !checkAirVisited[dx][dy] && map[dx][dy] == 0) {
					checkAirVisited[dx][dy] = true;
					q.offer(new int[] { dx, dy });
				}
			}
		}
	}

	private static void checkCheese(ArrayList<int[]> cheeseMelt, boolean[][] checkVisited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !checkVisited[i][j]) {
					checkingNear: for (int k = 0; k < 4; k++) {
						int dx = i + d[k][0];
						int dy = j + d[k][1];
						if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == 0 && checkVisited[dx][dy]) {
							checkVisited[i][j] = true;
							cheeseMelt.add(new int[] { i, j });
							break checkingNear;
						}
					}
				}
			}
		}
	}

	private static void meltCheese(ArrayList<int[]> cheeseMelt) {
		for (int i = 0; i < cheeseMelt.size(); i++) {
			int[] cur = cheeseMelt.get(i);
			map[cur[0]][cur[1]] = 0;
		}
	}
}
