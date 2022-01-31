import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18352_Path {
	
	static int N, M, K, X;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //도시 갯수
		M = Integer.parseInt(st.nextToken()); //도로 갯수
		K = Integer.parseInt(st.nextToken()); //목표 거리
		X = Integer.parseInt(st.nextToken()); //출발 노드 번호
		
		graph = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
		}//입력 완료
		
		bfs();
		
		if(answer.size()==0) {
			System.out.println(-1);
		} else {
			Collections.sort(answer);
			for(Integer i : answer) {
				System.out.println(i);
			}
		}
	}

	private static void bfs() {
		boolean[] visited = new boolean[N+1];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {X, 0}); //노드 번호, 누적 거리
		visited[X] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int node = cur[0]; int dist = cur[1];
			if(dist == K) {
				answer.add(node);
			}
			
			for(int i=0; i<graph[node].size(); i++) {
				int nxt = graph[node].get(i);
				if(!visited[nxt]) {
					q.add(new int[] {nxt, dist+1});
					visited[nxt] = true;
				}
			}
		}
	}
}
