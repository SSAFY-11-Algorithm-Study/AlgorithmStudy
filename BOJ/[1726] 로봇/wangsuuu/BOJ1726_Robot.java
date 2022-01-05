import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1726_Robot {
	
	static int M, N;
	static int[][] map;
	static int[] dx = {0, 0, 0, 1, -1}; //동:1, 서:2, 남:3, 북:4
	static int[] dy = {0, 1, -1, 0, 0};
 
	static class Robot{
		int x, y, dir, count;
		public Robot(int x, int y, int dir, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}
	static Robot start;
	static Robot end;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken()) - 1; //map은 0,0 부터 시작하니까
		int sy = Integer.parseInt(st.nextToken()) - 1;
		int sdir = Integer.parseInt(st.nextToken());
		start = new Robot(sx, sy, sdir, 0);
		
		st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken()) - 1;
		int ey = Integer.parseInt(st.nextToken()) - 1;
		int edir = Integer.parseInt(st.nextToken());
		end = new Robot(ex, ey, edir, 0);
		//입력 완료
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Robot> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][M][N]; //네 방향의 상태로 방문했을 때를 각각 관리하기 위해 3차원 visited 배열 사용
		q.add(new Robot(start.x, start.y, start.dir, start.count));
		visited[start.dir][start.x][start.y] = true;
		
		while(!q.isEmpty()) {
			Robot r = q.poll();
			int x = r.x; int y = r.y; int dir = r.dir; int count = r.count;
			
			if(x==end.x && y==end.y && dir == end.dir) {
				return count;
			}
			
			//1, 2, 3칸을 각각 가 보고 enqueue
			for(int i=1; i<=3; i++) {
				int nx = x + dx[dir] * i;
				int ny = y + dy[dir] * i;
				if(nx<0 || nx>=M || ny<0 || ny>=N || map[nx][ny]==1) {
					if(i==1 || i==2)
						break;
					continue;
				}
				if(!visited[dir][nx][ny]) {
					q.add(new Robot(nx, ny, dir, count+1));
					visited[dir][nx][ny]=true;
				}
			}
			
			//방향을 바꿔보기
			int left=0, right=0;
			switch (dir) {
			case 1:
				left=4; right=3; break;
			case 2:
				left=3; right=4; break;
			case 3:
				left=1; right=2; break;
			case 4:
				left=2; right=1; break;
			}
			if(!visited[left][x][y]) {
				q.add(new Robot(x, y, left, count+1));
				visited[left][x][y]=true;
			}
			if(!visited[right][x][y]) {
				q.add(new Robot(x, y, right, count+1));
				visited[right][x][y]=true;
			}
		}
		return -1;
	}
}
