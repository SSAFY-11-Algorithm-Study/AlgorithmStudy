import java.util.Scanner;

public class boj2606 {
    static int N;
    static int[][] arr;
    static boolean[] visit;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N + 1][N + 1];
        visit = new boolean[N + 1];
        answer = 0;
        int idx = sc.nextInt();

        for (int i = 0; i < idx; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            arr[c1][c2] = 1;
            arr[c2][c1] = 1;
        }

        visit[1] = true;
        dfs(1);

        System.out.println(answer);
    }

    private static void dfs(int idx) {
        for (int i = 1; i <= N; i++) {
            if (!visit[i] && arr[idx][i] == 1) {
                visit[i] = true;
                answer++;
                dfs(i);
            }
        }
    }
}
