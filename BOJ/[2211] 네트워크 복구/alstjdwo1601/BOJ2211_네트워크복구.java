package week20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 문제이해를 잘못한거같음.. 
 */
public class BOJ2211_네트워크복구 {
	
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
	
	static int n,m;
	static int [] dist;
	static ArrayList<Node>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		list = new ArrayList[n+1];
		for(int i = 1 ; i <=n ; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0 ; i < m ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			list[start].add(new Node(end,weight));
			list[end].add(new Node(start,weight)); //양방향통신
		}
		
		dijkstra();
		
		System.out.println(n-1); //모든 컴퓨터를 최소 회선으로 복구하려면 n-1개 필요
		
		for(int i=2; i<=n; i++)
            System.out.println(i+" "+dist[i]);
	}
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int vertex = node.vertex;
			int weight = node.weight;
			
			if(dist[vertex] < weight)
				continue;
			
			for(int i = 0 ; i < list[vertex].size();i++) {
				int nextVertex = list[vertex].get(i).vertex;
				int nextWeight= list[vertex].get(i).weight + weight;
				
				if(dist[nextVertex] > nextWeight) {
					dist[nextVertex] = nextWeight;
					pq.add(new Node(nextVertex,nextWeight));
				}
			}
		}
		
	}

}
