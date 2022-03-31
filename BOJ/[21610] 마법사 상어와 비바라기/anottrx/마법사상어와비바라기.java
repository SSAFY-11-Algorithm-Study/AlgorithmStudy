import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, map[][];
    static int[][] d = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static ArrayList<int[]> arrList = new ArrayList<>(); // [x좌표, y좌표]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        arrList.add(new int[]{N, 1});
        arrList.add(new int[]{N, 2});
        arrList.add(new int[]{N - 1, 1});
        arrList.add(new int[]{N - 1, 2});
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            solve(dir, s);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    private static void solve(int dir, int s) {
        ArrayList<int[]> changedList = new ArrayList<>();

        // 구름이 dir 방향으로 s만큼 이동
        for (int[] cur : arrList) {
            int dx = (cur[0] + d[dir][0] * s) % N;
            if (dx < 0) {
                dx = dx + (dx * (-1) / N + 1) * N;
            }
            if (dx == 0) {
                dx = N;
            }
            int dy = (cur[1] + d[dir][1] * s) % N;
            if (dy < 0) {
                dy = dy + (dy * (-1) / N + 1) * N;
            }
            if (dy == 0) {
                dy = N;
            }
            changedList.add(new int[]{dx, dy});
        }

        // 구름이 있는 칸에 비가 1씩 내린다
        for (int[] cur : changedList) {
            map[cur[0]][cur[1]] = map[cur[0]][cur[1]] + 1;
        }

        // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가
        for (int[] cur : changedList) {
            int cnt = 0;
            for (int i = 2; i <= 8; i = i + 2) {
                int dx = cur[0] + d[i][0];
                int dy = cur[1] + d[i][1];
                if (dx >= 1 && dx <= N && dy >= 1 && dy <= N && map[dx][dy] > 0) {
                    cnt++;
                }
            }
            map[cur[0]][cur[1]] = map[cur[0]][cur[1]] + cnt;
        }

        // 구름이 있었던 칸을 제외한 나머지 칸 중에서 물의 양이 2 이상인 칸에 구름이 생긴다. 구름이 생기면 물의 양이 2만큼 줄어든다.
        arrList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] >= 2) {
                    boolean wasCloud = false;
                    for (int k = 0; k < changedList.size(); k++) {
                        if (changedList.get(k)[0] == i && changedList.get(k)[1] == j) {
                            changedList.remove(k);
                            wasCloud = true;
                            break;
                        }
                    }
                    if (!wasCloud) {
                        map[i][j] = map[i][j] - 2;
                        arrList.add(new int[]{i, j});
                    }
                }
            }
        }
    }
}
