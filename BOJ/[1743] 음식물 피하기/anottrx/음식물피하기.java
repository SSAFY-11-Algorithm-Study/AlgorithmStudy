import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1743 {

    static int N, M, K, max, cnt;
    static char[][] map;
    static boolean[][] visited;
    static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) { // 통로 초기화
            for (int j = 0; j < M; j++) {
                map[i][j] = '.';
            }
        }
        for (int i = 0; i < K; i++) { // 입력 받기. 0,0부터 시작
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = '#';
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#' && !visited[i][j]) {
                    cnt = 1;
                    getFoodCnt(i, j);
                    max = Math.max(max, cnt);
                }
            }
        }
        System.out.println(max); // 답
    }

    private static void getFoodCnt(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int dx = x + d[i][0];
            int dy = y + d[i][1];
            if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == '#' && !visited[dx][dy]) {
                visited[dx][dy] = true;
                cnt++;
                getFoodCnt(dx, dy);
            }
        }
    }
}
