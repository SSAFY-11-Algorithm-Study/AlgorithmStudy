import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
	static int N, M, map[][], cnt;
	static boolean[][] visited;

	// 0:북쪽, 1:동쪽, 2:남쪽, 3:서쪽
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		robotCleaning(x, y, dir);
	}

	private static void robotCleaning(int curX, int curY, int curD) {
		// 1. 현재 위치를 청소한다.
		if (!visited[curX][curY]) {
			visited[curX][curY] = true;
			cnt++;
		}

		while (true) {
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
			for (int i = curD + 3; i >= curD; i--) {
				int dx = curX + d[i][0];
				int dy = curY + d[i][1];

				// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
				if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == 0 && !visited[dx][dy]) {
					robotCleaning(dx, dy, i % 4);
				}
				// 2-b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
			}

			int dx = curX - d[curD][0];
			int dy = curY - d[curD][1];
			// 2-c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == 0) {
				curX = dx;
				curY = dy;
			} else {
				// 2-d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
				System.out.println(cnt); // 답은 출력
				System.exit(0); // exit(0)으로 끝내기
			}
		}
	}
}
