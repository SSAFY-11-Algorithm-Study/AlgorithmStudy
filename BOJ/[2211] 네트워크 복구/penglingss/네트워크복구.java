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

    static int N, M;
    static ArrayList<Node>[] list;
    static int[] answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        answer = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();

            list[A].add(new Node(B, C));
            list[B].add(new Node(A, C));
        }

        dijkstra();

        System.out.println(N - 1);
        for (int i = 2; i <= N; i++) {
            System.out.println(i + " " + answer[i]);
        }
    }

    public static void dijkstra() {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, 100000000);
        boolean[] visit = new boolean[N + 1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        distance[1] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visit[cur.index]) continue;
            visit[cur.index] = true;

            for (Node next : list[cur.index]) {
                if (distance[next.index] > cur.cost + next.cost) {
                    distance[next.index] = cur.cost + next.cost;
                    answer[next.index] = cur.index;
                    q.add(new Node(next.index, cur.cost + next.cost));
                }
            }
        }
    }
}
