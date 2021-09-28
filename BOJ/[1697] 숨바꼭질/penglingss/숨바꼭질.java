import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, K, answer;
    static boolean flag;
    static boolean[] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        flag = false;
        visit = new boolean[100001];

        hideAndSeek();

        System.out.println(answer);
    }

    public static void hideAndSeek() {
        if(N == K) return;

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visit[N] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                if (chk(cur * 2)) {
                    q.add(cur * 2);
                    visit[cur * 2] = true;
                }
                if (chk(cur + 1)) {
                    q.add(cur + 1);
                    visit[cur + 1] = true;
                }
                if (chk(cur - 1)) {
                    q.add(cur - 1);
                    visit[cur - 1] = true;
                }

                if (flag) {
                    answer = cnt;
                    return;
                }
            }
        }
    }

    public static boolean chk(int idx) {
        if (idx == K) {
            flag = true;
        }
        return idx >= 0 && idx <= 100000 && !visit[idx];
    }
}
