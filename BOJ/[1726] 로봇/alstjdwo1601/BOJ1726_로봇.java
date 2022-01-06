package week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * https://mygumi.tistory.com/241 참고..
 * 2칸 못움직이면 3칸도 못움직여야함
 * 90도씩 도니까 180도 돌려면 2번 움직여야함
 */
public class BOJ1726_로봇 {
	static class Robot {
        int x;
        int y;
        int dir;
        int count;

        public Robot(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }
	
	static int M;
    static int N;
    static int[][] map;
    static boolean[][][] visited;
    static Robot start; //출발점
    static Robot end; //목적지
    static int[] dx = {0, 0, 1, -1}; //동, 서, 남, 북
    static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//순서 주의
		M = sc.nextInt();
		N = sc.nextInt();
		
		map = new int[M+1][N+1];
		visited = new boolean [M+1][N+1][5]; //4방향도 방문체크해줘야함
		
		//맵정보받기
		for(int i = 1 ; i <= M ; i ++) {
			for(int j = 1 ;j <= N ; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//시작점 도착점 저장해두기
		start = new Robot(sc.nextInt(), sc.nextInt(), sc.nextInt(), 0);
		end = new Robot(sc.nextInt(), sc.nextInt(), sc.nextInt(), 0);
		
		bfs();
	}
	
	private static void bfs() {
		Queue<Robot> q = new LinkedList<>();
        visited[start.x][start.y][start.dir] = true; //방문체크
        q.add(start); //시작점 담고 시작

        while (!q.isEmpty()) {
            Robot r = q.poll();
            int x = r.x;
            int y = r.y;
            int dir = r.dir;
            int count = r.count;

            //도착점에 도착하면 카운트 출력후 종료
            if (x == end.x && y == end.y && dir == end.dir) {
                System.out.println(count);
                return;
            }

            //1,2,3 움직이기
            for (int i = 1; i <= 3; i++) {
                int nx = x + (dx[dir - 1] * i);
                int ny = y + (dy[dir - 1] * i);

                //맵 밖으로 나가면 패스
                if (nx <= 0 || ny <= 0 || nx > M || ny > N) continue;

                //이동가능한 지점이고 방문아직안되어있으면 큐에담음
                if (map[nx][ny] == 0) {
                    if (!visited[nx][ny][dir]) {
                        visited[nx][ny][dir] = true;
                        q.add(new Robot(nx, ny, dir, count + 1));
                    }
                } 
                
                else 
                    break;
                
            }

            
            //방향바꾸기
            for (int i = 1; i <= 4; i++) {
                if (dir != i && !visited[x][y][i]) {
                    int turn = 1;
                    if (dir == 1) {
                        if (i == 2) 
                            turn++;
                        
                    } 
                    else if (dir == 2) {
                        if (i == 1) 
                            turn++;
                        
                    } 
                    else if (dir == 3) {
                        if (i == 4) 
                            turn++;
                        
                    } 
                    else {
                        if (i == 3) 
                            turn++;
                 
                    }

                    visited[x][y][i] = true;
                    q.add(new Robot(x, y, i, count + turn));
                }
            }
        }
	}

}
