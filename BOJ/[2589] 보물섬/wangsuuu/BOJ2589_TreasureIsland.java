import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589_TreasureIsland {
	
	static int N, M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int answer = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//입력 완료
		
		
		//각 L에서 최장 거리를 모두 구해보고 그 중 max 값 저장
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='L') {
					int value = bfs(i, j);
					answer = Math.max(answer, value);
				}
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int i, int j) {
		int value=-1;
		visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {i, j, 0});
		visited[i][j]=true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0]; int y = cur[1]; int time = cur[2];
			
			for(int k=0; k<4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny]) continue;
				if(map[nx][ny]!='L') continue;
				
				q.add(new int[] {nx, ny, time+1});
				visited[nx][ny]=true;
				value = Math.max(value, time+1);
			}
		}
		return value;
	}
}
