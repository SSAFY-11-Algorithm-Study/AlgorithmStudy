package BOJ;

import java.util.*;

public class Main {
    static class Node {
        int index;
        int cost;

        public Node(int vertex, int cost) {
            this.index = vertex;
            this.cost = cost;
        }
    }

    static int T, n, d, c;
    static int[] distances;
    static ArrayList<Node>[] list;
    static int count = 0;
    static int time = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            n = sc.nextInt();
            d = sc.nextInt();
            c = sc.nextInt();
            distances = new int[n + 1];
            list = new ArrayList[n + 1];

            Arrays.fill(distances, Integer.MAX_VALUE);
            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int s = sc.nextInt();
                list[b].add(new Node(a, s));
            }

            distances[c] = 0;
            dijkstra();
            count = 0;
            time = 0;
            for (int i = 1; i <= n; i++) {
                if (distances[i] != Integer.MAX_VALUE) {
                    count++;
                    time = Math.max(time, distances[i]);
                }
            }
            System.out.println(count + " " + time);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);

        pq.add(new Node(c, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curIndex = current.index;
            int curCost = current.cost;

            if (distances[curIndex] < curCost) {
                continue;
            }

            for (int i = 0; i < list[curIndex].size(); i++) {
                int nextIndex = list[curIndex].get(i).index;
                int nextCost = list[curIndex].get(i).cost + curCost;
                if (distances[nextIndex] > nextCost) {
                    distances[nextIndex] = nextCost;
                    pq.add(new Node(nextIndex, nextCost));
                }
            }
        }
    }
}
