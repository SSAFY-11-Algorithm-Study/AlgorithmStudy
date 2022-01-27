// https://st-lab.tistory.com/121

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int[] cnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        dfs(num[0], 1, num, cnt);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int n, int nth, int[] num, int[] cnt) {
        if (nth == N) {
            max = Math.max(max, n);
            min = Math.min(min, n);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;

                if (i == 0)
                    dfs(n + num[nth], nth + 1, num, cnt);
                else if (i == 1)
                    dfs(n - num[nth], nth + 1, num, cnt);
                else if (i == 2)
                    dfs(n * num[nth], nth + 1, num, cnt);
                else if (i == 3)
                    dfs(n / num[nth], nth + 1, num, cnt);

                cnt[i]++;
            }

        }
    }

}
