import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] map, cnt; // map:게임판(뱀,사다리 포함), cnt:해당 위치에 가기까지 필요한 주사위 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[101];
		for (int i = 0; i < 101; i++) {
			map[i] = i; // 우선 해당 위치를 해당 값으로 채우기
		}
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from] = to; // 사다리와 뱀인 경우 해당 위치 값 바꾸기
		}
		playGame(); // bfs
		System.out.println(cnt[100]); // 마지막 100번째가 답
	}

	private static void playGame() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[101];
		cnt = new int[101];
		q.offer(1); // 시작은 1

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int dice = 1; dice < 7; dice++) {
				if (cur + dice > 100) { // 100을 넘는 경우는 무시하기
					continue;
				}
				int next = map[cur + dice]; // 다음 위치로

				if (next < 101 && !visited[next]) {
					q.offer(next);
					cnt[next] = cnt[cur] + 1;
					visited[next] = true;
					if (next == 100) { // 100이면 끝내기
						return;
					}
				}
			}
		}
	}
}
