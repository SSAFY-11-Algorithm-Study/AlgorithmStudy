package time19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753_최단경로 {

	private static class Node implements Comparable<Node> { 
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}

	private static int V, E, K;
	private static ArrayList<Node>[] list;
	private static int[] distance;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList[V+1];
		distance = new int[V+1];
		for(int i = 0; i < V+1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[K] = 0;
		for(int i = 1; i < V+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end,weight));
		}
		 
		dikjstra();
		
		for(int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}

	}

	private static void dikjstra() {
		 PriorityQueue<Node> que = new PriorityQueue<>();
	        que.add(new Node(K, 0));
	        while (!que.isEmpty()) {
	            Node node = que.poll();
	            if (distance[node.vertex] < node.weight) { 
	                continue;
	            }
	            for (int i = 0; i < list[node.vertex].size(); i++) {
	                int v = list[node.vertex].get(i).vertex;
	                int w = list[node.vertex].get(i).weight + node.weight;
	                if (distance[v] > w) { 
	                    distance[v] = w;
	                    que.add(new Node(v, w));
	                }
	            }
	        }
		
	}

}
