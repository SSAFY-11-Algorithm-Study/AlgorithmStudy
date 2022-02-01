import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5567 {

    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[][] friends = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friends[from][to] = friends[to][from] = true;
        }
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0, friends, visited); // 상근이 학번이 1번이므로 1부터 시작. 깊이는 상근이의 친구이므로 0에서 시작
        System.out.println(answer);
    }

    private static void dfs(int n, int depth, boolean[][] friends, boolean[] visited) {
        for (int i = 2; i <= N; i++) { // 상근이가 1번이므로 2번부터 찾기 시작
            if (!visited[i] && friends[i][n]) {
                if (friends[1][i]) { // 상근이의 친구인 경우
                    visited[i] = true;
                    answer++;
                    dfs(i, 1, friends, visited); // 친구의 친구 초대하기 위해 dfs 한 번 더
                } else if (depth == 1) { // 친구의 친구인 경우
                    visited[i] = true;
                    answer++;
                }
            }
        }
    }
}
