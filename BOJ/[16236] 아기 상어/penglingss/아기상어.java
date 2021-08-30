import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj16236 {
    static int N, fishX, fishY, minDist, eatCnt, answer;
    static int[][] map, dist;
    static int[] baby; // {x, y, size}
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        baby = new int[3];
        eatCnt = 0;
        answer = 0;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    baby[0] = i;
                    baby[1] = j;
                    baby[2] = 2;
                    map[i][j] = 0;
                }
            }
        }

        while (true) { // 먹이가 없을때까지
            fishX = N; // 먹을 물고기 x위치 초기화
            fishY = N; // 먹을 물고기 y위치 초기화
            minDist = Integer.MAX_VALUE; // 먹을 물고기 거리 초기화
            dist = new int[N][N];

            bfs(); // 먹이 찾기

            if (fishX != N && fishY != N) { // 먹이가 있음
                map[fishX][fishY] = 0;
                baby[0] = fishX; // 아기 상어 위치는 먹은 물고기 자리로
                baby[1] = fishY;
                answer += dist[fishX][fishY];

                eatCnt++; // 먹은 수 ++
                if (eatCnt == baby[2]) { // 자신의 크기와 같은 수의 물고기를 먹었다면
                    eatCnt = 0;
                    baby[2]++; // 크기 1 증가
                }
            } else {
                break;
            }
        }
        System.out.println(answer);
    }

    private static void bfs() { // 가장 가까운 먹이 찾기
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{baby[0], baby[1]});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if (nextX < N && nextX >= 0 && nextY < N && nextY >= 0 // index 범위 안이고
                        && map[nextX][nextY] <= baby[2] && dist[nextX][nextY] == 0) { // 갈수있다면
                    dist[nextX][nextY] = dist[cur[0]][cur[1]] + 1; // 거리 + 1
                    if (map[nextX][nextY] != 0 && map[nextX][nextY] < baby[2]) { // 먹을수 있다면
                        if (minDist > dist[nextX][nextY]) { // 거리 갱신
                            minDist = dist[nextX][nextY];
                            fishX = nextX;
                            fishY = nextY;
                        } else if (minDist == dist[nextX][nextY]) {

                            if (fishX > nextX) {
                                fishX = nextX;
                                fishY = nextY;
                            } else if (fishX == nextX) {
                                if (fishY > nextY) {
                                    fishY = nextY;
                                }
                            }
                        }
                    }
                    q.add(new int[]{nextX, nextY});
                }
            }
        }
    }
}
