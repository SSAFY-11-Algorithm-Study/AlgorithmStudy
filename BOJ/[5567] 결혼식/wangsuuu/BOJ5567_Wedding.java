package week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5567_Wedding {
	
	static int N, M;
	static int[][] graph;
	static int answer=0;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //동기의 수
		M = Integer.parseInt(br.readLine()); //리스트의 수
		
		graph = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start][end] = 1;
			graph[end][start] = 1; //양방향
		}
		
		//"1로부터" 최단 depth가 2이하인 노드의 갯수 구하기 -> BFS!!!
		for(int i=2; i<=N; i++) {
			if(depthCalculate(i) <= 2)
				answer++;;
		}
		System.out.println(answer);
	}

	private static int depthCalculate(int node) {
		visited = new boolean[N+1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {1, 0}); //노드번호, depth
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int num = cur[0]; int depth = cur[1];
			if(num == node) {
				return depth;
			}
			for(int i=1; i<=N; i++) {
				if(graph[num][i]==1 && !visited[i]) {
					visited[i]=true;
					q.add(new int[] {i, depth+1});
				}
			}
		}
		return 3; //node와 1이 연결되어 있지 않을 때
	}
}
