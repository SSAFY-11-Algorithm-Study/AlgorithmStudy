import java.util.Scanner;

public class Main {
    static int N, M, K, count;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static boolean[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        int answer = 0;

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            map[r - 1][c - 1] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j]) {
                    count = 0;
                    dfs(i, j);
                    answer = Math.max(answer, count);
                }
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int r, int c) {
        count++;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M) continue;
            if (!visited[nextR][nextC] && map[nextR][nextC]) {
                dfs(nextR, nextC);
            }
        }
    }
}
