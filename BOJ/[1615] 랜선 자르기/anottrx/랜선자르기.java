// 출처: 백준저지 1654번 랜선 자르기 https://www.acmicpc.net/problem/1654

/*
https://st-lab.tistory.com/269
https://wootool.tistory.com/114
바탕으로 풀었습니다.
*/

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1654 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        long lan[] = new long[K];
        for (int i = 0; i < K; i++) {
            lan[i] = sc.nextInt();
        }
        Arrays.sort(lan);

        long start = 1;
        long end = lan[K - 1] + 1;
        long cnt = K;
        long max = 0;

        while (true) {
            cnt = 0;
            long len = (start + end) / 2;
            for (int i = 0; i < K; i++) {
                cnt = cnt + (lan[i] / len);
            }
            if (cnt < N) {
                end = len - 1;
            } else {
                start = len + 1;
                max = Math.max(max, len);
            }

            if (start > end) {
                break;
            }
        }
        System.out.println(max);
    }
}
