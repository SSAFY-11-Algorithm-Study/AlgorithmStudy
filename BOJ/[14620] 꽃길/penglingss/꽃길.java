import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        answer = Integer.MAX_VALUE;

        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    public static void dfs(int count, int sum) {
        if (count == 3) {
            answer = Math.min(answer, sum);
        } else {
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < N - 1; j++) {
                    if (!visit[i][j] && check(i, j)) {
                        visit[i][j] = true;
                        dfs(count + 1, sum + sum(i, j));
                        back(i, j);
                        visit[i][j] = false;
                    }
                }
            }
        }
    }

    public static boolean check(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (visit[nr][nc]) {
                return false;
            }
        }
        return true;
    }

    public static void back(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            visit[nr][nc] = false;
        }
    }

    public static int sum(int r, int c) {
        int num = map[r][c];

        for (int i = 0; i< dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            visit[nr][nc] = true;
            num += map[nr][nc];
        }

        return num;
    }
}
