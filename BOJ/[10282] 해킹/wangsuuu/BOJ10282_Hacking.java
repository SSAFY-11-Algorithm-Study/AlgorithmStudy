import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10282_Hacking {
	
	static class Node implements Comparable<Node>{
		int v, c;

		public Node(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
		
	}
	
	static int n, d, c;
	static int time, cnt;
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			
			StringTokenizer st= new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); //vertex 갯수
			d = Integer.parseInt(st.nextToken()); //edge 갯수
			c = Integer.parseInt(st.nextToken()); //시작 vertex
			
			graph = new ArrayList[n+1]; //1번부터 시작
			for(int i=1; i<=n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0; i<d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[b].add(new Node(a, cost));
			} //입력 완료
			
			time=0; cnt=0;
			dijkstra();
			System.out.println(cnt + " " + time);
		}
	}
	private static void dijkstra() {
		final int INF = Integer.MAX_VALUE;
		boolean[] visited=  new boolean[n+1];
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[c]=0;
		pq.add(new Node(c, 0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.v]) continue;
			
			visited[cur.v]=true;
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.c + node.c) {
					dist[node.v] = cur.c + node.c;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(dist[i]!=INF) {
				cnt++;				
				time = Math.max(time, dist[i]);
			}
		}
	}
}
