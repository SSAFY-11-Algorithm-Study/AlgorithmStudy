// https://wonit.tistory.com/540

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111 {
    static int N, M, B;
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int maxHeight = 0, totalTime = Integer.MAX_VALUE;
        for (int k = min; k <= max; k++) {
            int time = 0;
            int bb = B;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > k) {
                        time = time + (map[i][j] - k) * 2;
                        bb = bb + (map[i][j] - k);
                    } else if (map[i][j] < k) {
                        time = time + (k - map[i][j]);
                        bb = bb - (k - map[i][j]);
                    }
                }
            }
            if (bb >= 0) {
                if (totalTime >= time) {
                    totalTime = time;
                    maxHeight = k;
                }
            }
        }
        System.out.println(totalTime + " " + maxHeight);
    }
}
