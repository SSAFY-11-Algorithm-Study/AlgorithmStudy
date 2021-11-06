import java.util.Scanner;

public class Main {

    static int N, M, x, y, K;
    static int[] dice;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        K = sc.nextInt();
        dice = new int[7];

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < K; i++) {
            int direction = sc.nextInt();
            moveDice(direction);
        }
    }

    private static void moveDice(int direction) {

        int temp;
        switch (direction) {
            case 1:
                y++;
                if (y >= M) {
                    y--;
                    return;
                }
                temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
                break;

            case 2:
                y--;
                if (y < 0) {
                    y++;
                    return;
                }
                temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
                break;

            case 3:
                x--;
                if (x < 0) {
                    x++;
                    return;
                }
                temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;

                break;

            case 4:
                x++;
                if (x >= N) {
                    x--;
                    return;
                }
                temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
                break;
        }


        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
        System.out.println(dice[1]);
    }
}
