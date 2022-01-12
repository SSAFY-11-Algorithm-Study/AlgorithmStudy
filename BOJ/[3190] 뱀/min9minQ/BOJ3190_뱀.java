//테케 1,2는 맞는데 3 틀림..
//다시 확인
package time23;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3190_뱀 {
	
	private static class Point {
		int x;
		int y;
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int N,time;
	private static int[][] map;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static Map<Integer,String> move = new HashMap<>();
	private static Queue<Point> que = new LinkedList<>();
	
	public static void main(String[] args) {
		
		//입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		int K = sc.nextInt();
		for(int i = 0; i < K; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		int L = sc.nextInt();
		for(int i = 0; i < L; i++) {
			move.put(sc.nextInt(), sc.next());
		}
		//입력 끝.
		
		que.add(new Point(0,0));
		int dir = 0;
		int sx = 0;
		int sy = 0;
		
		while(true) {
			
			time ++;
			
			int nx = sx + dx[dir];
			int ny = sy + dy[dir];
			//System.out.println(nx);
			//System.out.println(ny);
			
			//벽 이나 몸통
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || que.contains(new Point(nx,ny))) {
				break;
			}
			if(map[ny][nx] == 1) {
				//System.out.println("test");
				map[ny][nx] = 0;
				que.add(new Point(nx,ny));
			} else {
				que.add(new Point(nx,ny));
				que.poll();
			}
			
			//시간정보에 방향 바꿔야할 타이밍이 담겨있으면
			
			if(move.containsKey(time)) {
				String data = move.get(time);
				if(data.equals("D")) {
					dir +=1;
					if(dir==4)  dir=0;
				}else {
					dir -=1;
					if(dir==-1) dir=3;
				}
			}
			sx = nx;
			sy = ny;
		}
		System.out.println(time);
		
	}

}
