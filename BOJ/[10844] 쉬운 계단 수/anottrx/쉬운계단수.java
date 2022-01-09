import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MOD = 1000000000;
        sc.close();
        long[][] dp = new long[N + 1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }
        
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
            }
            dp[i][9] = dp[i - 1][8];
        }
        
        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + dp[N][i]) % MOD;
        }
        System.out.println(answer);
    }
}
