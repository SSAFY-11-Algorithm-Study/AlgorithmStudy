// 출처: 백준저지 1783번 병든 나이트 https://www.acmicpc.net/problem/1783

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class BOJ1783 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 세로. N과 M은 2,000,000,000보다 작거나 같은 자연수
        int M = Integer.parseInt(st.nextToken()); // 가로
        int answer = 0; // 답

        if (N == 1 || M == 1) { // 세로 또는 가로가 1이라면 무조건 1칸이다
            answer = 1;
        } else if (N == 2) { // 세로가 2라면, 2줄마다 1칸씩 이동하는데 최대 4칸까지 가능하다
            answer = (M + 1) / 2;
            if (answer > 4) {
                answer = 4;
            }
        } else if (M < 7) { // 가로가 7보다 작은 경우, 최대 4칸은 이동한다
            if (M > 4) {
                answer = 4;
            } else {
                answer = M;
            }
        } else { // 그 외의 경우
            // 처음 6줄은 2칸을 제외한 4칸을 방문한다. 그 이후부터는 매번 모든 줄마다 1칸씩은 방문한다
            answer = M - 2;
        }

        bw.write(answer + "\n"); // 답 출력
        bw.flush();
        bw.close();

    }
}
