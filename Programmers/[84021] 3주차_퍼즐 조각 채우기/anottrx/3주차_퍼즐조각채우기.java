// https://art-coding3.tistory.com/52 참고했습니다

import java.util.*;

class Solution {

	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public int solution(int[][] game_board, int[][] table) {
		int answer = -1;

		int N = game_board.length; // 게임보드와 테이블 가로세로 길이

		Queue<int[]> q = new LinkedList<int[]>(); // bfs 위함
		ArrayList<int[]> pieces; // bfs 후 조각들을 저장하기 위함

		HashMap<ArrayList<int[]>, ArrayList<ArrayList<int[]>>> gamePieces = new HashMap<>();
		// key:테이블 위 조각, values:해당 조각을 90도씩 회전한 결과(가지수: 1가지, 2가지, 4가지) -> 둘다 위치 변경 없음

		boolean[][] visited = new boolean[N][N]; // bfs 위함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (table[i][j] == 1 && !visited[i][j]) { // 테이블 위 1이고 방문 안 했다면 bfs
					pieces = getPieces(i, j, 1, N, table, visited, q);
					gamePieces.put(pieces, null); // 찾은 조각 리스트를 key, 회전은 아직 안 했기 때문에 null
				}
			}
		}

		rotate(N, gamePieces); // 테이블 위 조각들(key)을 회전하여 저장(value)

		int cnt = 0; // 답
		visited = new boolean[N][N]; // bfs 위함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (game_board[i][j] == 0 && !visited[i][j]) { // 게임보드 위 0이고 방문 안 했다면 bfs
					pieces = getPieces(i, j, 0, N, game_board, visited, q);
					cnt = cnt + getSamePiece(pieces, gamePieces); // 찾은 빈 칸에 맞는 조각이 있다면 칸 개수만큼 증가
				}
			}
		}

		answer = cnt;
		System.out.println(answer);

		return answer;
	}

	private static ArrayList<int[]> getPieces(int x, int y, int k, int N, int[][] map, boolean[][] visited, Queue<int[]> q) {
		// 빈칸 또는 조각을 찾고 리스트에 담아 리턴 (bfs) (k는 게임보드일 때는 0, 테이블일 때는 1)
		ArrayList<int[]> pieces = new ArrayList<>(); // 찾은 칸들을 저장할 리스트
		q.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			pieces.add(new int[] { cur[0], cur[1] });

			for (int i = 0; i < 4; i++) {
				int dx = cur[0] + d[i][0];
				int dy = cur[1] + d[i][1];
				if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy] && map[dx][dy] == k) {
					visited[dx][dy] = true;
					q.offer(new int[] { dx, dy });
				}
			}
		}

		return pieces;
	}

	private static void rotate(int N, HashMap<ArrayList<int[]>, ArrayList<ArrayList<int[]>>> gamePieces) {
		ArrayList<ArrayList<int[]>> allRotated; // 하나의 조각을 총 1번/2번/4번 회전한 결과들을 모두 저장할 리스트
		ArrayList<int[]> curPieces; // 이전의 조각 모습(이걸로 회전하기)
		ArrayList<int[]> rotatedPieces; // 하나의 조각을 한 번 회전한 결과(칸 모음)

		for (ArrayList<int[]> piece : gamePieces.keySet()) { // 테이블 위 조각 개수만큼 반복
			allRotated = new ArrayList<>();
			allRotated.add(piece); // 처음 회전 안 한 조각 저장
			curPieces = piece; // 이 조각으로 다음에 회전
			for (int i = 0; i < 3; i++) { // 90도, 180도, 270도 회전
				if ((piece.size() == 1) || (piece.size() == 2 && i >= 2)) { // 조각 칸 개수가 1개인 경우. 또는 2개인데 회전 한 번 한 경우 그만두기
					continue;
				}

				rotatedPieces = new ArrayList<>();
				for (int j = 0; j < curPieces.size(); j++) { // 해당 조각의 모든 칸 개수만큼 반복
					int[] cur = curPieces.get(j);
					rotatedPieces.add(new int[] { cur[1], N - 1 - cur[0] }); // 회전
				}

				curPieces = rotatedPieces; // 이 조각으로 다음에 회전
				allRotated.add(rotatedPieces); // 회전한 결과를 저장
			}

			gamePieces.put(piece, allRotated); // 하나의 조각의 모든 회전이 끝났으므로 해당 조각(key)의 value로 저장
		}
	}

	private static ArrayList<int[]> makeMin(ArrayList<int[]> pieces) { // 빈 칸 또는 조각을 최소화시키기
		ArrayList<int[]> minArr = new ArrayList<>();
		int minX = 51; // 게임보드와 테이블 최댓값은 50
		int minY = 51;
		for (int i = 0; i < pieces.size(); i++) {
			int[] cur = pieces.get(i);
			minX = Math.min(minX, cur[0]);
			minY = Math.min(minY, cur[1]);
		}
		for (int i = 0; i < pieces.size(); i++) {
			int[] cur = pieces.get(i);
			cur[0] = cur[0] - minX;
			cur[1] = cur[1] - minY;
			minArr.add(cur);
		}
		return minArr;
	}

	private static int getSamePiece(ArrayList<int[]> boardPieces, HashMap<ArrayList<int[]>, ArrayList<ArrayList<int[]>>> gamePieces) {
		// 빈 칸에 맞는 퍼즐 조각이 있는지 찾고, 있다면 해당 칸 개수만큼 리턴

		boardPieces = makeMin(boardPieces); // 해당 게임보드의 빈 칸을 최소화

		for (ArrayList<int[]> piece : gamePieces.keySet()) { // 테이블 위 모든 퍼즐 조각을 가지고 확인 (만약 맞는 조각이 있다면 remove해서 다음 차례에는 개수가 줄어들 것)
			if (piece.size() == boardPieces.size()) { // 게임보드의 빈칸을 퍼즐조각이 한 번에 모두 채워야하므로 둘이 같은 개수일 때만 확인

				for (ArrayList<int[]> gamePiece : gamePieces.get(piece)) { // 해당 퍼즐조각의 모든 회전된 모습을 비교
					gamePiece = makeMin(gamePiece); // 회전된 퍼즐조각을 최소화

					ArrayList<int[]> curBoardArr = boardPieces; // 게임보드 빈 칸
					ArrayList<int[]> curTableArr = gamePiece; // 퍼즐조각
					int boardSize = curBoardArr.size();

					for (int j = 0; j < boardSize; j++) { // 게임보드의 빈 칸의 모든 칸을 하나하나 퍼즐조각의 모든 칸과 비교하기
						// 게임보드:(0,0)(1,0), 테이블 위 퍼즐조각:(1,0)(0,0) -> 이 둘은 서로 딱 맞는 경우인데 코드에서는 다르게 인식해서 하나하나 비교
						int[] curBoard = curBoardArr.get(j);
						int tableSize = curTableArr.size();
						finding: for (int k = 0; k < tableSize; k++) { // 퍼즐조각 칸 개수만큼 반복 (같은게 있었다면 갈수록 줄어들것)
							int[] curTable = curTableArr.get(k);
							if (curBoard[0] == curTable[0] && curBoard[1] == curTable[1]) {
								// 게임보드 한 칸의 x좌표,y좌표가 테이블 위 퍼즐조각 한 칸의 x좌표,y좌표와 동일하다면 해당 퍼즐조각 없애기
								curTableArr.remove(k);
								break finding;
							}
						}
					}

					if (curTableArr.isEmpty()) { // 다했을 때 퍼즐조각이 비었다면 딱 맞는 경우
						int cnt = piece.size();
						gamePieces.remove(piece); // 해당 퍼즐조각은 사용했기 때문에 remove
						return cnt; // 칸 개수 리턴
					}
				}
			}
		}
		return 0; // 해당 빈칸에 맞는 퍼즐조각이 없으므로 0을 리턴 (계산에 영향 X)
	}
}
