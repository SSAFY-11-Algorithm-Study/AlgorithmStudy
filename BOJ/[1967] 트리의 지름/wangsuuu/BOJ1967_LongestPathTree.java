import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//인터넷으로 아이디어 참고함..
//임의의 노드에서 가장 먼 거리의 노드 구하기(a) -> a노드에서 가장 먼 노드 구하기 : 2번의 DFS 

public class BOJ1967_LongestPathTree {
	
	static class Node{
		int child, weight;

		public Node(int child, int weight) {
			super();
			this.child = child;
			this.weight = weight;
		}
	}
	
	static int N;
	static ArrayList<Node>[] list; //배열의 한칸 한칸에는 ArrayList<Node>가 들어갈 수 있음.
	static boolean[] visited;
	static int maxDist=-1;
	static int res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //노드의 갯수
		list= new ArrayList[N+1]; //노드 번호는 1번부터 시작
		
		for(int i=0; i<N+1; i++) { //이 과정 필요함!
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken());
			list[p].add(new Node(c,w));
			list[c].add(new Node(p,w));
		}//트리 정보 입력 완료
		
		//1. 임의의 정점(루트노드 1)에서 가장 거리가 먼 노드 구하기
		visited = new boolean[N+1];
		dfs(1, 0);
		
		//다시 세팅
		visited = new boolean[N+1];
		maxDist=-1;
		int result1 = res;
		res=0;
		dfs(result1, 0);
		System.out.println(maxDist);
	}

	private static void dfs(int start, int dist) {
		
		visited[start]=true;
		
		for(int i=0; i<list[start].size(); i++) {
			Node tmp = list[start].get(i);
			if(!visited[tmp.child]) {
				dfs(tmp.child, dist+tmp.weight);
			}
		}
		
		//탐색이 다 끝나거나 리프 노드일 경우
		if(maxDist<dist) {
			maxDist= dist;
			res = start;
		}
	}
}
