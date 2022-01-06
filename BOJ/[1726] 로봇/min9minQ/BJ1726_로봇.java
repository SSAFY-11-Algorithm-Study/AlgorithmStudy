//기존에 이용하던 queue 사이즈 이용해서  움직인 거리 계산하는 걸로 풀고 싶은데...
//답이 틀림ㅠ

// 이런경우 class 만들 때 count 해주는 방식으로 푸는게 좋을 것 같은데 하던 방법으로는 불가..?

package time22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1726_로봇 {
	
	private static class Point {
		int x;
		int y;
		int dir;
		
		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	private static int N,M;
	private static int[][] map;
	private static int[] dx = {0, 0, 0, 1, -1}; // 동서남북 1234
	private static int[] dy = {0, 1, -1, 0, 0};
	private static boolean[][][] visited;
	private static Point start, end;
	private static int result = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[5][N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		int dir = Integer.parseInt(st.nextToken());
		
		start = new Point(x,y,dir);
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()) - 1;
		y = Integer.parseInt(st.nextToken()) - 1;
		dir = Integer.parseInt(st.nextToken());
		
		end = new Point(x,y,dir);
		
		bfs();
		
		System.out.println(result);
		
	}

	private static void bfs() {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(start.x, start.y, start.dir));
		visited[start.dir][start.x][start.y] = true;
		int move = 0;
		
		while(!que.isEmpty()) {
			int size = que.size();
			move++;
			
			for(int i = 0; i < size; i ++) {
				Point temp = que.poll();
				
				if(temp.x == end.x && temp.y == end.y && temp.dir == end.dir) {
					result = move;
					return;
				}
				// go
				for(int j = 1; j <= 3; j++) {
					int nx = temp.x + dx[temp.dir] * j;
					int ny = temp.y + dx[temp.dir] * j;
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
						continue;
					}
					
					if(map[nx][ny] == 0) {
						if(!visited[temp.dir][nx][ny]) {
							visited[temp.dir][nx][ny] = true;
							que.offer(new Point(temp.dir,nx,ny));
						} else {
							break;
						}
					}
				}
				//turn
				for (int j = 1; j <= 4; j++) {
	                if (temp.dir != j && !visited[j][temp.x][temp.y]) {
	                    if (temp.dir == 1) {
	                        if (j == 2) {
	                            move++;
	                        }
	                    } else if (temp.dir == 2) {
	                        if (j == 1) {
	                            move++;
	                        }
	                    } else if (temp.dir == 3) {
	                        if (j == 4) {
	                            move++;
	                        }
	                    } else {
	                        if (j == 3) {
	                            move++;
	                        }
	                    }
	                    visited[j][temp.x][temp.y] = true;
	                    que.offer(new Point(j,temp.x,temp.y));
	                }
	            }
			}
		}
		
	}

}
