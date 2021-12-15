// https://coder-in-war.tistory.com/entry/BOJ-JAVA11779-%EC%B5%9C%EC%86%8C%EB%B9%84%EC%9A%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-2 참고했습니다

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
                        path[edge.end] = cur.end; // 어디서 간 건지 저장
                    }
                }
            }
        }

        ArrayList<Integer> pathList = new ArrayList<>();
        int num = end; // 도착지점부터 시작해서 시작지점으로 가기
        while (num != 0) {
            pathList.add(num);
            num = path[num];
        }

        sb.append(dist[end] + "\n"); // 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
        sb.append(pathList.size() + "\n"); // 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력
        for (int i = pathList.size() - 1; i >= 0; i--) { // 리스트에는 뒤에서부터 저장되어 있기 때문에 역순으로 출력
            sb.append(pathList.get(i) + " "); // 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력
        }
        return sb.toString();
    }
}
