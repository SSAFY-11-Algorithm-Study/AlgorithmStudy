package time20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2211_네트워크복구 {

	private static class Node implements Comparable<Node> {
		int i, dist;

		public Node(int i, int dist) {
			this.i = i;
			this.dist = dist;
		}

		public int compareTo(Node node) {
			return this.dist - node.dist;
		}
	}

	private static int N, M;
	private static ArrayList<Node>[] list;
	private static int INF = 50000000;
	static int[] dist, path;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		path = new int[N + 1];
		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			list[s].add(new Node(e, d));
			list[e].add(new Node(s, d));
		}

		dijkstra();

		System.out.println(N - 1);

		for (int i = 2; i < N + 1; i++)
			System.out.println(i + " " + path[i]);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Node temp = pq.poll();

			for (Node next : list[temp.i]) {
				if (dist[next.i] > temp.dist + next.dist) {
					dist[next.i] = temp.dist + next.dist;
					path[next.i] = temp.i;
					pq.add(new Node(next.i, temp.dist + next.dist));
				}
			}
		}

	}

}
