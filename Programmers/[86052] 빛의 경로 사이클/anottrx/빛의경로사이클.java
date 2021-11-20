// 답이 이상하게 나와서 다시 풀어야합니다ㅠ

import java.util.ArrayList;
import java.util.Collections;

public class PROG86052 {

	static char[][] map;
	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 위 아래 왼쪽 오른쪽
	static int[] dL = { 2, 3, 0, 1 }; // 좌회전할 때 방향
	static int[] dR = { 3, 2, 1, 0 }; // 우회전할 때 방향
	static ArrayList<Integer> cycleCntArr;
	static int N, M, cnt;

	public static void main(String[] args) {
//		String[] grid = { "S" };
		String[] grid = { "SL", "LR" };
//		String[] grid = { "R", "R" };
		int[] answer;

		cycleCntArr = new ArrayList<>();
		N = grid.length;
		M = grid[0].length();
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = grid[i].toCharArray();
		}

		boolean[][][] visited = new boolean[4][N][M];
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[k][i][j]) {
						cnt = 1;
						getCycle(i, j, k, i, j, visited);
						cycleCntArr.add(cnt);
					}
				}
			}
		}

		Collections.sort(cycleCntArr);
		answer = new int[cycleCntArr.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = cycleCntArr.get(i);
		}
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
//		return answer;
	}

	private static void getCycle(int x, int y, int dir, int startX, int startY, boolean[][][] visited) {
		while (true) {
			visited[dir][x][y] = true;

			if (map[x][y] == 'L') {
				dir = dL[dir];
			} else if (map[x][y] == 'R') {
				dir = dR[dir];
			}

			x = getPosition(x, dir, 0, N);
			y = getPosition(y, dir, 1, M);
			if (visited[dir][x][y]) {
				break;
			}

			cnt++;
		}
	}

	private static int getPosition(int cur, int dir, int xy, int len) {
		int dd = cur + d[dir][xy];
		if (dd < 0) {
			dd = dd + len;
		}
		return (dd % len);
	}
}

