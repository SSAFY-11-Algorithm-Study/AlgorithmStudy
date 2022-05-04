import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], move[][];
    static int[][] d = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            move = new int[2][2];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                move[i] = new int[]{x, y};
            }
            int result = countMove();
            sb.append(result).append("\n");
        }

        System.out.println(sb.substring(0, sb.length() - 1).toString());
    }

    private static int countMove() {
        int result = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[]{move[0][0], move[0][1], 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == move[1][0] && cur[1] == move[1][1]) {
                result = cur[2];
                break;
            }

            for (int i = 0; i < 8; i++) {
                int dx = cur[0] + d[i][0];
                int dy = cur[1] + d[i][1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    q.add(new int[]{dx, dy, cur[2] + 1});
                }
            }
        }

        return result;
    }
}
