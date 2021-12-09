package week19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ10282_해킹 {
	private static class Edge implements Comparable<Edge> {
		int vertex;
		int cost;

		public Edge(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	private static int n; //컴퓨터 개수
	private static int d; //의존성 개수
	private static int c; //해킹당한 컴퓨터 번호(시작점)
	private static int[] dist; // 최단거리
	private static ArrayList<Edge>[] edgeList;

	private static int cnt, time; // 총 감염되는 컴퓨터 수, 걸리는시간

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();
		for(int tc = 1 ; tc<=TC ; tc++) {
			n = sc.nextInt();
			d = sc.nextInt();
			c = sc.nextInt();

			//거리배열 초기화
			dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[c] = 0; //시작점

			//인접리스트 초기화
			edgeList = new ArrayList[n+1];
			for(int i = 0 ; i <= n ; i++) {
				edgeList[i] = new ArrayList<>();
			}
			for(int i = 0 ; i < d ; i ++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int s = sc.nextInt();

				edgeList[b].add(new Edge(a,s));
			}

			dijkstra();

			cnt = 0;
			time = 0;
			for (int i = 1; i <= n; i++) {
				if (dist[i] != Integer.MAX_VALUE) {
					cnt++;
					time = Math.max(time, dist[i]);
				}
			}
			System.out.println(cnt + " " + time);
		}
	}
	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(c, 0)); //시작점

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int vertex = edge.vertex;
			int cost = edge.cost;

			//이건 패스
			if(dist[vertex] > cost) {
				continue;
			}
			
			
			for(int i = 0 ; i < edgeList[vertex].size() ; i ++) {
				int vertex2 = edgeList[vertex].get(i).vertex;
				int cost2 = edgeList[vertex].get(i).cost + cost;

				if(dist[vertex2] > cost2) {
					dist[vertex2] = cost2;
					pq.add(new Edge(vertex2, cost2));
				}
			}

		}

	}

}
