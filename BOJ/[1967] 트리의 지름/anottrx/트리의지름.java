import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {

    static int N, node, max;
    static ArrayList<Node>[] nodeList;

    static class Node {
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodeList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            nodeList[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodeList[from].add(new Node(to, weight));
            nodeList[to].add(new Node(from, weight));
        }

        max = 0;
        boolean[] visited = new boolean[N + 1];
        dfs(1, 0, visited);

        visited = new boolean[N + 1];
        dfs(node, 0, visited);

        System.out.println(max);
    }

    private static void dfs(int from, int cnt, boolean[] visited) {
        visited[from] = true;
        for (int i = 0; i < nodeList[from].size(); i++) {
            Node cur = nodeList[from].get(i);
            if (!visited[cur.to]) {
                dfs(cur.to, cur.weight + cnt, visited);
            }
        }

        if (cnt > max) {
            max = cnt;
            node = from;
        }
    }
}
