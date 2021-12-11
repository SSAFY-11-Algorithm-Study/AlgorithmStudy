import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;
        
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    int[] parent;
    PriorityQueue<Node> q;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        q = new PriorityQueue<>();

        for(int[] cost : costs) {
            q.offer(new Node(cost[0], cost[1], cost[2]));
        }
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(find(node.from) == find(node.to)) {
                continue;
            } else {
                union(node.from, node.to);
                answer += node.cost;
            }
        }
        
        
        return answer;
    }
    
    public int find(int v) {
        if(parent[v] == v) return v;
        else return parent[v] = find(parent[v]);
    }
    
    public void union(int from, int to) {
        int rootFrom = find(from);
        int rootTo = find(to);
        parent[rootTo] = rootFrom;
    }
}
