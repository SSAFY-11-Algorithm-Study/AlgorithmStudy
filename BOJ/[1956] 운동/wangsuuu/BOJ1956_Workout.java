import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//갈피를 못잡아서 인터넷에서 힌트 얻어서 품
/*
어느 정점에서 얼만큼의 사이클이 최소비용 사이클인지 모름 
-> "모든 정점에서의 모든 쌍"의 최단거리 정보가 필요 -> "플로이드 와샬 알고리즘"!!!
*/

public class BOJ1956_Workout {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V, E;
		final int INF = 1000000000; //20억으로 하니까 오버플로우 (int최댓값이 21억..) 
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int dist[][] = new int[V+1][V+1]; //1번부터 시작. i에서 j까지의 최단거리를 저장할 배열
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[s][e]=c;
		}
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i!=j && dist[i][j]==0) //연결되어 있지 않다면 INF
					dist[i][j]=INF;
			}
		}
		
		//플로이드 와샬
		for(int k=1; k<=V; k++) {//경유지
			for(int i=1; i<=V; i++){//출발지
				if(k==i) continue;
				for(int j=1; j<=V; j++) {//도착지
					if(k==j || i==j) continue;
					if(dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
		int answer = INF;
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j) continue;
				if(dist[i][j] != INF && dist[j][i] != INF) { //사이클이 존재한다는 뜻이므로
					int cycle = dist[i][j] + dist[j][i];
					answer = Math.min(answer, cycle);
				}
			}
		}
		
		if(answer==INF)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}
