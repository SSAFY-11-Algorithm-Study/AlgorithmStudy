// 답이 틀림...
package time24;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ11559_PuyoPuyo {
	
	private static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static char[][] map = new char[12][6];
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static boolean[][] visited;
	private static int result = 0;
	private static Queue<Point> list = new LinkedList<>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 12; i++) {
			String str = sc.nextLine();
			for(int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		play();
		
		System.out.println(result);

	}
	
	
	private static void play() {
		while(true) {
			boolean flag = true; //터트릴게 없나?
			visited = new boolean[12][6];
			
			for(int i = 0; i < 12; i++) {
				for(int j = 0; j < 6; j++) {
					if(visited[i][j]== false && map[i][j] != '.')
						bfs(i,j);
					if(list.size()>=4) { //4개 이상 붙어있으면  폭파시켜주기
						flag = false; // 4개이상인게 아직 있음
						while(!list.isEmpty()) {
							System.out.println("test");
							Point temp = list.poll();
							map[temp.x][temp.y] = '.';
						}
					}
				}
			}
			// 맵을 전체 한턴 훑었어.
			down();
			if(flag == true) // 4개이상인게 없었어
				break;
			else
				result ++;
		}
	}


	private static void bfs(int x, int y) {
		Queue<Point> que = new LinkedList<>();
		
		visited[x][y] = true;
		que.offer(new Point(x,y));
		
		list.add(new Point(x,y)); // 4개이상인지 갯수 세기 위해서
		
		while(!que.isEmpty()) {
			Point temp = que.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !visited[nx][ny] && map[nx][ny] == map[nx][ny]) {
					list.add(new Point(nx,ny));
					que.offer(new Point(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
	}


	public static void down() {        
        for (int i = 0; i < 6; i++) {
            for (int j = 11; j > 0; j--) {
                if (map[j][i] == '.') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '.') {
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

}
