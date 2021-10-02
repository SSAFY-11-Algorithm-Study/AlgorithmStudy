import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] dp = new long[N + 1];

        for (int i = 1; i < N + 1; i++) {
            dp[i] = i;
            if (i > 6) {
                for (int j = 2; j < 5; j++) {
                    dp[i] = Math.max(dp[i], dp[i - (j + 1)] * j);
                }
            }
        }
        System.out.println(dp[N]);
    }
}
