import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, max;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        max = 0;
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        buildWall(0);

        System.out.println(max);
    }

    private static void buildWall(int cnt) {
        if (cnt == 3) {
            int n = cntSafe();
            max = Math.max(max, n);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    buildWall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int cntSafe() {
        int cnt = 0;

        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2) {
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextR = cur[0] + dr[k];
                            int nextC = cur[1] + dc[k];

                            if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M && copyMap[nextR][nextC] == 0) {
                                copyMap[nextR][nextC] = 2;
                                q.add(new int[]{nextR, nextC});
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }
}
