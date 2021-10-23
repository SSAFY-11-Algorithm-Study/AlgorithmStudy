import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179_Fire {

	static int R, C;
	static char[][] map; 
	static Queue<int[]> fire, jh;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		fire = new LinkedList<>();
		jh = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='J') {
					jh.add(new int[] {i, j, 0}); //지훈이의 위치와 그때의 경과 시간 저장
					map[i][j]='.'; //빈칸으로 바꿔놓기
					visited[i][j]=true;
				} else if(map[i][j]=='F') {
					fire.add(new int[] {i, j});
				}
			} 
		}
		
		int answer=bfs();
		
		System.out.println(answer==-1 ? "IMPOSSIBLE" : answer);
	}

	private static int bfs() {
		
		while(!jh.isEmpty()) {
			
			//동시간에 대해서, 불이 먼저 번진 다음에 지훈이가 지나감 (불이 퍼지는 곳으로 동시에 지훈이가 가면 죽음) 
			int f_size = fire.size();
			
			for (int i = 0; i < f_size; i++) {
				int[] F = fire.poll();
				int Fx = F[0], Fy = F[1];
				for (int j = 0; j < 4; j++) {
					int nFx= Fx+dx[j];
					int nFy = Fy + dy[j];
					if(isValid(nFx, nFy) && map[nFx][nFy]=='.') {
						map[nFx][nFy] = 'F';
						fire.add(new int[] {nFx, nFy});
					}
				}
			}
			
			int j_size = jh.size();
			
			for (int i = 0; i < j_size; i++) {
				int[] JH = jh.poll();
				int jx = JH[0], jy = JH[1], time=JH[2];
				
				if((jx==0 || jx==R-1) || (jy==0 || jy==C-1) && map[jx][jy]=='.') {
					return ++time;
				}
				
				for (int j = 0; j < 4; j++) {
					int njx = jx + dx[j];
					int njy = jy + dy[j];
					if(isValid(njx, njy) && !visited[njx][njy] && map[njx][njy]=='.') {
						jh.add(new int[] {njx, njy, time+1});
						visited[njx][njy] = true;
					}
				}
			}
		}
		return -1;
	}

	private static boolean isValid(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}

}
