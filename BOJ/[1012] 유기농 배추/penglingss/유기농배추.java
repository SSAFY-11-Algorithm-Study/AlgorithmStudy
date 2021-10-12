import java.util.Scanner;

public class Main {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visit;

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            int cnt = 0;

            map = new int[M][N];
            visit = new boolean[M][N];
            for (int i = 0; i < K; i++) {
                int r, c;
                r = sc.nextInt();
                c = sc.nextInt();
                map[r][c] = 1;
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void dfs(int r, int c) {
        if (visit[r][c]) return;
        visit[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (nextR >= 0 && nextC >= 0 && nextR < M && nextC < N) {
                if (map[nextR][nextC] == 1 && !visit[nextR][nextC]) {
                    dfs(nextR, nextC);
                }
            }
        }
    }
}
