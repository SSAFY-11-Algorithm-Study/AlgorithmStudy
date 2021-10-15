import java.util.Scanner;

public class Main {
    static int N, K, left, right;
    static int[] belt;
    static boolean on[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        belt = new int[2 * N];
        on = new boolean[N];
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = sc.nextInt();
        }
        left = 0;
        right = N;

        int cnt = 0;
        while (K > 0) {
            cnt++;
            moveBelt();
            moveRobot();
            newRobot();
        }
        System.out.println(cnt);
    }

    public static void moveBelt() {
        left--;
        right--;
        if (left == -1) {
            left = 2 * N - 1;
        }
        if (right == -1) {
            right = 2 * N - 1;
        }

        for (int i = N - 2; i >= 0; i--) {
            if (on[i]) {
                on[i] = false;
                if (i + 1 < N - 1) {
                    on[i + 1] = true;
                }
            }
        }
    }

    public static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (on[i]) {
                int next = left + i + 1;
                if (next >= 2 * N) {
                    next -= 2 * N;
                }
                if (!on[i + 1] && belt[next] >= 1) {
                    on[i] = false;
                    if (i + 1 < N - 1) {
                        on[i + 1] = true;
                    }
                    belt[next]--;
                    if (belt[next] == 0) {
                        K--;
                    }
                }
            }
        }
    }

    public static void newRobot() {
        if (!on[0] && belt[left] > 0) {
            on[0] = true;
            belt[left]--;
            if (belt[left] == 0) {
                K--;
            }
        }
    }
}
