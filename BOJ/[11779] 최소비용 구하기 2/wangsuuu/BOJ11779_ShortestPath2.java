import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11779_ShortestPath2 {
	
	static class Node implements Comparable<Node>{
		int v, c;

		public Node(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}
		public int compareTo(Node o) {
			return this.c -o.c;
		}
	}
	
	static int n, m, start, end;
	static int cnt=0;
	static ArrayList<Node>[] graph;
	static int[] via;
	static ArrayList<Integer> viaList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine()); //정점 갯수
		int m = Integer.parseInt(br.readLine()); //간선 갯수
		
		graph = new ArrayList[n+1]; //1번부터 시작
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e,c));
		}
		st = new StringTokenizer(br.readLine());
		start= Integer.parseInt(st.nextToken()); //출발점
		end= Integer.parseInt(st.nextToken()); //도착점
		//입력 완료
		
		via = new int[n+1]; //가장 직전에 들린 정점의 번호 저장
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist=  new int[n+1];
		boolean[] visited = new boolean[n+1];
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[start]=0;
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.v]) continue;
			if(cur.v==end) break;
			
			visited[cur.v]=true;
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.c + node.c) {
					dist[node.v] = cur.c + node.c;
					via[node.v] = cur.v; //node.v번 정점은 직전에 경유지로 cur.v를 들림
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
		
		System.out.println(dist[end]);
		
		dfs(end);
		System.out.println(cnt);
		//처음에 sb.reverse했었는데, 그럴 경우 도시 번호가 2자리 수면 잘못 뒤집어지는 문제 발생
		for(int i=viaList.size()-1; i>=0; i--) {
			System.out.print(viaList.get(i) + " ");
		}
	}
	private static void dfs(int a) {
		if(a==0)
			return;
		cnt++;
		viaList.add(a);
		dfs(via[a]);
	}
}
