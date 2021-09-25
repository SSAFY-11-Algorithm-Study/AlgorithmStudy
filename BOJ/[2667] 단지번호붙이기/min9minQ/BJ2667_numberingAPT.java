//dfs 사용

package time9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ2667_numberingAPT {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	static int total = 0;
	static int apt;
	static ArrayList<Integer> apts = new ArrayList<>();// 단지별 집 수

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		N = Integer.parseInt(str);

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str.split("")[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				apt = 0;
				if (map[i][j] == 1 && !visited[i][j]) {
					total++;
					apt++;
					dfs(i, j);
					apts.add(apt);
				}
			}
		}

		System.out.println(total);
		Collections.sort(apts);
		for (int i = 0; i < apts.size(); i++) {
			System.out.println(apts.get(i));
		}

	}

	static void dfs(int x, int y) {

		visited[x][y] = true;

		int ny, nx;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (!visited[nx][ny] && map[nx][ny] == 1) {
					apt++;
					dfs(nx, ny);
				}
			}
		}

	}

}