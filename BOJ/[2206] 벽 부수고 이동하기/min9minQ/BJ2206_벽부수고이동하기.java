/*
 *  답이 다르게 나오는데 뭐가 틀렸는 지 아직 못찾음 ,.. ㅠ
 * 
 */
package time11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2206_벽부수고이동하기 {
	
	static class Point{
		int x,y,drill;
		public Point(int x, int y, int drill) {
			this.x = x;
			this.y = y;
			this.drill = drill;
		}
	}
	
	static int N,M;
	static int[][] map;
	static boolean[][] visit;
	static int result = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs();
		
		System.out.println(result);

	}

	private static void bfs() {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0,0));
		visit[0][0] = true;
		result++;
		
		while(!que.isEmpty()) {
			int size = que.size();
			result++;
			for(int i = 0; i < size; i++) {
				Point temp = que.poll();
				
				for(int j = 0 ; j < 4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];
					
					if(nx == N-1 && ny == M-1)
					return;
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < M && visit[nx][ny] == false) {
						if(map[nx][ny] == 0) {
							que.offer(new Point(nx,ny,temp.drill));
							visit[nx][ny] = true;
						} else {
							if(temp.drill == 0) {
								que.offer(new Point(nx,ny,temp.drill++));
								visit[nx][ny] = true;
							}
						}
					}
				}
				
			}
		}
		result = -1;
	}

}
