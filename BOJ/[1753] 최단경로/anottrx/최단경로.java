import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

    static int V, E;
    static ArrayList<Node>[] distArr;

    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        distArr = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            distArr[i] = new ArrayList<>();
        }
        int K = Integer.parseInt(br.readLine()); // start
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            distArr[from].add(new Node(to, cost));
        }

        dijkstra(K);
    }

    private static void dijkstra(int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(k, 0));
        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.to]) {
                visited[cur.to] = true;

                for (int i = 0; i < distArr[cur.to].size(); i++) {
                    Node node = distArr[cur.to].get(i);
                    if (distance[node.to] > distance[cur.to] + node.cost) {
                        distance[node.to] = distance[cur.to] + node.cost;
                        pq.offer(new Node(node.to, distance[node.to]));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(distance[i] + "\n");
            }
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
