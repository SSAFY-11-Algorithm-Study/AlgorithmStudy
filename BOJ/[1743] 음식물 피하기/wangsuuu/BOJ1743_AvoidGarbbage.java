import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1743_AvoidGarbbage {
	
	static int N, M, K;
	static int cnt=0;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		K = Integer.parseInt(st.nextToken()); //음식물의 갯수
		map= new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}//음식물 위치 표시
		int answer=0;
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=M; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					cnt=0; //초기화
					dfs(i , j);
					answer = Math.max(answer, cnt);
				}
			}
		}
		System.out.println(answer);
	}
	private static void dfs(int x, int y) {
		visited[x][y]=true;
		cnt++;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0 || nx>N || ny<0 || ny>M)
				continue;
			if(map[nx][ny]==1 && !visited[nx][ny])
				dfs(nx, ny);
		}
	}
}
