import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//visited를 3차원 배열로 두고 다시 품 (인터넷 참고)
//가중치 없는 경우, BFS는 먼저 enqueue된 애가 최단 경로임이 보장됨.

public class BOJ2206_CrashWall {
			
	static int N, M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	//[0][][] : 벽을 부수지 않은 상태의 방문 배열,  [1][][] : 벽을 부순 상태에서의 방문 배열
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[2][N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}//입력 완료
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0, 0, 1, false));
		visited[0][0][0] = true; 
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			int x = p.x; int y = p.y;
			int dist = p.dist; boolean crash = p.crash;
			
			if(x==N-1 && y==M-1) //도착점이라면
				return dist;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M)//범위 밖이라면
					continue;
				
				if(map[nx][ny]=='0') { //벽이 아니면 -> 벽을 부수지 않고도 갈 수 있음
					if(crash==false && !visited[0][nx][ny]) { //그 전에 벽을 부순 적이 없다면
						q.add(new Pos(nx, ny, dist+1, false));
						visited[0][nx][ny]=true;
					} else if(crash==true && !visited[1][nx][ny]) { //그 전에 벽을 부순 적이 있다면
						q.add(new Pos(nx, ny, dist+1, true));
						visited[1][nx][ny]=true;
					}
				} else { //벽이라면 -> 부수고 가야 함
					if(crash==false && !visited[1][nx][ny]) { //벽을 부순 적이 없는 곳에서 온 애들만 부수고 갈 수 있음
						q.add(new Pos(nx, ny, dist+1, true)); //벽을 부숨
						visited[1][nx][ny]=true; //벽을 부쉈으므로 부순 방문 배열에 체크
					}
				}
			}
		}
		return -1;
	}

	static class Pos{ //좌표, 시작접에서부터의 거리, 벽을 부쉈는지의 여부
		int x, y, dist;
		boolean crash;
		public Pos(int x, int y, int dist, boolean crash) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crash = crash;
		}
	}
}
