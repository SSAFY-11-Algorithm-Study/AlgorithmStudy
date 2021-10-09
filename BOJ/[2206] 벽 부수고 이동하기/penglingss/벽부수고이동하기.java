import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
    static class Position {
        int r;
        int c;
        int cnt;
        boolean wall;

        public Position(int r, int c, int cnt, boolean wall) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.wall = wall;
        }
    }

    static int N, M;
    static char[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new char[N][M];
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        bfs();
    }

    private static void bfs() {
        Queue<Position> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2]; // 세번째 값 : 0 이면 벽을 안부숨, 1 이면 벽을 부순적있음
        q.add(new Position(0, 0, 1, false));

        while (!q.isEmpty()) {
            Position cur = q.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                System.out.println(cur.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;

                int nextCnt = cur.cnt + 1;

                if (map[nextR][nextC] == '0') {
                    if (!cur.wall && !visited[nextR][nextC][0]) {
                        q.add(new Position(nextR, nextC, nextCnt, false));
                        visited[nextR][nextC][0] = true;
                    } else if (cur.wall && !visited[nextR][nextC][1]) {
                        q.add(new Position(nextR, nextC, nextCnt, true));
                        visited[nextR][nextC][1] = true;
                    }

                } else if (map[nextR][nextC] == '1') {

                    if (!cur.wall) {
                        q.add(new Position(nextR, nextC, nextCnt, true));
                        visited[nextR][nextC][1] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
