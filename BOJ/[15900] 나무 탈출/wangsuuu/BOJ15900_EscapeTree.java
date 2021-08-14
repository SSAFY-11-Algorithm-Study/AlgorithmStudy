//테케는 맞는데 채점하면 틀렸다고 뜹니다ㅠ

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// ** 공통된 규칙 찾기!!! **
//각 리프노드부터 루트노드까지의 깊이의 합이 짝수이면 지고, 홀수이면 이김.
// dfs로 각 깊이 구하기

public class BOJ15900_EscapeTree {

	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;
	static int sum; //각 리프 노드 깊이의 합
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //정점의 갯수
		list = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[N+1];
		StringTokenizer st;
		
		//노드 번호는 1번부터 시작. 노드 갯수만큼 list 칸 생성
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			list.get(node1).add(node2);
			list.get(node2).add(node1);
		}
		//트리 생성 완료
		
		visited[1] = true;
		dfs(1, 0);
		System.out.println((sum%2==0) ? "NO" : "YES");
		
	}
	
	public static void dfs(int start, int cnt) { //각 leaf node를 찍고 나서, 그 깊이를 answer에 더함.
		
		if(start!=1 && list.get(start).size()==1) { //리프노드이면 갯수 더하고 끝냄
			sum += cnt;
			return;
		}
		
		for(int i=0; i<list.get(start).size(); i++) {
			int nodeNum = list.get(start).get(i);
			if(!visited[nodeNum]) {
				visited[nodeNum]=true;
				dfs(nodeNum, cnt+1);
			}
			
		}
	}
}
