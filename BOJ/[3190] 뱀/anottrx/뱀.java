// 테스트케이스도 안 돌아가서 다시 풀어야 합니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public interface BOJ3190 {

    static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    static class Position {
        int x, y, cnt, dir;

        public Position(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            map[n][m] = 2; // 사과 존재
        }
        int L = Integer.parseInt(br.readLine());
        char[] timeDir = new char[10001];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            timeDir[X] = C;
        }

        Queue<ArrayList<Position>> q = new LinkedList<>();
        ArrayList<Position> arrList = new ArrayList<>();
        arrList.add(new Position(1, 1, 0, 1));
        q.offer(arrList);
        game: while (true) {
            arrList = q.poll();
            ArrayList<Position> next = new ArrayList<>();
            boolean meetApple = false;
            int size = arrList.size();
            for (int i = 0; i < size; i++) {
                Position cur = arrList.get(i);
                map[arrList.get(i).x][arrList.get(i).y] = 0;
                int dx = cur.x + d[cur.dir][0];
                int dy = cur.y + d[cur.dir][1];
                int curCnt = cur.cnt;
                int curDir = cur.dir;
                if (timeDir[curCnt] == 'D') {
                    curDir = curDir + 1;
                    if (curDir == 4)
                        curDir = 0;
                } else if (timeDir[curCnt] == 'L') {
                    curDir = curDir - 1;
                    if (curDir == -1)
                        curDir = 3;
                }
                if (dx >= 1 && dx <= N && dy >= 1 && dy <= N) {
                    if (map[dx][dy] == 1) {
                        System.out.println(cur.cnt);
                        break game;
                    }
                    if (map[dx][dy] == 2) {
                        map[dx][dy] = 0;
                        meetApple = true;
                    }
                    map[dx][dy] = 1;
                    next.add(new Position(dx, dy, cur.cnt + 1, curDir));
                } else {
                    System.out.println(cur.cnt);
                    break game;
                }
            }
            if (meetApple) {
                for (int i = 0; i < size; i++) {
                    map[arrList.get(i).x][arrList.get(i).y] = 1;
                    next.add(arrList.get(i));
                }
            }

            q.offer(next);
        }
    }
}
