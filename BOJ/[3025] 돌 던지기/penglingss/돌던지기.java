import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Something {
        int r;
        int c;
        String s;

        public Something(int r, int c, String s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int R;
    static int C;
    static String[][] map;
    static Something[][] obstacle;
    static int[][] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new String[R][C];
        obstacle = new Something[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                updateObstacle(i, j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int column = Integer.parseInt(br.readLine());
            roll(0, column - 1);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void roll(int row, int column) {
        if (row == R - 1) {
            map[row][column] = "O";
            updateObstacle(row, column);
            return;
        }

        Something some = obstacle[row][column];
        int r = some.r;
        int c = some.c;
        String s = some.s;

        if (s.equals("X")) {
            map[r - 1][c] = "O";
            updateObstacle(r - 1, c);
        } else if (s.equals(".")) {
            map[r][c] = "O";
            updateObstacle(r, c);
        } else if (s.equals("O")) {
            if (c - 1 >= 0 && r < R && map[r][c - 1].equals(".")) {
                roll(r, c - 1);
            } else if (c + 1 < C && r < R && map[r][c + 1].equals(".")) {
                roll(r, c + 1);
            } else {
                map[r - 1][c] = "O";
                updateObstacle(r - 1, c);
            }
        }
    }

    private static void updateObstacle(int r, int c) {
        String thing = map[r][c];
        int k = r - 1;
        obstacle[r][c] = new Something(r, c, thing);
        while (k >= 0 && map[k][c].equals(".")) {
            obstacle[k][c] = obstacle[k + 1][c];
            k--;
        }
    }
}
