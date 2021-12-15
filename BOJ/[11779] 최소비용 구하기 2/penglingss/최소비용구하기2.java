import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    static ArrayList<Node>[] list;
    static int n, m, startIndex, endIndex;
    static int[] distance;
    static int[] route;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            list[start].add(new Node(end, cost));
        }

        startIndex = sc.nextInt();
        endIndex = sc.nextInt();

        distance = new int[n + 1];
        route = new int[n + 1];
        Arrays.fill(distance, 100000000);

        dijkstra();


        ArrayList<Integer> routes = new ArrayList<>();
        int cur = endIndex;
        while (cur != 0) {
            routes.add(cur);
            cur = route[cur];
        }

        System.out.println(distance[endIndex]);
        System.out.println(routes.size());
        for (int i = routes.size() - 1; i >= 0; i--) {
            System.out.print(routes.get(i) + " ");
        }
    }

    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(startIndex, 0));
        visit = new boolean[n + 1];
        distance[startIndex] = 0;
        route[startIndex] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visit[cur.index]) continue;
            visit[cur.index] = true;

            for (int i = 0; i < list[cur.index].size(); i++) {
                Node next = list[cur.index].get(i);
                if (distance[next.index] > distance[cur.index] + next.cost) {
                    distance[next.index] = distance[cur.index] + next.cost;
                    q.add(new Node(next.index, distance[next.index]));
                    route[next.index] = cur.index;
                }
            }
        }
    }
}
