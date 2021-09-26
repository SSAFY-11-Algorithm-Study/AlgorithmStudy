import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
    static int N, M, map[][], answer;
    static Queue<Position> q; // 바이러스 위치를 저장할 큐
    static ArrayList<Position> arrToPutWall; // 빈칸 위치를 저장할 리스트
    static Position[] selected; // 벽을 세우기 위해 빈칸 3개를 고르고 해당 위치를 저장할 리스트
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static class Position { // 위치
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new LinkedList<>();
        arrToPutWall = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 바이러스 위치 큐에 넣기
                    q.offer(new Position(i, j));
                } else if (map[i][j] == 0) { // 빈 칸 위치 리스트에 넣기
                    arrToPutWall.add(new Position(i, j));
                }
            }
        }

        answer = Integer.MIN_VALUE; // 답은 최소값으로 초기화
        selected = new Position[3]; // 3개의 빈칸을 고를 것
        pickThree(0, 0); // 조합으로 3개의 벽을 놓을 곳 찾기

        System.out.println(answer); // 답 출력
    }

    private static void pickThree(int cnt, int start) {
        if (cnt == 3) {
            int[][] copyMap = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new); // 맵 복사
            for (int i = 0; i < 3; i++) {
                int x = selected[i].x;
                int y = selected[i].y;
                copyMap[x][y] = 1; // 복사된 맵의 빈칸 3군데에 벽을 세우기
            }
            Queue<Position> copyQ = new LinkedList<>(q); // 큐 복사

            spreadVirus(copyQ, copyMap); // 복사된 큐와 맵을 가지고 bfs

            return;
        }

        for (int i = start; i < arrToPutWall.size(); i++) {
            selected[cnt] = arrToPutWall.get(i);
            pickThree(cnt + 1, i + 1);
        }
    }

    private static void spreadVirus(Queue<Position> copyQ, int[][] copyMap) {
        while (!copyQ.isEmpty()) {
            Position pos = copyQ.poll();

            for (int i = 0; i < 4; i++) {
                int dx = pos.x + d[i][0];
                int dy = pos.y + d[i][1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < M && copyMap[dx][dy] == 0) { // 빈칸이 있다면 이동
                    copyMap[dx][dy] = 2;
                    copyQ.offer(new Position(dx, dy));
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cnt += (copyMap[i][j] == 0) ? 1 : 0; // 안전 영역 크기 개수 세기
            }
        }
        answer = Math.max(answer, cnt); // 안전 영역 크기의 최댓값 구하기
    }
}
