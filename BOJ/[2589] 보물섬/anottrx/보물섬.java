import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589 {

    static int N, M;
    static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = (br.readLine()).toCharArray();

        }

        int max = 0; // 최댓값 -> 답
        int cntL = 0; // L의 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    max = Math.max(max, getCount(i, j, map));
                    cntL++;
                }
            }
        }

        max = (cntL == 1) ? 1 : max; // L이 1개라면 답은 1, 그외는 max가 답
        System.out.println(max);
    }

    private static int getCount(int x, int y, char[][] map) {
        int cnt = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y });
        visited[x][y] = true;
        boolean lastVisited = false; // 맵 안에서 새롭게 이동을 했는지 확인하기 위함

        while (!q.isEmpty()) {
            int qSize = q.size();
            lastVisited = false;

            for (int j = 0; j < qSize; j++) { // 큐 사이즈만큼 반복
                int[] cur = q.poll();
                x = cur[0];
                y = cur[1];

                for (int i = 0; i < 4; i++) {
                    int dx = x + d[i][0];
                    int dy = y + d[i][1];
                    if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy] && map[dx][dy] == 'L') {
                        visited[dx][dy] = true;
                        q.offer(new int[] { dx, dy });
                        lastVisited = true; // 새롭게 방문을 했다고 표시 -> 이동을 했으므로
                    }
                }
            }

            if (lastVisited) { // 방문을 새로 한 경우만 cnt 증가
                cnt++;
            }
        }

        return cnt;
    }
}
