import java.util.ArrayList;
import java.util.Collections;

class Solution {

    static char[][] map;
    static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 위 아래 왼쪽 오른쪽
    static int[] dL = { 3, 2, 0, 1 }; // 좌회전할 때 방향
    static int[] dR = { 2, 3, 1, 0 }; // 우회전할 때 방향
    static ArrayList<Integer> cycleCntArr;
    static int N, M, cnt;

    public int[] solution(String[] grid) {
        int[] answer = {};

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
                        cnt = 0;
                        getCycle(i, j, k, visited);
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
	    
        return answer;
    }

    private static void getCycle(int x, int y, int dir, boolean[][][] visited) {
        while (true) {
            if (visited[dir][x][y]) {
                break;
            }

            cnt++;
            visited[dir][x][y] = true;

            if (map[x][y] == 'L') {
                dir = dL[dir];
            } else if (map[x][y] == 'R') {
                dir = dR[dir];
            }

            x = getPosition(x, dir, 0, N);
            y = getPosition(y, dir, 1, M);

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
