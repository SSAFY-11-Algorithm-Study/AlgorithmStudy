import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {

	public static void main(String[] args) throws IOException {
		int N, M, map[][];
		int[][][] poly = { { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, // 노랑
				{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } }, // 파랑
				{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 0 } }, // 주황
				{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 0, 0 } }, // 주황
				{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, // 주황
				{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } }, // 주황
				{ { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } }, // 분홍
				{ { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }, // 분홍
				{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 1, 0 }, { 2, 0 }, { 0, 1 }, { 1, 1 } }, // 초록
				{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } }, // 초록
		};

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

		int max = 0; // 답
		int temp = 0;
		int dx, dy;
		for (int d = 0; d < 19; d++) { // 폴리오미노 19가지
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) { // 지도의 모든 칸을 확인
					temp = 0;
					addFour: for (int k = 0; k < 4; k++) { // 4칸을 모두 더하기
						dx = i + poly[d][k][0];
						dy = j + poly[d][k][1];
						if (dx >= 0 && dx < N && dy >= 0 && dy < M) { // 맵 안일 경우
							temp = temp + map[dx][dy];
						} else { // 맵 밖으로 나가면 계산 그만두기
							temp = 0;
							break addFour;
						}
					}
					max = Math.max(max, temp);
				}
			}
		}

		System.out.println(max);
	}
}
