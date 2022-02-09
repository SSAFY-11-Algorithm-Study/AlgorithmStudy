import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261_AlgoSpot {
	
	static class Point implements Comparable<Point>{
		int x, y, weight;

		public Point(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		public int compareTo(Point o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = tmp[j]-'0';
			}
		}//입력 완료
		
		System.out.println(bfs());
	}
	private static int bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>(); //가장 비용이 작은 점이 나옴.
		
		//시작점 넣음
		pq.add(new Point(0, 0, 0));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			if(cur.x == N-1 && cur.y == M-1) {
				return cur.weight;
			}
			
			for(int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny])
					continue;
				pq.add(new Point(nx, ny, cur.weight + map[nx][ny]));
				visited[nx][ny]=true;
			}
		}
		return -1;
	}
}
