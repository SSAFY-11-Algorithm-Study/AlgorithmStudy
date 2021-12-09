import java.util.*;

public class Main {
    static class Node {
        int index;
        double x;
        double y;

        public Node(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.cost < e.cost) return -1;
            return 1;
        }
    }

    static ArrayList<Node> list = new ArrayList<>();
    static int[] parent;
    static PriorityQueue<Edge> q = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            list.add(new Node(i, x, y));
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(list.get(i).x - list.get(j).x, 2) + Math.pow(list.get(i).y - list.get(j).y, 2));
                q.add(new Edge(i, j, dist));
            }
        }

        parent = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            parent[i] = i;
        }

        double distance = 0;
        while (!q.isEmpty()) {
            Edge current = q.poll();

            int p1 = find(current.from);
            int p2 = find(current.to);

            if (p1 != p2) {
                union(p1, p2);
                distance += current.cost;
            }
        }

        System.out.printf("%.2f", distance);
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        parent[a] = b;
    }
}

