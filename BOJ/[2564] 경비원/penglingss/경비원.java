import java.util.Scanner;

public class boj2564 {
    static int R, C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        R = sc.nextInt();
        int[] location = new int[]{0, R, 0, C};

        int N = sc.nextInt();
        int[][] store = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            int dir = sc.nextInt();

            if (dir == 1 || dir == 2) {
                store[i][0] = location[dir - 1];
                store[i][1] = sc.nextInt();
            } else if(dir == 3 || dir == 4) {
                store[i][0] = sc.nextInt();
                store[i][1] = location[dir - 1];
            }
        }

        int DGR = store[N][0];
        int DGC = store[N][1];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += getDist(DGR, DGC , store[i][0], store[i][1]);
        }

        System.out.println(sum);
    }

    public static int getDist(int DGR, int DGC, int toR, int toC) {
        int res = 0;
        if (DGR == toR) {
            res = Math.abs(DGC - toC);
        } else {
            res += Math.min(DGC + toC, (C - DGC) + (C - toC));
            res += Math.abs(DGR - toR);
        }
        return res;
    }
}
