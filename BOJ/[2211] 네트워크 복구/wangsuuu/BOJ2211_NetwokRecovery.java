package week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

//문제 두번 잘못 이해함ㅜㅜ (MST, 다익스트라 n번 돌리기..)
//1번 컴퓨터에서 다른 컴퓨터들까지의 최단 거리를 이루는 간선의 정보 출력하는 문제(다익스트라 한번 돌리면 됨)

public class BOJ2211_NetwokRecovery2{
	
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
	
	static int N, M;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] graph;
	static Set<String> set = new HashSet<>(); //int[]가 아닌, String으로 해야 중복값 걸러내기 가능(배열은 주소값으로 저장되므로)
	static boolean[] visited;
	static int[] dist, prevNode;
	static PriorityQueue<Node> pq;
	static ArrayList<Integer> nodes;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //정점 갯수
		M = Integer.parseInt(st.nextToken()); //간선 갯수
		
		graph = new ArrayList[N+1]; //1번부터 시작
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e,c));
			graph[e].add(new Node(s,c));
		}//입력 완료
		
		
		visited= new boolean[N+1];
		dist = new int[N+1];
		pq = new PriorityQueue<>();
		prevNode = new int[N+1];// 직전 노드 저장
		nodes = new ArrayList<>();
		
		dijkstra();
		
		System.out.println(set.size());
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}
	private static void dijkstra() { 
		Arrays.fill(dist, INF);
		
		dist[1]=0;
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.v]) continue;
			
			visited[cur.v]=true;
			for(Node node : graph[cur.v]) {
				if(!visited[node.v] && dist[node.v] > cur.c + node.c) {
					dist[node.v] = cur.c+node.c;
					pq.add(new Node(node.v, dist[node.v]));
					prevNode[node.v] = cur.v;
				}
			}
		}

		for(int i=2; i<=N; i++) {
			nodes.clear();
			dfs(i);
			for(int j=0; j<nodes.size()-1; j++) {
				int min = Math.min(nodes.get(j), nodes.get(j+1));
				int max = Math.max(nodes.get(j), nodes.get(j+1));
				
				set.add(min + " " + max);
			}
		}
	}
	private static void dfs(int i) {
		
		if(i==0)
			return;
		nodes.add(i);
		dfs(prevNode[i]);
	}
}
