import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//시간초과 해결 : priorityQueue를 사용한 다익스트라 
//내부에서 정렬이 되므로, 1단계를 수행하기 위해 for문을 낭비적으로 돌릴 필요가 없어짐
//멤버변수 weight는 인접 리스트에서는 간선의 가중치이고, pq에서는 dist(출발지~정점까지의 최소 비용)다.

public class BOJ1753_ShortestPath {
	
	static class Node implements Comparable<Node>{
		int v, w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) { //가중치를 기준으로 오름차순 정렬
			return this.w - o.w;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static int V, E, K;
	static ArrayList<Node>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); //정점의 갯수
		E = Integer.parseInt(st.nextToken()); //간선의 갯수
		K = Integer.parseInt(br.readLine()); //시작 정점
		
		graph = new ArrayList[V+1]; //정점 번호는 1부터 시작
		
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<Node>();
		} //초기화
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}//입력 완료
		
		dijkstra();
		
		for(int i=1; i<=V; i++) {
			if(dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
		
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(); //Node(정점, dist[정점])을 담아 dist를 기준으로 오름차순 정렬할 우선순위 큐
	
		boolean[] visited = new boolean[V+1];
		dist = new int[V+1]; //시장 정점에서 해당 정점까지의 최소 거리를 담은 배열
		Arrays.fill(dist, INF);//초기화
		
		dist[K]=0; //시작 정점의 거리는 0으로 세팅
		pq.add(new Node(K, 0));

		while(!pq.isEmpty()) {
			
			//1. 아직 방문하지 않은 정점 중 최소 비용을 가진 정점을 구한다.
			Node cur = pq.poll();
			if(visited[cur.v])
				continue;
			
			//2. 그 정점을 방문처리 해준다.
			visited[cur.v]=true;
			
			//3. 방문하지 않은 정점들 중 그 정점을 경유지 삼아 갈 수 있는 경우가 원래 값보다 더 작다면, 업데이트하고 pq에 넣음(다시 1번을 하기 위해)
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.w + node.w) {
					dist[node.v] = cur.w + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}
