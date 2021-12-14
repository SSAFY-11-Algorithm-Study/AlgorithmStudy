package week20;

/*
 * https://bcp0109.tistory.com/60 보고 참고함
 * 반대로 가는길도 다시 다익스트라해야되는걸 몰랐음
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1238_파티 {
	static class Node implements Comparable<Node>{
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
	
	static final int INF = 987654321;
	static int N,M,X;
	static ArrayList<ArrayList<Node>> list, reverseList;
	static int [] dist;   // X에서 각 점들로의 최단거리
	static int [] dist2;  // 각 점들에서 X로의 최단거리
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		
		dist = new int[N+1];
        dist2 = new int[N+1];
        Arrays.fill(dist, INF);
        Arrays.fill(dist2, INF);
		
        list = new ArrayList<ArrayList<Node>>();
        reverseList = new ArrayList<ArrayList<Node>>();
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<Node>());
            reverseList.add(new ArrayList<Node>());
		}
		
		for(int i = 0 ; i < M ; i ++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			list.get(start).add(new Node(end,weight));
			
			//반대방향을 여기다 넣는게 핵심
			reverseList.get(end).add(new Node(start,weight));
		}
		
		// 다익스트라 2번을 이용해서 구할 수 있다
        // 주어진 간선에서 X번 마을에서 각 마을로 가는 최단 경로를 구하고
        dijkstra(list, dist, X);
 
        // 간선을 뒤집은 다음에 X번 마을에서 각 마을로 가는 최단 경로를 구하면
        // 각 마을에서 X번 마을로 가는 최단 경로를 구할 수 있음
        dijkstra(reverseList, dist2, X);
		
		int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dist[i] + dist2[i]);
        }
		
		System.out.println(max);
	}
	
	
	static void dijkstra(ArrayList<ArrayList<Node>> list, int[] weight, int start) {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
 
        weight[start] = 0;
        pq.add(new Node(start, 0));
 
        while(!pq.isEmpty()) {
            int idx = pq.poll().vertex;
 
            // 방문한 곳은 또 방문할 필요 없음
            if(visited[idx]) continue;
            visited[idx] = true;
 
            for(Node node : list.get(idx)) {
                // node.vertex 까지의 거리는 (시작점->idx 거리 + idx->node.vertex 거리) 중 더 작은 것
                if(weight[node.vertex] > weight[idx] + node.weight) {
                    weight[node.vertex] = weight[idx] + node.weight;
                    pq.add(new Node(node.vertex, weight[node.vertex]));
                }
            }
        }
    }
}
