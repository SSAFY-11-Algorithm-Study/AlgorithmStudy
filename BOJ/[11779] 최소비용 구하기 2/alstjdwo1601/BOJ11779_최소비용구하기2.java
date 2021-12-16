package week20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
/*
 * 스택에 루트를 저장한 후 역추적하는 방식을 
 * https://youngest-programming.tistory.com/465 여기서 참고함
 */

public class BOJ11779_최소비용구하기2 {
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
	static int n,m;
	static int [] dist;
	static int[] route; //루트 역추적을 위한 배열
	static ArrayList<Node>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		route = new int[n + 1];// 역추적 배열
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		list = new ArrayList[n+1];
		for(int i = 1 ; i <=n ; i++)
			list[i] = new ArrayList<>();
		
		for(int i = 0 ; i < m ; i ++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int weight = sc.nextInt();
			
			list[s].add(new Node(e,weight));
		}
		
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		dist[start] = 0;
		dijkstra(start);
		
		System.out.println(dist[end]); //최소 비용
		
		Stack<Integer> stack = new Stack<>();
		while(true) {
			stack.push(end);
			end = route[end];
			if(end == start) {
				stack.push(end);
				break;
			}
		}
		System.out.println(stack.size()); // 거친 도시의 갯수
		
		while(!stack.isEmpty()) {
			//거친 도시의 번호 
			System.out.print(stack.pop() + " ");
		}

	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));
		
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int vertex = node.vertex;
			int weight = node.weight;
			
			if(dist[vertex] < weight)
				continue;
			
			for(int i =0; i < list[vertex].size(); i ++) {
				int nextVertex = list[vertex].get(i).vertex;
				int nextWeight = list[vertex].get(i).weight + weight;
				
				if(dist[nextVertex] > nextWeight) {
					dist[nextVertex] = nextWeight;
					//루트배열에 저장
					route[nextVertex] = vertex;
					pq.add(new Node(nextVertex,nextWeight));
				}
			}
		}
		
	}

}
