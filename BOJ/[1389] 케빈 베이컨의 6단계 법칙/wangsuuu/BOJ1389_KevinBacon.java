import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//각 노드로부터의 다른 노드까지의 최단 거리를 구하므로 dfs가 아닌 bfs!

public class BOJ1389_KevinBacon {

	static int N, M, kb;
	static boolean[][] adj;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new boolean[N+1][N+1]; //사람 번호는 1번부터
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adj[s][e] = true;
			adj[e][s] = true; //양방향 그래프
		}//입력 완료
		
		int min = Integer.MAX_VALUE;
		int answer = 0;
		for(int i=1; i<=N; i++) {
			kb=0; //초기화
			for(int j=1; j<=N; j++) {
				if(i==j)
					continue;
				bfs(i, j);
			}
			if(kb < min) {
				min = kb;
				answer = i;
			} else if(kb==min) {
				if(answer > i) {
					answer = i;
				}
			}
		}
		System.out.println(answer);
	}

	private static void bfs(int x, int y) { //x로부터 y에 도착하기까지 거쳐야 하는 최소 간선의 갯수
		
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N+1];
		
		q.add(new int[] {x, 0});
		visited[x] = true;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int a = tmp[0], cnt=tmp[1];
			if(a==y) {//y에 도달했다면
				kb += cnt;
				return;
			}
			for(int i=1; i<=N; i++) {
				if(adj[a][i] && !visited[i]) {
					q.add(new int[] {i, cnt+1});
					visited[i]=true;
				}
			}
		}
	}
}
