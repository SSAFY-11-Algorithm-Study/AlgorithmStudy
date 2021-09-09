import java.util.Scanner;

public class Main {
    static char[][] candies;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        candies = new char[N][N];

        sc.nextLine();
        for (int i = 0; i < N; i++) {
            candies[i] = sc.nextLine().toCharArray();
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j < N - 1) {
                    swapRight(i, j); // 오른쪽 사탕과 바꿈
                    int result = count(); // 숫자 세기
                    max = Math.max(max, result);
                    swapRight(i, j); // 원래대로
                }
                if (i < N - 1) {
                    swapDown(i, j); // 아래 사탕과 바꿈
                    int result = count(); // 숫자 세기
                    max = Math.max(max, result);
                    swapDown(i, j); // 원래대로
                }
            }
        }
        System.out.println(max);
    }

    public static int count() { // 가장 긴 연속된 사탕 수 찾기
        int n = candies.length;
        int result = 1;
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < n - 1; j++) {
                if (candies[i][j] == candies[i][j + 1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                result = Math.max(result, cnt);
            }

            cnt = 1;
            for (int j = 0; j < n - 1; j++) {
                if (candies[j][i] == candies[j + 1][i]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                result = Math.max(result, cnt);
            }
        }
        return result;
    }

    public static void swapRight(int i, int j) {
        if (!(candies[i][j] == candies[i][j + 1])) {
            char tmp = candies[i][j];
            candies[i][j] = candies[i][j + 1];
            candies[i][j + 1] = tmp;
        }
    }

    public static void swapDown(int i, int j) {
        if (!(candies[i][j] == candies[i + 1][j])) {
            char tmp = candies[i][j];
            candies[i][j] = candies[i + 1][j];
            candies[i + 1][j] = tmp;
        }
    }
}
