//답이 안나오는데 왜그런지 못 찾겠...
package time15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ1647_도시분할계획 {
	
	static int n,m;
	static int[] parents;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        parents = new int[N + 1];
        for (int i = 0; i < M; i++) {
        	int n1 = sc.nextInt();
        	int n2 = sc.nextInt();
        	int cost = sc.nextInt();
        	edgeList.add(new Edge(n1,n2,cost));
        }
        
        make();
        
        Collections.sort(edgeList);
        
        int result = 0;
        int bigcost = 0;
        for(int i = 0; i < edgeList.size(); i ++) {
        	Edge temp = edgeList.get(i);
        	System.out.println("from: "+find(temp.from));
        	System.out.println("to: "+find(temp.to));
        	if(find(temp.from) != find(temp.to)) {
        		union(temp.to, temp.from);
        		System.out.println(999);
        		result += temp.cost;
        		bigcost = temp.cost;
        	}
        }
        System.out.println(result);
        System.out.println(result-bigcost);
	}
	
	public static void make() {
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
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

}
