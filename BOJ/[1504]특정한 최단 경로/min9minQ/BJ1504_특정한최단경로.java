package time27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1504_특정한최단경로 {

	private static class Point implements Comparable<Point> {
		int end;
		int weight;

		public Point(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Point o) {
			return weight - o.weight;
		}
	}

	private static int N, E;
	private static ArrayList<ArrayList<Point>> arr = new ArrayList<>();
	private static boolean[] visited;
	private static int[] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			arr.add(new ArrayList<Point>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			arr.get(from).add(new Point(to, weight));
			arr.get(to).add(new Point(from, weight));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int result1 = 0;
        int result2 = 0;
        int answer = 0;

        result1 += dijkstra(1, v1);
        result1 += dijkstra(v1, v2);
        result1 += dijkstra(v2, N);

        result2 += dijkstra(1, v2);
        result2 += dijkstra(v2, v1);
        result2 += dijkstra(v1, N);
        
        if(result1 >= 200000000 && result2 >= 200000000) answer = -1;
        else answer =  Math.min(result1, result2);

        System.out.println(answer);
	}

	private static int dijkstra(int start, int end) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Point temp = pq.poll();
			int cur = temp.end;

			if (!visited[cur]) {
				visited[cur] = true;

				for (Point p : arr.get(cur)) {
					if (!visited[p.end] && dist[p.end] > dist[cur] + p.weight) {
						dist[p.end] = dist[cur] + p.weight;
						pq.add(new Point(p.end, dist[p.end]));
					}
				}
			}
		}
		return dist[end];
	}

}
