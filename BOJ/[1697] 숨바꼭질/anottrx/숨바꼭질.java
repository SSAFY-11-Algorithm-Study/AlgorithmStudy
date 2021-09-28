import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) { // 시작과 끝이 같아서 이동할 필요가 없는 경우
            System.out.println(N - K);
        } else {
            System.out.println(playGame(N, K));
        }
    }

    private static int playGame(int N, int K) { // bfs
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] cnt = new int[100001];
        q.offer(N);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int[] threeWays = new int[3]; // 1초 후 이동하는 방법 3가지
            threeWays[0] = cur - 1; // X-1
            threeWays[1] = cur + 1; // X+1
            threeWays[2] = cur * 2; // 2*X (순간이동)

            for (int i = 0; i < 3; i++) {
                if (threeWays[i] < cnt.length && threeWays[i] >= 0 && !visited[threeWays[i]]) {
                    q.offer(threeWays[i]);
                    cnt[threeWays[i]] = cnt[cur] + 1;
                    visited[threeWays[i]] = true;
                    if (threeWays[i] == K) {
                        return cnt[K];
                    }
                }
            }
        }
        return cnt[K];
    }
}
