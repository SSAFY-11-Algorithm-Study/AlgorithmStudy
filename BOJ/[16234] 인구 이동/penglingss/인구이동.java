import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, R, L;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int answer = 0;
        while (true) {
            boolean flag = false;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) continue;
                    if(checkBorder(i, j)) flag = true;
                }
            }
            if (!flag) break;
            answer++;
        }

        System.out.println(answer);
    }

    private static boolean checkBorder(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> union = new LinkedList<>();
        q.add(new int[]{r, c});
        visit[r][c] = true;
        int sum = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            union.add(cur);
            int population = map[cur[0]][cur[1]];
            sum += population;

            for (int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N || visit[nextR][nextC]) continue;

                int nextPopulation = map[nextR][nextC];
                int dif = Math.abs(nextPopulation - population);
                if (dif < L || dif > R) continue;

                q.add(new int[]{nextR, nextC});
                visit[nextR][nextC] = true;
            }
        }

        int size = union.size();
        if (size == 1) return false;

        while (!union.isEmpty()) {
            int[] country = union.poll();
            map[country[0]][country[1]] = sum / size;
        }

        return true;
    }
}
