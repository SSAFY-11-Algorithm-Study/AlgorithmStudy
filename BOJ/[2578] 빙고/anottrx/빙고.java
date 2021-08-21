// 출처: 백준저지 2578번 빙고 https://www.acmicpc.net/problem/2578

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class BOJ2578 {

    static int[][] bingo = new int[5][5]; // 철수가 빙고판에 쓴 수들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int answer = 0;
        for (int i = 0; i < 5; i++) { // 빙고판 입력받아 저장
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean result = false;
        boolean check = false;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                int bNum = Integer.parseInt(st.nextToken());
                if (!check) { // 아직 빙고 3개가 안 나왔다면
                    findNumber(bNum); // 빙고판에서 사회자가 입력한 숫자 찾아 0으로 바꾸기
                    check = checkBingo(); // 빙고 개수 세러 가기
                }
                if (check && !result) { // 빙고가 3개가 처음 나온 순간
                    answer = i * 5 + j + 1; // 답 저장
                    result = true; // 앞으로 불필요한 과정 멈추기 위해 true로 바꿔주기
                    check = true;
                }
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    private static void findNumber(int num) { // 사회자가 입력한 숫자 찾아 0으로 바꾸기
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == num) {
                    bingo[i][j] = 0;
                    return;
                }
            }
        }
    }

    private static boolean checkBingo() {
        for (int k = 0; k < 25; k++) {
            int count = 0; // 빙고 선 개수
            // 1. 가로 빙고
            for (int i = 0; i < 5; i++) {
                int row = 0;
                checkRow: for (int j = 0; j < 5; j++) {
                    if (bingo[i][j] == 0) {
                        row++;
                    } else {
                        break checkRow;
                    }
                }
                if (row == 5) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            // 2. 세로 빙고
            for (int i = 0; i < 5; i++) {
                int column = 0;
                checkColumn: for (int j = 0; j < 5; j++) {
                    if (bingo[j][i] == 0) {
                        column++;
                    } else {
                        break checkColumn;
                    }
                }
                if (column == 5) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
            // 3. 대각선 빙고
            if (bingo[0][0] == 0 && bingo[1][1] == 0 && bingo[2][2] == 0 && bingo[3][3] == 0 && bingo[4][4] == 0) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else if (bingo[0][4] == 0 && bingo[1][3] == 0 && bingo[2][2] == 0 && bingo[3][1] == 0
                    && bingo[4][0] == 0) {
                count++;
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
