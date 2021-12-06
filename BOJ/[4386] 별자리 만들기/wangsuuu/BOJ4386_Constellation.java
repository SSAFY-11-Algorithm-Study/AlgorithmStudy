import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//MST - Prim 알고리즘 사용

public class BOJ4386_Constellation {
	
	static int n;
	static double[][] star;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); //별의 갯수
		star = new double[n][2];
		double[][] graph = new double[n][n]; //별 i에서 j까지의 거리(비용)저장
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}//입력 완료
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				graph[i][j] = Math.sqrt(Math.pow(star[i][0] - star[j][0], 2) + Math.pow(star[i][1] - star[j][1], 2));
			}
		}//그래프 완성
		
		//Prim - 정점을 n개까지, minEdge가 작은 순서대로 하나씩 선택해 나가기
		double result=0.0;
		double[] minEdge = new double[n]; //정점으로 들어오는 간선들 중 최소비용을 저장
		Arrays.fill(minEdge, Double.MAX_VALUE);
		boolean[] visited = new boolean[n]; //MST에 포함되었는지 여부
		
		minEdge[0]=0; //임의의 시작점을 0으로 세팅(맨 처음에 선택되게 하기 위해서)
		for(int i=0; i<n; i++) {
			//1. MST에 포함되지 않은 정점들 중 minEdge의 값이 최소인 정점을 구하기
			double min = Double.MAX_VALUE;
			int minVertex = -1;
			for(int j=0; j<n; j++) {
				if(!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			//2. 구한 정점을 MST에 포함시키기
			result += minEdge[minVertex];
			visited[minVertex]=true;
			
			//3. 방금 포함시킨 정점을 거칠 경우 minEdge가 더 작아지는 경우들은 업데이트
			for(int j=0; j<n; j++) {
				if(!visited[j] && minEdge[j] > graph[minVertex][j])
					minEdge[j] = graph[minVertex][j];
			}
		}
		System.out.println(result);
	}
}
