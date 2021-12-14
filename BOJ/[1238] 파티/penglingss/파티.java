import java.util.*;

public class Main {

    static int INF = 100000000;
    static int N, M, X;
    static ArrayList<ArrayList<Node>> list, reverseList;
    static int[] dist, reverseDist;

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();

        list = new ArrayList<>();
        reverseList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        dist = new int[N + 1];
        reverseDist = new int[N + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(reverseDist, INF);

        for (int i = 1; i <= M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            list.get(start).add(new Node(end, cost));
            reverseList.get(end).add(new Node(start, cost));
        }

        dijkstra(list, dist, X);
        dijkstra(reverseList, reverseDist, X);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dist[i] + reverseDist[i]);
        }
        System.out.println(answer);
    }

    private static void dijkstra(ArrayList<ArrayList<Node>> list, int[] distance, int start) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        distance[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll().index;

            if (visit[cur]) continue;
            visit[cur] = true;

            for (Node node : list.get(cur)) {
                if (distance[node.index] > distance[cur] + node.distance) {
                    distance[node.index] = distance[cur] + node.distance;
                    q.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }
}
