// https://jaimemin.tistory.com/742

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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
                scores[nth - 1] = score;
            }
            int nth = 1;
            int nthScore = scores[0];
            for (int i = 0; i < N; i++) {
                if (scores[i] < nthScore) {
                    nth++;
                    nthScore = scores[i];
                }
            }
            System.out.println(nth);
        }
    }
}
