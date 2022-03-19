import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504_ShortestPath {
	
	static class Node implements Comparable<Node>{
		int y;
		int weight;
		public Node(int y, int weight) {
			super();
			this.y = y;
			this.weight = weight;
		}
		
		public int compareTo(Node n) { //pq 정렬 시 dist 오름차순으로 정렬
			return this.weight - n.weight;
		}
	}
	
	static int N, E;
	static ArrayList<Node>[] graph;
	static int v1, v2;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //노드 갯수
		E = Integer.parseInt(st.nextToken()); //간선 갯수
		
		graph = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[x].add(new Node(y, weight));
			graph[y].add(new Node(x, weight)); //양방향
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		long ans1 = calculate(v1, v2);
		long ans2 = calculate(v2, v1);
		
		if(ans1 == -1 && ans2 == -1) {
			System.out.println(-1);
		} else if(ans1 == -1 || ans2 == -1) {
			System.out.println(Math.max(ans1, ans2));
		} else {
			System.out.println(Math.min(ans1, ans2));
		}
		
	}

	private static long calculate(int v1, int v2) { // 1 -> v1 -> v2 -> N
		
		long answer = 0;
		
		//1 -> v1 최단경로
		long ans1 = dijkstra(1, v1);
		
		if(ans1 == INF) return -1;
		
		answer += ans1;
		
		// v1 -> v2 최단경로!!!! (v1 -> v2 다이렉트가 아니라, 최단경로를 또 구해봐야 함!)
		long ans2 = dijkstra(v1, v2);
		if(ans2 == INF) return -1;
		
		answer += ans2;
		
		//v2 -> N 최단경로
		long ans3 = dijkstra(v2, N);
		if(ans3==INF) return -1;
		
		answer += ans3;
		
		return answer; //총 다익스트라 3번...
	}

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[start] = 0;
		pq.add(new Node(start, 0)); //노드번호, start부터 노드번호까지의 최단 거리 저장
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			
			if(visited[cur.y])
				continue;
			
			if(cur.y == end) {
				return cur.weight;
			}

			visited[cur.y] = true;
			
			for(Node n : graph[cur.y]) {
				if(!visited[n.y] && dist[n.y] > cur.weight + n.weight) {
					dist[n.y] = cur.weight + n.weight;
					pq.add(new Node(n.y, dist[n.y]));
				}
			}
		}
		
		return INF;
		
	}

}
