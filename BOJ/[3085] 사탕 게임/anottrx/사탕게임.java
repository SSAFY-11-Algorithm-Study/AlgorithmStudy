// 출처: 백준저지 3085번 사탕 게임 https://www.acmicpc.net/problem/3085

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3085_3 {
    static int N, max, cnt;
    static char[][] map;
    static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 위 아래 왼쪽 오른쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) { // 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i] = (st.nextToken()).toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int dd = 0; dd < 4; dd++) { // 해당 사탕의 위 아래 오른쪽 왼쪽 모두 바꿔보기
                    int dx = i + d[dd][0];
                    int dy = j + d[dd][1];

                    if (isInMap(dx, dy)) {
                        swap(i, j, dx, dy); // 바꾸기
                        char[] col = new char[N]; // 해당 위치의 세로를 일차원 char 배열형태로 입력
                        for (int k = 0; k < N; k++) {
                            col[k] = map[k][j];
                        }
                        char[] row = new char[N]; // 해당 위치의 가로를 일차원 char 배열형태로 입력
                        for (int k = 0; k < N; k++) {
                            row[k] = map[i][k];
                        }
                        swap(dx, dy, i, j); // 원래대로 돌려놓기

                        max = Math.max(max, checkRowCol(row));
                        max = Math.max(max, checkRowCol(col));
                    }
                }
            }
        }
        System.out.println(max); // 답 출력
    }

    private static int checkRowCol(char[] rowcol) { // 일차원 배열에서 가장 많이 연속된 사탕 수 구하기
        int cnt = 1;
        int bigger = 0;
        for (int i = 0; i < N - 1; i++) {
            if (rowcol[i] == rowcol[i + 1]) {
                cnt++;
            } else {
                bigger = Math.max(bigger, cnt);
                cnt = 1;
            }
        }
        bigger = Math.max(bigger, cnt);
        return bigger;
    }

    private static boolean isInMap(int x, int y) { // 맵 안에 있는지 확인
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }
        return false;
    }

    private static void swap(int i, int j, int dx, int dy) { // 인접한 두 칸의 사탕 바꾸기
        char temp = map[i][j];
        map[i][j] = map[dx][dy];
        map[dx][dy] = temp;
    }
}
