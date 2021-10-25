import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C]; // 방문 체크. 지훈이와 불 모두 함께 사용

        Queue<Position> Jihoon = new LinkedList<>(); // 지훈이 위치
        Queue<Position> fires = new LinkedList<>(); // 불 위치

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    Jihoon.add(new Position(i, j)); // 지훈이 위치 넣기
                    map[i][j] = '.'; // 지훈이 위치 .으로 바꾸기
                    visited[i][j] = true; // 방문 체크
                } else if (map[i][j] == 'F') {
                    fires.add(new Position(i, j)); // 불 위치 넣기
                    visited[i][j] = true; // 방문 체크
                }
            }
        }
        System.out.println(escape(Jihoon, fires));
    }

    private static String escape(Queue<Position> jihoon, Queue<Position> fires) {
        int cnt = 0;
        boolean isFinished = false;

        while (!jihoon.isEmpty()) {
            moveFire(fires); // 불부터 이동시키기
            isFinished = moveJihoon(jihoon); // 지훈이 이동시키기

            if (isFinished) { // true라면 탈출 성공
                return String.valueOf(cnt + 1); // 벽에서 밖으로 나가는 것 때문에 1 추가해서 리턴
            }

            cnt++; // 시간 증가
        }

        return "IMPOSSIBLE"; // 여기까지 오면 탈출 못했기 때문에 IMPOSSIBLE 리턴
    }

    private static void moveFire(Queue<Position> fires) { // 불 이동시키기. bfs
        int size = fires.size();

        for (int i = 0; i < size; i++) { // 불 큐 사이즈만큼 반복
            Position cur = fires.poll();

            for (int j = 0; j < 4; j++) {
                int dx = cur.x + d[j][0];
                int dy = cur.y + d[j][1];
                if (dx >= 0 && dx < R && dy >= 0 && dy < C && map[dx][dy] == '.' && !visited[dx][dy]) {
                    map[dx][dy] = 'F';
                    fires.add(new Position(dx, dy));
                    visited[dx][dy] = true;
                }
            }
        }
    }

    private static boolean moveJihoon(Queue<Position> jihoon) { // 지훈 이동시키기. bfs
        int size = jihoon.size();

        for (int i = 0; i < size; i++) { // 지훈이 큐 사이즈만큼 반복
            Position cur = jihoon.poll();
            if (cur.x == 0 || cur.x == R - 1 || cur.y == 0 || cur.y == C - 1) { // 미로의 가장자리에 접한 공간에 도달했다면
                return true; // true 리턴해서 끝내기
            }

            for (int j = 0; j < 4; j++) {
                int dx = cur.x + d[j][0];
                int dy = cur.y + d[j][1];
                if (dx >= 0 && dx < R && dy >= 0 && dy < C && map[dx][dy] == '.' && !visited[dx][dy]) {
                    jihoon.add(new Position(dx, dy));
                    visited[dx][dy] = true;
                }
            }
        }
        return false; // 벽에 도착 못했다면 false 리턴
    }
}
