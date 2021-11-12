import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, min, blankCount;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        min = Integer.MAX_VALUE;
        blankCount = 0;
        map = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) blankCount++;
            }
        }

        activateVirus(0, 0);

        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    private static void activateVirus(int cnt, int start) {
        if (cnt == M) {
            int time = spreadTime();
            if (time == -1) return;
            min = Math.min(min, time);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = i * N + j;
                if (index >= start && map[i][j] == 2) {
                    map[i][j] = -1;
                    activateVirus(cnt + 1, index);
                    map[i][j] = 2;
                }
            }
        }
    }

    private static int spreadTime() {
        int time = -1;
        int spreadAreaCount = 0;
        Queue<int[]> q = new LinkedList<>();

        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
                if (map[i][j] == -1) q.add(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {

            if (time >= min) return -1;

            int size = q.size();
            time++;

            if (spreadAreaCount == blankCount) break;

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nextR = cur[0] + dr[j];
                    int nextC = cur[1] + dc[j];

                    if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < N && (copyMap[nextR][nextC] == 0 || copyMap[nextR][nextC] == 2)) {
                        if (copyMap[nextR][nextC] == 0) spreadAreaCount++;
                        copyMap[nextR][nextC] = -1;
                        q.add(new int[]{nextR, nextC});
                    }
                }
            }
        }

        if (spreadAreaCount != blankCount) return -1;

        return time;
    }
}
