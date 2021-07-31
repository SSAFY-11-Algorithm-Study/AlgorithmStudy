// 출처: 백준저지 7568번 덩치 https://www.acmicpc.net/problem/7568

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] arsg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 전체 사람의 수 N (2 ≤ N ≤ 50)
        int[][] xy = new int[N][2]; // 각 사람의 몸무게와 키 (10 ≤ x, y ≤ 200)

        for (int i = 0; i < N; i++) { // N개의 줄에는 각 사람의 몸무게와 키를 나타내는 양의 정수 x와 y가 하나의 공백을 두고 각각 나타난다.
            String input = br.readLine();
            xy[i][0] = Integer.parseInt(input.split(" ")[0]);
            xy[i][1] = Integer.parseInt(input.split(" ")[1]);
        }

        for (int i = 0; i < N; i++) {
            int count = 1; // 등수는 가장 작은 수인 1로 초기화
            for (int j = 0; j < N; j++) {
                if (i != j) { // 해당 사람을 제외한 나머지의 사람들과 모두 비교
                    if ((xy[i][0] < xy[j][0]) && (xy[i][1] < xy[j][1])) { // 해당 사람보다 키와 덩치 모두 큰 경우만
                        count++; // 등수에 1을 더한다
                    }
                }
            }
            bw.write(count + " "); // 답 출력
        }
        bw.flush();
        bw.close();
    }
}
