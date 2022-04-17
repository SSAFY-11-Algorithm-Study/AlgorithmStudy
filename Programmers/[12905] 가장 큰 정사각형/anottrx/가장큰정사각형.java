/*
* 문제 풀이 순서
1. x축과 y축 모두 1부터 시작해서 끝까지 이동한다.
2. 해당 위치의 왼쪽, 위, 왼쪽위가 모두 0 이상일 때 모든 값의 최솟값을 해당 위치에 더한다
3. 모든 위치의 최댓값의 제곱한 값이 답이다

* 처음에는 모든 위치마다 가로세로 길이가 1, 2, 3, ...인 정사각형이 될 수 있는 경우를 모두 구했더니 시간초과가 났다.
* */

class Solution {
    static int[][] d = {{-1, -1}, {-1, 0}, {0, -1}};

    public int solution(int[][] board) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (board[i][j] > 0) {
                    int cnt = board[i - 1][j - 1];
                    boolean canMakeSquare = true;
                    for (int k = 1; k < 3; k++) {
                        int dx = i + d[k][0];
                        int dy = j + d[k][1];
                        if (board[dx][dy] == 0) {
                            canMakeSquare = false;
                            break;
                        } else {
                            cnt = Math.min(cnt, board[dx][dy]);
                        }
                    }
                    if (canMakeSquare) {
                        board[i][j] += cnt;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, board[i][j]);
            }
        }
        return answer * answer;
    }
}
