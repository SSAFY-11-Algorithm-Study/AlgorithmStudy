// 좋은수열인지 아닌지 판단하는 함수를 아직 못 구현했습니다

import java.util.Scanner;

public class BOJ2661 {

    static int M;
    static String answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        M = (N % 2 == 0) ? (N / 2) : (N / 2 + 1);
        dfs(1, "1", 1);
        System.out.println(answer);
    }

    private static void dfs(int len, String str, int last) {
        if (len == M) {
            for (int i = 0; i < M; i++) {
 
            }
            return;
        }
        if (last == 1) {
            dfs(len + 1, str + "2", 2);
            dfs(len + 1, str + "3", 3);
        } else if (last == 2) {
            dfs(len + 1, str + "1", 1);
            dfs(len + 1, str + "3", 3);
        } else {
            dfs(len + 1, str + "1", 1);
            dfs(len + 1, str + "2", 2);
        }
    }
}
