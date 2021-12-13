import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238_Party {
	
	static class Node implements Comparable<Node>{
		int v, c;
		public Node(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
	
	static int N, M, X;
	static ArrayList<Node>[] graph;
	static int answer=-1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //노드 갯수
		M = Integer.parseInt(st.nextToken()); //간선 갯수
		X = Integer.parseInt(st.nextToken()); //반환점
		
		graph = new ArrayList[N+1]; //1번 노드부터 시작
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}//초기화
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e,c));
		}//입력 완료
		
		for(int i=1; i<=N; i++) {
			dijkstra(i);
		}
		System.out.println(answer);
	}

	private static void dijkstra(int n) { //n -> X 최단거리 + X -> n 최단거리 구하기
		final int INF = Integer.MAX_VALUE;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		int result=0;
		Arrays.fill(dist, INF);
		
		//1. n -> X 최단거리
		dist[n] = 0;
		pq.add(new Node(n, 0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.v]) continue;
			
			if(cur.v==X) break;
			
			visited[cur.v]=true;
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.c + node.c) {
					dist[node.v] = cur.c + node.c;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
		result += dist[X];
		
		//2. X -> n 최단거리
		pq.clear();
		Arrays.fill(visited, false);
		Arrays.fill(dist, INF);
		
		dist[X]=0;
		pq.add(new Node(X, 0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.v]) continue;
			if(cur.v==n) break;
			
			visited[cur.v]=true;
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.c + node.c) {
					dist[node.v] = cur.c + node.c;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
		result += dist[n];
		answer = Math.max(answer, result);
	}
}
