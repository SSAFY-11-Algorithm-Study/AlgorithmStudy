// https://jaimemin.tistory.com/742

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] scores = new int[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int nth = Integer.parseInt(st.nextToken());
                scores[nth - 1] = score; // 면접 등수 순서대로 점수 입력
            }
            int cnt = 1; // 우선 면접 1등은 무조건 선발할 것
            int bestScore = scores[0]; // 면접 1등의 서류심사 성적
            for (int i = 0; i < N; i++) {
                if (scores[i] < bestScore) { // 면접 1등의 서류 성적보다 더 높다면
                    cnt++; // 이 사람도 뽑을 테니 선발 인원 1 증가
                    bestScore = scores[i]; // 서류 성적 바꾸기
                }
            }
            System.out.println(cnt);
        }
    }
}
