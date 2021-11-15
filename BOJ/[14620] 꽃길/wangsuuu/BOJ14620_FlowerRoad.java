import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14620_FlowerRoad {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1, 1, 0, 0};
	static int[] dy= {0, 0, -1, 1};
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		dfs(0,0);
		System.out.println(result);
		
	}
	private static void dfs(int cnt, int cost) {
		
		if(cnt==3) {
			result = Math.min(result, cost);
			return;
		}
		
		for(int i=1; i<N-1; i++) { //테두리 부분은 뽑을 수 없으므로 (1,1)~(N-2,N-2)만 try
			for(int j=1; j<N-1; j++) {
				if(Possible(i, j)) { //(i, j)에 꽃을 심을 수 있다면
					int sum=0;
					visited[i][j]=true;
					sum += map[i][j];
					for(int k=0; k<4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						visited[ni][nj]=true;
						sum+=map[ni][nj];
					}
					
					dfs(cnt+1, cost+sum);
					//다 구하고 돌아와서, 다음 경우를 try해보기 위해 원상복구 작업
					visited[i][j]=false;
					for(int k=0; k<4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						visited[ni][nj]=false;
					}
				}
			}
		}
	}
	
	private static boolean Possible(int x, int y) {
		
		if(visited[x][y])
			return false;
		else{
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(visited[nx][ny])
					return false;
			}
			return true;
		}
	}

}
