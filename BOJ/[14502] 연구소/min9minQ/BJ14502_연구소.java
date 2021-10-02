package time10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_연구소 {
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int N,M;
	static int[][] map;
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1 ,1};
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeWall(0);
		System.out.println(result);
	}
	
	static void makeWall(int cnt) {
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					makeWall(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void bfs() {
		Queue<Point> que = new LinkedList<>();
		int[][] copy = new int[N][M];
		//카피
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		//
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j < M; j++) {
				if(copy[i][j] == 2) {
					que.offer(new Point(i,j));
				}
			}
		}
		
		while(!que.isEmpty()) {
			Point v = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(copy[nx][ny] == 0) {
						copy[nx][ny] = 2;			
						que.offer(new Point(nx,ny));
					}
				}
				
			}
		}
		
		//while문 끝나면 카운트
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copy[i][j] == 0) {
					count ++;
				}
			}
		}
		result = Math.max(result, count);
	}
}