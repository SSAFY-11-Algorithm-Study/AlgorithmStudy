import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {

    static int INF = 100001;

    static class Edge implements Comparable<Edge> {
        int end, weight;

        public Edge(int e, int w) {
            this.end = e;
            this.weight = w;
        }

        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수 N(1 ≤ N ≤ 1,000)
        int M = Integer.parseInt(st.nextToken()); // 도로 수 M(1 ≤ M ≤ 10,000)
        int X = Integer.parseInt(st.nextToken()); // 파티 열리는 곳
        ArrayList<Edge>[] houseDistList = new ArrayList[N + 1]; // 집에서 X로 가기
        ArrayList<Edge>[] reverseDistList = new ArrayList[N + 1]; // X에서 집으로 돌아가기
        for (int i = 0; i < N + 1; i++) {
            houseDistList[i] = new ArrayList<>();
            reverseDistList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            houseDistList[from].add(new Edge(to, cost));
            reverseDistList[to].add(new Edge(from, cost));
        }

        int[] go = dijkstra(N, X, houseDistList);
        int[] back = dijkstra(N, X, reverseDistList);
        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, go[i] + back[i]);
        }
        System.out.println(max);
    }

    private static int[] dijkstra(int N, int X, ArrayList<Edge>[] houseDistList) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.end] >= cur.weight) {
                for (Edge edge : houseDistList[cur.end]) {
                    if (dist[edge.end] > dist[cur.end] + edge.weight) {
                        dist[edge.end] = dist[cur.end] + edge.weight;
                        pq.offer(new Edge(edge.end, dist[edge.end]));
                    }
                }
            }
        }

        return dist;
    }
}
