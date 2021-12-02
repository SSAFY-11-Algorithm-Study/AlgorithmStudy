import java.util.*;

public class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visit;
    static int N, M;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) count++;
            }
        }

        int tmpCount = 0;
        int time = 0;
        while (count != 0) {
            tmpCount = count;
            time++;
            visit = new boolean[N][M];
            bfs();
        }
        System.out.println(time);
        System.out.println(tmpCount);
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visit[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M || visit[nextR][nextC]) continue;

                visit[nextR][nextC] = true;
                if (map[nextR][nextC] == 0) {
                    q.offer(new int[]{nextR, nextC});
                } else {
                    count--;
                    map[nextR][nextC] = 0;
                }

            }
        }
    }
}
