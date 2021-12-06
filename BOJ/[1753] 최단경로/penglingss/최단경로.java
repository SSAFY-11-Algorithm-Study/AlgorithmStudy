import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    static int V, E, start;
    static ArrayList<Node>[] listArray;
    static int[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        start = sc.nextInt();
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        listArray = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            listArray[i] = new ArrayList();
        }

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            listArray[from].add(new Node(to, cost));
        }

        dijkstra();

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        boolean[] visit = new boolean[V + 1];

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curIndex = current.index;

            if (visit[curIndex]) continue;
            visit[curIndex] = true;

            for (Node node : listArray[curIndex]) {
                if (dist[node.index] > dist[curIndex] + node.cost) {
                    dist[node.index] = dist[curIndex] + node.cost;
                    pq.add(new Node(node.index, dist[node.index]));
                }
            }
        }
    }
}
