import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R, C, answer;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> fireList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        answer = -1;
        map = new char[R][C];
        int startR = 0;
        int startC = 0;
        fireList = new LinkedList<>();
        sc.nextLine();
        for (int i = 0; i < R; i++) {
            map[i] = sc.nextLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == 'F') {
                    fireList.add(new int[]{i, j});
                }
            }
        }
        bfs(startR, startC);
        if (answer != -1) {
            System.out.println(answer);
        }
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];
        q.add(new int[]{r, c});
        visit[r][c] = true;
        int time = 0;
        while (!q.isEmpty()) {
            time++;
            fireSpread();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (cur[0] == R - 1 || cur[1] == C - 1 || cur[0] == 0 || cur[1] == 0) {
                    answer = time;
                    return;
                }
                for (int j = 0; j < 4; j++) {
                    int nextR = cur[0] + dr[j];
                    int nextC = cur[1] + dc[j];
                    if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || visit[nextR][nextC]) continue;
                    if (map[nextR][nextC] == '.') {
                        q.add(new int[]{nextR, nextC});
                        visit[nextR][nextC] = true;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    private static void fireSpread() {
        int size = fireList.size();
        for (int i = 0; i < size; i++) {
            int[] fire = fireList.poll();
            for (int j = 0; j < 4; j++) {
                int nextR = fire[0] + dr[j];
                int nextC = fire[1] + dc[j];
                if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) continue;
                if (map[nextR][nextC] == '.') {
                    map[nextR][nextC] = 'F';
                    fireList.add(new int[]{nextR, nextC});
                }
            }
        }
    }
}
