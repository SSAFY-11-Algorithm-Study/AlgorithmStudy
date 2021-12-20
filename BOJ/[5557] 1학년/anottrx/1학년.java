// https://118k.tistory.com/551 보고 풀었습니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        long[][] memo = new long[21][N + 1];
        memo[num[1]][1] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (memo[j][i] != 0) {
                    if (j + num[i + 1] <= 20) {
                        memo[j + num[i + 1]][i + 1] = memo[j + num[i + 1]][i + 1] + memo[j][i];
                    }
                    if (j - num[i + 1] >= 0) {
                        memo[j - num[i + 1]][i + 1] = memo[j - num[i + 1]][i + 1] + memo[j][i];
                    }
                }
            }
        }

        System.out.println(memo[num[N]][N - 1]);
    }
}
