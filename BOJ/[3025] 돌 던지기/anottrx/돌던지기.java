// 시간초과 난 코드입니다...

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ3025 {
    static char[][] map;
    static int R, C, N; // 행 개수 R, 열 개수 C, 입력 개수 N

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken()) - 1; // (0,0)이 시작이기 때문에 입력받은 뒤 1을 뺸다
            goDown(0, startY); // 매번 실행. 0번째 줄부터 위치는 startY에서.
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) { // 답 출력
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n"); // 각 줄마다 다음줄로 이동하기
        }
        bw.write(sb + "\n"); // 답 출력
        bw.flush();
        bw.close();
    }

    private static void goDown(int startX, int startY) {
        if (startX == R) { // 마지막 줄이면
            if (map[startX - 1][startY] == '.') { // 해당 위치가 .이라면 O으로 바꾸고 끝
                map[startX - 1][startY] = 'O';
                return;
            } else {

            }
        } else if ((startX >= 0 && startX < R) && (map[startX][startY] == 'X')) {
            // 다음이 X라면 O으로 해당 위치 바꾸고 끝
            map[startX - 1][startY] = 'O';
            return;
        } else if ((startX >= 0) && (startX < R) && (map[startX][startY] == 'O')) { // 다음줄이 O일 때
            if (startY - 1 >= 0) { // 왼쪽 확인
                if (map[startX][startY - 1] == '.') { // 다음줄 왼쪽이 .이라면 이동
                    startY--; // 왼쪽으로 이동
                } else if ((startY + 1 < C) && (map[startX][startY + 1] == '.') && map[startX - 1][startY + 1] == '.') {
                    // 다음줄 오른쪽이 .이라면 이동
                    startY++; // 오른쪽으로 이동
                } else {
                    map[startX - 1][startY] = 'O'; // 해당 위치 O으로 바꾸고 끝
                    return;
                }
            } else if (startY + 1 < R) {
                if (map[startX][startY + 1] == '.' && map[startX - 1][startY + 1] == '.') {
                    // 다음줄 오른쪽이 .이라면 이동
                    startY++;
                } else {
                    map[startX - 1][startY] = 'O'; // 해당 위치 O으로 바꾸고 끝
                    return;
                }
            }
        }
        startX++; // 다음 줄로 이동
        goDown(startX, startY); // 다음으로 이동

    }

}
