import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2211 {

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int f, int t, int c) {
            this.from = f;
            this.to = t;
            this.cost = c;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] computerList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            computerList[i] = new ArrayList<Edge>();
        }
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            computerList[from].add(new Edge(from, to, cost));
            computerList[to].add(new Edge(to, from, cost));
        }

        String answer = dijkstra(N, computerList, 1);
        System.out.println(answer);
    }

    private static String dijkstra(int N, ArrayList<Edge>[] computerList, int start) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, start, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 100001);
        int[] from = new int[N + 1];
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.to] >= cur.cost) {
                for (Edge edge : computerList[cur.to]) {
                    if (dist[edge.to] > dist[cur.to] + edge.cost) {
                        // System.out.println(edge.from + " , " + edge.to);
                        dist[edge.to] = dist[cur.to] + edge.cost;
                        from[edge.to] = edge.from;
                        pq.offer(new Edge(edge.from, edge.to, dist[edge.to]));
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] != 0 && dist[i] != 100001) {
                cnt++;
            }
        }
        sb.append(cnt + "\n");
        for (int i = 2; i < N + 1; i++) {
            sb.append(i + " " + from[i] + "\n");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
