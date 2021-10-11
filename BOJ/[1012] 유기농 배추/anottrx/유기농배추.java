import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int N, M, K, map[][], cnt;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int x = 0;
        int y = 0;

        for (int tc = 0; tc < T; tc++) {
            cnt = 0; // 답이 될 배추흰지렁이 마리 수 초기화
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        cnt++; // 배추흰지렁이 마리 수 증가
                        visited[i][j] = true; // 해당 위치 방문했다고 표시
                        countCabbage(i, j, visited); // 이어진 배추 찾기
                    }
                }
            }
            sb.append(cnt + "\n"); // 출력
        }

        System.out.println(sb.substring(0, sb.length() - 1));
    }

    private static void countCabbage(int x, int y, boolean[][] visited) { // DFS
        for (int i = 0; i < 4; i++) {
            int dx = x + d[i][0];
            int dy = y + d[i][1];
            if (dx >= 0 && dx < N && dy >= 0 && dy < M && map[dx][dy] == 1 && !visited[dx][dy]) {
                // map 안에 있고 배추가 존재하고 아직 방문 안 했다면
                visited[dx][dy] = true; // 해당 위치 방문했다고 표시
                countCabbage(dx, dy, visited); // 이어진 배추 찾기
            }
        }
    }
}
