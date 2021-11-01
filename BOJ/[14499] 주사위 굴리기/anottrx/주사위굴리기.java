import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14499 {

	static int N, M, K, map[][], diceOrder[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도 세로
		M = Integer.parseInt(st.nextToken()); // 지도 가로
		int startX = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 좌표
		int startY = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 명령의 개수
		map = new int[N][M]; // 지도
		diceOrder = new int[K]; // 명령
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			diceOrder[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> answer = moveDice(startX, startY); // 명령 따라 주사위 이동

		for (int i = 0; i < answer.size(); i++) {
			bw.write(answer.get(i) + "\n"); // 답 출력
		}
		bw.flush();
		bw.close();
	}

	private static ArrayList<Integer> moveDice(int startX, int startY) {
		int[][] d = { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
		int[][] diceMovement = { {}, { 3, 0, 2, 5, 4, 1 }, { 1, 5, 2, 0, 4, 3 }, // 동쪽, 서쪽
				{ 0, 2, 3, 4, 1, 5 }, { 0, 4, 1, 2, 3, 5 } }; // 북쪽, 남쪽
		/*
		 처음 모양  , 동쪽으로 이동 , 서쪽으로 이동, 북쪽으로 이동, 남쪽으로 이동
		 0 1 5        3 0 1		 1 5 3		0 2 5	      0 4 5 
		   2		2		   2		  3		1
		   3		5		   0		  4		2
		   4		4		   4		  1		3
		*/
		// 주사위 윗면은 diceMovement[1], 아랫면은 diceMovement[3] 자리

		ArrayList<Integer> diceUpNum = new ArrayList<>(); // 이동할 때마다 주사위의 윗 면에 쓰여 있는 수. 답
		int[] diceNum = new int[6]; // 현재 주사위의 각 면에 적힌 번호
		int[] befDiceNum = new int[6]; // 직전 주사위의 각 면에 적힌 번호
		int curX = startX; // 주사위 현재 x좌표
		int curY = startY; // 주사위 현재 y좌표

		for (int i = 0; i < K; i++) {
			int dx = curX + d[diceOrder[i]][0];
			int dy = curY + d[diceOrder[i]][1];
			if (dx >= 0 && dx < N && dy >= 0 && dy < M) { // 주사위는 지도의 바깥으로 이동X -> 해당 명령 무시, 출력 X
				for (int j = 0; j < 6; j++) { // 명령 따라 주사위 한 칸 이동 후 해당 주사위의 면 위에 적힌 번호를 저장
					diceNum[j] = befDiceNum[diceMovement[diceOrder[i]][j]];
				}

				if (map[dx][dy] == 0) { // 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
					map[dx][dy] = diceNum[3];
				} else { // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
					diceNum[3] = map[dx][dy];
					map[dx][dy] = 0;
				}
				diceUpNum.add(diceNum[1]); // 주사위 윗면

				curX = dx; // 현재 주사위 위치 저장
				curY = dy;
				for (int j = 0; j < 6; j++) {
					befDiceNum[j] = diceNum[j]; // 현재 주사위를 다음 이동 때 사용하기 위함
				}
			}
		}

		return diceUpNum;
	}
}
