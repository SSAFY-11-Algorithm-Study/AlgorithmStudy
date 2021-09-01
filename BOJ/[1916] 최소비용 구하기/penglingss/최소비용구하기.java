import java.util.Scanner;

public class Main {
    static int N, M, from, to;
    static int[] dist;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N + 1][N + 1];
        dist = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int cost = sc.nextInt();
            if (map[city1][city2] == Integer.MAX_VALUE) {
                map[city1][city2] = cost;
            } else {
                if (map[city1][city2] > cost) { // 더 작은 비용 저장
                    map[city1][city2] = cost;
                }
            }
        }
        from = sc.nextInt();
        to = sc.nextInt();

        for (int i = 1; i < N + 1; i++) {
            dist[i] = map[from][i]; // 거리 배열 초기화
        }

        dijkstra();

        System.out.println(dist[to]);
    }

    private static void dijkstra() {
        boolean[] visit = new boolean[N + 1];
        visit[from] = true;

        for (int i = 0; i < N - 1; i++) {
            int min = Integer.MAX_VALUE;
            int idx = 0;

            for (int j = 1; j < N + 1; j++) { // 최단 거리 찾기
                if (!visit[j] && dist[j] < min) {
                    idx = j;
                    min = dist[j];
                }
            }

            visit[idx] = true; // 최단 거리 방문

            for (int j = 1; j < N + 1; j++) { // 경유해서 가는게 더 빠르다면 업데이트
                if (!visit[j] && map[idx][j] != Integer.MAX_VALUE && dist[idx] + map[idx][j] < dist[j]) {
                    dist[j] = dist[idx] + map[idx][j];
                }
            }
        }
    }
}
