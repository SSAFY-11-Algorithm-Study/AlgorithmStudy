// 여러 블로그를 참고했습니다

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_2 {

    static int N, answer;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
    static int[][] map;
    static int[][] dist;
    static int sharkX, sharkY, sharkSize, sharkEatCnt;

    private static class Position {
        private int x;
        private int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i; // 상어 위치
                    sharkY = j;
                    map[i][j] = 0;
                    sharkSize = 2; // 상어 크기
                }
            }
        }

        while (true) {
            dist = new int[N + 1][N + 1]; // 상어의 현재 위치에서 얼마나 떨어졌는지 계산하기 위함. 매번 초기과
            int minX = Integer.MAX_VALUE; // x 최솟값
            int minY = Integer.MAX_VALUE; // y 최솟값
            int minDist = Integer.MAX_VALUE; // 최소거리

            Queue<Position> q = new LinkedList<>();
            q.add(new Position(sharkX, sharkY));

            while (!q.isEmpty()) {
                Position pos = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = pos.x + d[i][0];
                    int ny = pos.y + d[i][1];

                    if (nx > 0 && nx <= N && ny <= N && ny > 0 // map 안에 있고
                            && sharkSize >= map[nx][ny] // 상어 크기가 크거나 같아서 이동할 수 있고
                            && dist[nx][ny] == 0) { // 아직 간 적이 없다면
                        dist[nx][ny] = dist[pos.x][pos.y] + 1; // 현재 위치에서 1 더한 거리가 된다

                        if (map[nx][ny] != 0 && sharkSize > map[nx][ny]) { // 해당 위치에 상어가 먹을 수 있는 물고기가 있다면
                            if (dist[nx][ny] < minDist) { // 최소거리보다 짧다면
                                minDist = dist[nx][ny]; // 최소거리, x, y 모두 바꾸기
                                minX = nx;
                                minY = ny;
                            } else if (dist[nx][ny] == minDist) { // 최소거리와 동일하다면
                                if ((nx < minX) || ((nx == minX) && (ny < minY))) {
                                    // x가 더 작다면, 또는 x는 같은데 y가 작다면 x,y 바꾸기
                                    // 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
                                    minX = nx;
                                    minY = ny;
                                }
                            }
                        }

                        q.add(new Position(nx, ny));
                    }
                }
            }

            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                sharkEatCnt++; // 상어가 먹은 것 개수 + 1
                map[minX][minY] = 0; // 상어 위치 이동
                sharkX = minX;
                sharkY = minY;
                answer = answer + dist[minX][minY]; // 거리 계산

                if (sharkEatCnt == sharkSize) {
                    sharkSize++;
                    sharkEatCnt = 0;
                }
            } else { // 더 이동하지 못하면 끝
                break;
            }
        }
        System.out.println(answer);
    }
}
