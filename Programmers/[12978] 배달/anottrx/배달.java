import java.util.*;

class Solution {

    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<Node>[] village = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            village[i] = new ArrayList<>();
        }
        for (int i = 0; i < road.length; i++) {
            int from = road[i][0];
            int to = road[i][1];
            int cost = road[i][2];

            // 두 마을 a, b를 연결하는 도로는 여러 개가 있을 수 있습니다.
            boolean canAdd = true;
            ArrayList<Node> curNodeList = village[to];
            finding: for (Node node : curNodeList) {
                if (node.end == to) {
                    if (node.cost > cost) {
                        village[to].remove(node);
                        Node otherNode = new Node(from, cost);
                        village[from].remove(otherNode);
                    } else {
                        canAdd = false;
                    }
                    break finding;
                }
            }
            if (canAdd) {
                village[to].add(new Node(from, cost));
                village[from].add(new Node(to, cost));
            }
        }

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 500001);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.end] >= cur.cost) {
                for (Node node : village[cur.end]) {
                    if (dist[node.end] > dist[cur.end] + node.cost) {
                        dist[node.end] = dist[cur.end] + node.cost;
                        pq.offer(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return (answer);
    }
}
