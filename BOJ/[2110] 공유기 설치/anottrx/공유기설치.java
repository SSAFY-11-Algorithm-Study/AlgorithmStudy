// 푸는 방법은 어느 정도 알게 되었고 이제부터 다시 풀어야 합니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        int left = 1;
        int right = house[N - 1] - house[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 1;
            for (int i = 0; i < mid; i++) {
            }
        }
    }
}
