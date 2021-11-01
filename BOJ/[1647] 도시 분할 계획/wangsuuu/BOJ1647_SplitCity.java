import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//전체 그래프에 대한 MST를 크루스칼로 구하기 -> 그 중 최대 간선 하나를 빼기

public class BOJ1647_SplitCity {

	static int N, M;
	static Edge[] list;
	static int[] parents;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //집의 갯수
		M = Integer.parseInt(st.nextToken()); //길의 갯수
		list = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[i] = new Edge(start, end, cost);
		}//입력 완료
		
		Arrays.sort(list);
		
		make();//단위집합 만들기
		
		int result = 0;
		int maxEdge = -1;
		int cnt=0;
		for(int i=0; i<M; i++) {
			Edge e= list[i];
			int start = e.start; int end = e.end;
			int cost = e.cost;
			if(union(start, end)) {
				result += cost;
				maxEdge = Math.max(maxEdge, cost);
				cnt++;
			}
			if(cnt==N-1) //MST 완성했다면
				break;
		}
		
		System.out.println(result - maxEdge);
	}

	private static void make() {
		parents = new int[N+1]; //집 번호는 1번부터
		for(int i=1; i<=N; i++)
			parents[i] = -1;
	}
	
	private static int find(int a) { //a의 부모 찾기
		
		if(parents[a]==-1)
			return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) //a와 b의 부모가 같으므로, 이미 합쳐져 있음 -> union 불가
			return false;
		
		parents[bRoot] = aRoot; //b 무리를 a 무리 밑으로 합침.
		return true;
	}

	static class Edge implements Comparable<Edge>{
		int start, end, cost;
		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
}
