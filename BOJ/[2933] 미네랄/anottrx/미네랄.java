import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2933 {

    static int R, C;
    static char[][] map;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    static int bottom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = (br.readLine()).toCharArray();
        }
        int N = Integer.parseInt(br.readLine());
        boolean isLeft = true; // true:왼쪽->오른쪽, false:오른쪽->왼쪽
        st = new StringTokenizer(br.readLine());
        for (int tc = 0; tc < N; tc++) {
            // 입력시 높이 1은 행렬의 가장 바닥, R은 가장 위를 의미 -> map은 왼쪽 맨 위가 0,0이므로 바꾸기
            int H = R - Integer.parseInt(st.nextToken());
            breakAndDrop(H, isLeft); // H번째 줄에 방향에 맞춰 막대를 던지기
            isLeft = !isLeft;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) { // 출력
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.substring(0, sb.length() - 1)); // 마지막 빈 줄은 출력 안 하기
    }

    private static void breakAndDrop(int x, boolean isLeft) {
        // 미네랄 파괴하기
        int y = -1;
        for (int i = 0; i < C; i++) {
            if (map[x][i] == 'x') { // 미네랄 발견
                y = i;
                if (isLeft) { // 왼쪽->오른쪽인 경우 바로 끝내기. 오른쪽->왼쪽 경우라면 계속 확인하기
                    break;
                }
            }
        }
        if (y == -1) { // x번째 줄에 미네랄이 없는 경우 리턴하고 다음 막대를 던진다
            return;
        }
        map[x][y] = '.'; // 막대에 부딪혀 파괴된 미네랄은 .으로 바꾸기

        // 새로 클러스터가 생성되었는지 확인, 만약 떠 있는 클러스터가 존재한다면 아래로 떨어뜨리기
        for (int i = 0; i < 4; i++) { // 4방 탐색
            int dx = x + d[i][0];
            int dy = y + d[i][1];
            if (dx >= 0 && dx < R && dy >= 0 && dy < C && map[dx][dy] == 'x') {
                boolean[][] visited = new boolean[R][C];
                ArrayList<int[]> cluster = new ArrayList<>(); // 클러스터 위치 모음
                bottom = 0; // 미네랄 클러스터의 가장 아래부분
                checkCluster(dx, dy, visited, cluster); // dfs로 클러스터 구하기

                if (bottom != R - 1) { // 찾은 클러스터의 맨 아래부분이 바닥에 닿지 않았다면 클러스터는 아래로 내려갈 수 있다
                    for (int[] cur : cluster) { // 클러스터를 우선 .으로 바꾸기
                        map[cur[0]][cur[1]] = '.';
                    }
                    int downCnt = 1; // 클러스터가 얼마나 아래로 내려갈 수 있는지 계산
                    goDown: while (true) {
                        for (int[] cur : cluster) { // 클러스터의 모든 위치를 확인
                            if (cur[0] + downCnt >= R || map[cur[0] + downCnt][cur[1]] == 'x') {
                                // 범위 밖으로 나가거나 x를 만났다면 더 내려갈 수 없는 것
                                downCnt--; // 못 내려가는 상태이기 때문에 1 빼기
                                break goDown;
                            }
                        }
                        downCnt++;
                    }
                    for (int[] cur : cluster) {
                        map[cur[0] + downCnt][cur[1]] = 'x'; // 클러스터의 모든 위치를 downCnt만큼 아래로 내리기
                    }
                    return; // 두 개 또는 그 이상의 클러스터가 동시에 떨어지는 경우도 없기 때문에 그냥 리턴해서 다음 막대 던지기
                }
            }
        }
    }

    private static void checkCluster(int x, int y, boolean[][] visited, ArrayList<int[]> cluster) { // dfs
        visited[x][y] = true;
        cluster.add(new int[] { x, y });
        bottom = Math.max(bottom, x);

        for (int dd = 0; dd < 4; dd++) {
            int dx = x + d[dd][0];
            int dy = y + d[dd][1];
            if (dx >= 0 && dx < R && dy >= 0 && dy < C && !visited[dx][dy] && map[dx][dy] == 'x') {
                checkCluster(dx, dy, visited, cluster);
            }
        }
    }
}
