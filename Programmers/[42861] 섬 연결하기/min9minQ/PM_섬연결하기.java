package time20;

import java.util.PriorityQueue;

public class PM_섬연결하기 {
	
	private static class Node implements Comparable<Node> {
	    int a;
	    int b;
	    int cost;

	    public Node(int a, int b, int cost) {
	        this.a = a;
	        this.b = b;
	        this.cost = cost;
	    }

	    public int compareTo(Node node) {
	        return this.cost - node.cost;
	    }
	}
	
	private static int n = 4;
	private static int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};

	private static int[] parent;
	
	public static void main(String[] args) {
		int answer = 0;
		
		parent = new int[n];
		
		make();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int[] cost : costs){
            pq.offer(new Node(cost[0], cost[1], cost[2]));
        }

        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int A = temp.a;
            int B = temp.b;
            int cost = temp.cost;

            if(find(A) != find(B)) {
                union(A, B);
                answer += cost;
            }
        }
		
		//return answer;
		System.out.println(answer);
	}

	private static void union(int a, int b) {
		int parentA = find(a);
        int parentB = find(b);

        if(parentA > parentB) {
            parent[parentA] = parentB;
        }else {
            parent[parentB] = parentA;
        }
    
	}

	private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

	private static void make() {
		for(int i = 0; i < n; i++) {
            parent[i] = i;
        }		
	}

}
