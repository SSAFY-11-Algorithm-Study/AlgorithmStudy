import java.util.*;

class Solution {

    static private class Position {
        int x, y, cnt;

        public Position(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public int solution(int[][] maps) {
        int answer = -1; // 초기화

        int mapLenX = maps.length;
        int mapLenY = maps[0].length;
        int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        boolean[][] visited = new boolean[mapLenX][mapLenY];
        Queue<Position> q = new LinkedList<>();

        q.add(new Position(0, 0, 1)); // 시작
        visited[0][0] = true;

        while (!q.isEmpty()) { // bfs
            Position cur = q.poll();

            if (cur.x == mapLenX - 1 && cur.y == mapLenY - 1) { // 종료조건
                answer = cur.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + d[i][0];
                int dy = cur.y + d[i][1];

                if (dx >= 0 && dx < mapLenX && dy >= 0 && dy < mapLenY && !visited[dx][dy] && maps[dx][dy] == 1) {
                    visited[dx][dy] = true;
                    q.add(new Position(dx, dy, cur.cnt + 1));
                }
            }
        }

        return answer;
    }
}
