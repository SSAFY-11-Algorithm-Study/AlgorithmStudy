import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//다익스트라 사용

//같은 출발점과 도착점을 가지지만 비용이 다른 버스가 입력으로 들어올 경우 고려하기. (https://www.acmicpc.net/board/view/52793)

public class BOJ1916_MinCost {
	
	static class Node{
		int x, weight;
		Node link;
		
		public Node(int x, int weight, Node link) {
			super();
			this.x = x;
			this.weight = weight;
			this.link = link;
		}
	}
	
	static int N, M;
	static Node[] nodeList;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //도시의 갯수
		M = Integer.parseInt(br.readLine()); //버스의 갯수
		nodeList = new Node[N+1]; //인접 리스트 (도시 번호는 1부터이므로)
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//만약 같은 경로의 값이 들어온다면, 기존의 weight보다 더 작은 경우에만 그 값으로 update하기.
			if(isConnected(from, to)) {
				if(cost < findCost(from, to)) {
					for(Node tmp=nodeList[from]; tmp!=null; tmp=tmp.link) {
						if(tmp.x==to) tmp.weight=cost;
					}
				}
			} else {
				nodeList[from] = new Node(to, cost, nodeList[from]);
			}
		} //그래프 정보 입력 완료
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); //출발 도시
		int end = Integer.parseInt(st.nextToken()); //도착 도시
		//입력 완료
		
		int[] distance = new int[N+1]; //출발지에서 해당 정점까지의 거리
		boolean[] visited = new boolean[N+1]; //방문 여부 체크
		
		Arrays.fill(distance, Integer.MAX_VALUE); //초기화
		distance[start]=0; //시작점은 0으로 세팅
		
		
		for(int i=1; i<=N; i++) { //정점의 수만큼 반복
			
			int min=Integer.MAX_VALUE, minVertex=-1;
			
			//1. 아직 방문하지 않은 정점들 중에서 min값 찾아서 방문하기
			for(int j=1; j<=N; j++) {
				if(!visited[j] && min>distance[j]) {
					min = distance[j];
					minVertex=j;
				}
			}
			visited[minVertex]=true;
			
			if(minVertex==end) break; //목적지 도달했다면, 끝냄
			
			//2. 선택한 정점을 경유지로 삼아, 갈 수 있는 다른 방문하지 않은 정점들 거리 업데이트
			for(int j=1; j<=N; j++) {
				if(!visited[j] && isConnected(minVertex, j) && distance[j]>min+findCost(minVertex, j)) {
					//아직 방문 안했고, 서로 연결되어 있으며 minVertex를 경유한 거리가 더 가깝다면
					distance[j] = min+findCost(minVertex, j);
				}
			}
		}
		System.out.println(distance[end]);
	}

	private static int findCost(int minVertex, int j) {
		
		int cost=0;
		for(Node tmp = nodeList[minVertex]; tmp!=null ; tmp=tmp.link) {
			if(tmp.x==j)
				cost = tmp.weight;
		}
		return cost;
	}

	private static boolean isConnected(int minVertex, int j) {

		for(Node tmp = nodeList[minVertex]; tmp!=null; tmp=tmp.link) {
			if(tmp.x==j)
				return true;
		}
		return false;
	}
}
