import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] distance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) distance[i][j] = 0;
                else distance[i][j] = 500001;
            }
        }

        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            distance[A][B] = 1;
            distance[B][A] = 1;
        }

        floydWarshall();

        int[] answer = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += distance[i][j];
            }
            answer[i] = sum;
            if (sum < min) {
                min = sum;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (answer[i] == min) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }
    }
}
