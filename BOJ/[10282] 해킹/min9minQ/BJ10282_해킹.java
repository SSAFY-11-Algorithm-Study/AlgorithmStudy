package time19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ10282_해킹 {

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

	private static int T;
	private static int n, d, c;
	private static ArrayList<Node>[] list;
	private static int[] distance;
	private static int time;
	private static int computer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n+1];
			distance = new int[n+1];
			for(int i = 0; i < n+1; i++) {
				distance[i] = Integer.MAX_VALUE;
			}
			distance[c] = 0;
			for(int i = 0; i < n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Node(a, s));
			}
			
			computer = 0;
			time = 0;
			dikjstra();
			

			for (int i = 1; i <= n; i++) {
				if (distance[i] != Integer.MAX_VALUE) {
					computer++;
					time = Math.max(time, distance[i]);
				}
			}

			System.out.println(computer + " " + time);
		}

	}

	private static void dikjstra() {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(c, 0));
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
