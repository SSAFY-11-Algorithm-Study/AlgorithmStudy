import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11779 {

    static int INF = 100000001;

    static class Edge implements Comparable<Edge> {
        int end, cost;

        public Edge(int e, int c) {
            this.end = e;
            this.cost = c;
        }

        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 도시의 개수 n(1≤n≤1,000)
        int m = Integer.parseInt(br.readLine()); // 버스의 개수 m(1≤m≤100,000)
        ArrayList<Edge>[] cityList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            cityList[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            cityList[from].add(new Edge(to, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        String answer = dijkstra(start, end, n, cityList);
        System.out.println(answer);
    }

    private static String dijkstra(int start, int end, int n, ArrayList<Edge>[] cityList) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        int[] path = new int[n + 1];

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.end] >= cur.cost) {
                for (Edge edge : cityList[cur.end]) {
                    if (dist[edge.end] > dist[cur.end] + edge.cost) {
                        dist[edge.end] = dist[cur.end] + edge.cost;
                        pq.offer(new Edge(edge.end, dist[edge.end]));
                        // System.out.println(edge.end + " " + cur.end);
                        path[edge.end] = cur.end;
                    }
                }
            }
        }

        ArrayList<Integer> pathList = new ArrayList<>();
        int num = end;
        while (num != 0) {
            pathList.add(num);
            num = path[num];
        }

        sb.append(dist[end] + "\n");
        sb.append(pathList.size() + "\n");
        for (int i = pathList.size() - 1; i >= 0; i--) {
            sb.append(pathList.get(i) + " ");
        }
        return sb.toString();
    }
}
