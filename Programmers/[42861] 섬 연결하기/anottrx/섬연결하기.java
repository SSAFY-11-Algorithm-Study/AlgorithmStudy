import java.util.*;

class Solution {
    
    static int[] parents;
    static ArrayList<Edge> edgeList;
    
    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int s, int e, int c) {
            this.start = s;
            this.end = e;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        edgeList = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            edgeList.add(new Edge(from, to, cost));
        }
        Collections.sort(edgeList);

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        int result = 0;
        int count = 0;
        for (Edge edge : edgeList) {
            if (union(edge.start, edge.end)) {
                result = result + edge.cost;
                count++;
                if (count == n - 1) {
                    break;
                }
            }
        }
                 
        answer = result;
        return answer;
    }
    
    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
