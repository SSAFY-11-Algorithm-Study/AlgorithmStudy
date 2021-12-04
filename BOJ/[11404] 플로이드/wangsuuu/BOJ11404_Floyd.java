import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//"모든 도시의 쌍"에 대해서 "비용의 최솟값"구하기 -> 플로이드 워샬

public class BOJ11404_Floyd {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); //도시의 갯수
		int m = Integer.parseInt(br.readLine()); //버스의 갯수
		int INF = 9999999;
		int[][] matrix = new int[n+1][n+1]; //도시 번호는 1부터 시작
		
		StringTokenizer st;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(matrix[start][end]==0) {
				matrix[start][end]=cost;
			} else {
				if(matrix[start][end] > cost)
					matrix[start][end]=cost; //최솟값으로 업데이트
			}
		}//입력 완료
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i!=j && matrix[i][j]==0) { //i에서 j로 갈 수 없다면
					matrix[i][j]=INF;
				}
			}
		}
		
		for(int k=1; k<=n; k++) { //경유지
			for(int i=1; i<=n; i++) { //출발지
				if(i==k) continue;
				for(int j=1; j<=n; j++) { //도착지
					if(k==j || i==j) continue;
					if(matrix[i][j] > matrix[i][k] + matrix[k][j])
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}
		
		for(int i=1;i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(matrix[i][j]==INF)
					System.out.print(0 + " ");
				else
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
