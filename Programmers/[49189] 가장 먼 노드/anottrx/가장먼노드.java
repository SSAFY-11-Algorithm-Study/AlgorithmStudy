import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;

        ArrayList<Node>[] distArr = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            distArr[edge[i][0]].add(new Node(edge[i][1], 1));
            distArr[edge[i][1]].add(new Node(edge[i][0], 1));
        }

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, 1000000001);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!visited[cur.to]) {
                visited[cur.to] = true;

                for (int i = 0; i < distArr[cur.to].size(); i++) {
                    Node next = distArr[cur.to].get(i);
                    if (dist[next.to] > dist[cur.to] + 1) {
                        dist[next.to] = dist[cur.to] + 1;
                        pq.offer(new Node(next.to, dist[next.to]));
                    }
                }
            }
        }

        int max = 0;
        for (int i = 2; i <= n; i++) {
            max = Math.max(max, dist[i]); // 모든 노드는 연결되어 있다
        }
        for (int i = 2; i <= n; i++) {
            if (max == dist[i]) {
                answer++; // 가장 멀리 떨어진 노드 개수
            }
        }
        return answer;
    }
}
