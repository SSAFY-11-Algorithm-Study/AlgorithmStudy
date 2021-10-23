//답은 맞는데 메모리 초과 나서 조금 더 생각 필요

package time13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4179_불 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Queue<Point> Jihoon = new LinkedList<>();
	static Queue<Point> fire = new LinkedList<>();
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int time = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'J') {
					map[i][j] = '.';
					visited[i][j] = true;
					Jihoon.offer(new Point(i, j));
				} else if (map[i][j] =='F') {
					fire.offer(new Point(i,j));
				}
			}
		}

		move();
		
		System.out.println(++time);

	}

	private static void move() {
		
		while (!Jihoon.isEmpty()) {
			burn();
			int size = Jihoon.size();
			time ++;
			for (int i = 0; i < size; i++) {

				Point temp = Jihoon.poll();
				
				for(int j = 0 ; j < 4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];
					
					if(nx == R-1 || ny == C-1) {
						return;
					}
					
					if (nx >= 0 && nx < R && ny >= 0 && ny < C && visited[nx][ny] == false &&map[nx][ny] == '.') {
						Jihoon.offer(new Point(nx,ny));
					}
				}
				
			}
		}
		System.out.print("IMPOSSIBLE");
	}

	private static void burn() {
		
		int size = fire.size();
		
		for (int k = 0; k < size; k++) {
			Point ftemp = fire.poll();

			for (int i = 0; i < 4; i++) {
				int nx = ftemp.x + dx[i];
				int ny = ftemp.y + dy[i];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
					map[nx][ny] = 'F';
					fire.offer(new Point(nx,ny));
				}
			}
		}
	}

}
