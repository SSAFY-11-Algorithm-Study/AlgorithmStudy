import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726 {

    static int N, M, map[][];
    static int[][] d = { {}, { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
    // 오른쪽으로 회전 : 북(4) -> 동(1) -> 남(3) -> 서(2)
    // 왼쪽으로 회전 : 북(4) -> 서(2) -> 남(3) -> 동(1)
    static int[][] dd = { {}, { 0, 0, 2, 1, 1 }, { 0, 2, 0, 1, 1, }, { 0, 1, 1, 0, 2 }, { 0, 1, 1, 2, 0 } };
    /*
     * //- 동 서 남 북
     * 1 동 0 2 1 1
     * 2 서 2 0 1 1
     * 3 남 1 1 0 2
     * 4 북 1 1 2 0
     */
    static Position start, end; 

    static class Position {
        int x, y, dir, cnt;

        public Position(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 길이
        M = Integer.parseInt(st.nextToken()); // 가로 길이
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        String[] input = (br.readLine()).split(" ");
        start = new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), 0); // 시작점
        input = (br.readLine()).split(" ");
        end = new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), 0); // 끝점

        Queue<Position> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][N + 1][M + 1]; // 방향 4가지, x좌표, y좌표
        q.offer(start);
        visited[start.dir][start.x][start.y] = true;
        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Position cur = q.poll();
            if (cur.x == end.x && cur.y == end.y) {
                answer = cur.cnt + dd[cur.dir][end.dir]; // 마지막 위치의 방향 확인 후 끝내기
                break;
            }

            // 명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
            go: for (int j = 1; j <= 3; j++) {
                int dx = cur.x + d[cur.dir][0] * j;
                int dy = cur.y + d[cur.dir][1] * j;
                if (dx >= 1 && dx <= N && dy >= 1 && dy <= M && map[dx][dy] == 0) {
                    if (!visited[cur.dir][dx][dy]) {
                        visited[cur.dir][dx][dy] = true;
                        q.offer(new Position(dx, dy, cur.dir, cur.cnt + 1)); // 몇 칸을 이동하든 cnt는 1만큼 증가
                    }
                    // 방문을 했어도 바로 끝낼 필요는 없다. 3칸 이동할 때, 2번째 칸이 방문했어도 3번째 칸이 최종 목적지이기 때문
                } else { // map 밖으로 나가거나 1을 만난 경우는 끝내야 한다
                    break go;
                }
            }

            // 명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
            for (int i = 1; i <= 4; i++) {
                if (!visited[i][cur.x][cur.y]) {
                    visited[i][cur.x][cur.y] = true;
                    q.offer(new Position(cur.x, cur.y, i, cur.cnt + dd[cur.dir][i]));
                }
            }
        }
      
        System.out.println(answer); // 답 출력
    }
}
