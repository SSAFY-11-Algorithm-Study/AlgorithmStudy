package time11;

import java.util.LinkedList;
import java.util.Queue;

public class PM_게임맵최단거리 {
	
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
	static boolean[][] visit = new boolean[maps.length][maps[0].length];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) {
		int answer = 0;
		
		System.out.println(bfs(answer));
		
		
	}
	static int bfs(int answer) {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0));
		answer ++;
		visit[0][0] = true;
		
		while(!que.isEmpty()) {
			int size = que.size();
			answer++;
			for(int i = 0; i < size; i++) {
				Point temp = que.poll();
				
				for(int j = 0; j < 4; j++) {
					int nx = temp.x + dx[j];
					int ny = temp.y + dy[j];
					
					if(nx == maps.length-1 && ny == maps[0].length-1) {
						return answer;
					}
					
					if(nx >=0 && nx < maps.length && ny >= 0 && ny < maps[0].length && maps[nx][ny] == 1 && visit[nx][ny] == false) {
						visit[nx][ny] = true;
						que.offer(new Point(nx,ny));
					}
				}
			}
		}
		answer = -1;
		return answer;
	}
}
