import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012_Cabbage {
	
	static int N, M, K;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken()); //가로 길이
			N = Integer.parseInt(st.nextToken()); //세로 길이
			K = Integer.parseInt(st.nextToken()); //배추 심어져 있는 위치 갯수
			map = new int[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[x][y] = -1;
			}
			
			int cnt=1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==-1) {
						dfs(i, j, cnt);
						cnt++;
					}
				}
			}
			System.out.println(--cnt);
		}
	}
  
	private static void dfs(int x, int y, int cnt) {
		
		map[x][y] = cnt;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny]==-1) {
				dfs(nx, ny, cnt);
			}
		}
	}

}
