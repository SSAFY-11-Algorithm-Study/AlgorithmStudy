// https://12216715011126.tistory.com/23 보고 풀었습니다

import java.util.Scanner;

public class BOJ2448 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] tree = new String[N];
        tree[0] = "  *  ";
        tree[1] = " * * ";
        tree[2] = "*****";
      
        for (int i = 1; 3 * (int) Math.pow(2, i) <= N; i++) {
            int end = 3 * (int) Math.pow(2, i), start = end / 2;
            int k = 0;
            String blank = "";

            for (int l = start; l < end; l++) {
                tree[l] = tree[k] + " " + tree[k];
                k++;
            }

            for (int l = 0; l < start; l++) {
                blank = blank + " ";
            }

            for (int l = 0; l < start; l++) {
                tree[l] = blank + tree[l] + blank;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(tree[i]);
        }
    }
}
