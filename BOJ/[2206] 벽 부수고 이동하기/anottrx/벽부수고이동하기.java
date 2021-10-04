package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    static int N, M, map[][];

    static private class Position {
        int x, y, wall, cnt; // x좌표, y좌표, 벽 부쉈는지 여부(0 또는 1), 이동 거리

        public Position(int x, int y, int wall, int cnt) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        System.out.println(move());
    }

    private static int move() { // bfs
        int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        boolean[][][] visited = new boolean[2][N][M]; // 방문 여부 체크. 0은 벽 안 부쉈고, 1은 벽 부수고 이동

        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 0, 0, 0)); // 시작
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) { // 종료 조건
                return cur.cnt + 1;
            }

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + d[i][0];
                int dy = cur.y + d[i][1];
                int cnt = cur.cnt;
                int wall = cur.wall;
                if (dx < 0 || dx >= N || dy < 0 || dy >= M || visited[wall][dx][dy]) {
                    continue;
                }
                if (map[dx][dy] == 0) { // 0이라면 그냥 이동
                    visited[wall][dx][dy] = true;
                    q.add(new Position(dx, dy, wall, cnt + 1));
                } else if (map[dx][dy] == 1 && wall == 0) { // 1이라면 벽을 부수지 않은 경우만 이동
                    wall++; // 벽 부쉈으니 1로 만들기
                    visited[wall][dx][dy] = true;
                    q.add(new Position(dx, dy, wall, cnt + 1));
                }
            }
        }

        return -1; // 이동 불가능
    }
}
