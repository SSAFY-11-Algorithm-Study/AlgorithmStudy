import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, map[][], seedCnt, selectedSeed[], min;
	static ArrayList<int[]> seedList;
	static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		seedList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
                seedList.add(new int[] { i, j });
			}
		}
		seedCnt = seedList.size();
		selectedSeed = new int[3];
		min = Integer.MAX_VALUE;

		getThreeSeeds(0, 0);
		System.out.println(min);
	}

	private static void getThreeSeeds(int cnt, int start) {
		if (cnt == 3) {
			min = Math.min(min, getCost());
			return;
		}
		for (int i = start; i < seedCnt; i++) {
			selectedSeed[cnt] = i;
			getThreeSeeds(cnt + 1, i + 1);
		}
	}

	private static int getCost() {
		int totalCost = 0;
		int x, y;
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < 3; i++) {
			x = seedList.get(selectedSeed[i])[0];
			y = seedList.get(selectedSeed[i])[1];
			visited[x][y] = true;
			totalCost += map[x][y];
			for (int j = 0; j < 4; j++) {
				int dx = x + d[j][0];
				int dy = y + d[j][1];
				if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy]) {
					visited[dx][dy] = true;
					totalCost += map[dx][dy];
				} else {
					return Integer.MAX_VALUE;
				}
			}
		}
		return totalCost;
	}
}
