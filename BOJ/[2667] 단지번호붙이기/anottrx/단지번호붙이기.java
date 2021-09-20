import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {
    static int[][] map;
    static int N, cnt;
    static boolean[][] visited;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] inputChar = (br.readLine()).toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = (inputChar[j] - '0' == 1) ? -1 : 0; // 1은 -1로 바꾸기
            }
        }

        ArrayList<Integer> colorCntArr = new ArrayList<>();
        visited = new boolean[N][N];
        int colorNum = 1; // 단지별 번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == -1) {
                    cnt = 0; // 단지수 0으로 초기화
                    colorNumbering(i, j, colorNum); // dfs로 단지별 번호 넣어 칠하기
                    colorCntArr.add(cnt); // 단지수
                    colorNum++;
                }
            }
        }

        Collections.sort(colorCntArr); // 정렬
        bw.write(colorCntArr.size() + "\n");
        for (int i = 0; i < colorCntArr.size(); i++) {
            bw.write(colorCntArr.get(i) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void colorNumbering(int x, int y, int colorNum) {
        visited[x][y] = true;
        map[x][y] = colorNum;
        cnt++;

        for (int dd = 0; dd < 4; dd++) {
            int dx = x + d[dd][0];
            int dy = y + d[dd][1];
            if (isInMap(dx, dy) && !visited[dx][dy] && map[dx][dy] == -1) {
                colorNumbering(dx, dy, colorNum);
            }
        }
    }

    private static boolean isInMap(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }

}
