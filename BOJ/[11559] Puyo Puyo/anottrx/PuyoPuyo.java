import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559 {

    static char[][] map;
    static int N = 12, M = 6;
    static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = (br.readLine()).toCharArray();
        }
        int answer = 0;
        while (true) {
            boolean[][] visited = new boolean[N][M];
            ArrayList<ArrayList<int[]>> puyoAllList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        ArrayList<int[]> puyo4List = new ArrayList<>();
                        visited[i][j] = true;
                        puyo4List.add(new int[] { i, j }); // 우선 현재 위치를 뿌요4 리스트에 담기
                        getPuyo(i, j, map[i][j], puyo4List, visited); // bfs
                        if (puyo4List.size() >= 4) { // 뿌요4 리스트에 뿌요가 4개 이상이라면 최종 리스트에 담기
                            puyoAllList.add(puyo4List);
                        }
                    }
                }
            }

            if (puyoAllList.size() > 0) { // 뿌요 최종 리스트 크기가 0보다 크다면 터질 그룹이 있는 것
                for (ArrayList<int[]> curList : puyoAllList) {
                    for (int[] cur : curList) {
                        map[cur[0]][cur[1]] = '.'; // .으로 바꾸기
                    }
                }
                moveAll(); // 위에 떠 있는 뿌요 찾아서 아래로 떨구기
                answer++; // 연쇄 1 추가
            } else {
                break;
            }
        }
        System.out.println(answer);
    }

    private static void getPuyo(int x, int y, char color, ArrayList<int[]> puyo4List, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int dx = cur[0] + d[i][0];
                int dy = cur[1] + d[i][1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < M && !visited[dx][dy] && map[dx][dy] == color) {
                    // 맵 안에 있고 방문 안 했고 색상이 같다면 뿌요4 리스트에 담기
                    q.offer(new int[] { dx, dy });
                    puyo4List.add(new int[] { dx, dy });
                    visited[dx][dy] = true;
                }
            }
        }
    }

    private static void moveAll() { // 공중에 떠 있는 뿌요 아래로 내리기
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == '.') { // .을 만나면 현 위치부터 뿌요 만날 때까지 위로 쭉 확인하기
                    checking: for (int k = i; k >= 0; k--) {
                        if (map[k][j] != '.') { // 뿌요 만나면 . 위치에 뿌요 넣고 뿌요 위치는 .으로 바꾸기
                            map[i][j] = map[k][j];
                            map[k][j] = '.';
                            break checking;
                        }
                    }
                }
            }
        }
    }
}
