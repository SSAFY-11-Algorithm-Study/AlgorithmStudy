import java.util.Scanner;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        visit = new boolean[N][M];
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                checkT(i, j);
                visit[i][j] = false;
            }
        }
        System.out.println(answer);

    }

    static void dfs(int r, int c, int cnt, int sum) {

        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];

            if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M && !visit[nextR][nextC]) {
                visit[nextR][nextC] = true;
                dfs(nextR, nextC, cnt + 1, sum + map[nextR][nextC]);
                visit[nextR][nextC] = false;
            }
        }
    }

    static void checkT(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int sum = map[r][c];
            boolean flag = false;

            for (int j = 0; j < 3; j++) {
                int nextR = r + dr[(i + j) % 4];
                int nextC = c + dc[(i + j) % 4];

                if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M) {
                    sum += map[nextR][nextC];
                } else {
                    flag = true;
                    break;
                }

            }

            if(!flag) answer = Math.max(answer, sum);
        }
    }
}
