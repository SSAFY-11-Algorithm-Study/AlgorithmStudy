import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        long N = sc.nextInt();
        long[] lan = new long[K];
        long max = -1;

        for (int i = 0; i < K; i++) {
            lan[i] = sc.nextInt();
            max = Math.max(max, lan[i]); // 최댓값 구하기
        }

        long min = 1; // 랜선의 길이는 자연수

        while (min <= max) { // 이분 탐색
            long length = (max + min) / 2;
            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += lan[i] / length;
            }
            if (cnt >= N) min = length + 1;
            else max = length - 1;
        }

        System.out.println(max);
    }
}
