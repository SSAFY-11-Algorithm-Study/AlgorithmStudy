package time13;

import java.util.Scanner;

public class BJ14503_로봇청소기 {
	
	static int N, M;
	static int[][] map;
	static int r,c,d;
	static int result = 1;
	static int[] dx = {-1, 0, 1, 0}; 
    static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		clean(r,c,d);
		System.out.println(result);
	}

	private static void clean(int x, int y, int d) {
		map[x][y] = -1;
		
		for(int i = 0; i < 4; i++) {
            d -= 1; // 왼쪽으로 돌기
            if(d == -1) d = 3;
            
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if(map[nx][ny] == 0) { 
                    result++;
                    clean(nx, ny, d);
                   
                    return;
                }
            }
        }
        
		//청소할 곳 x
        int nd = (d + 2) % 4; 
        int tx = x + dx[nd];//후진
        int ty = y + dy[nd];//후진
        if(tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 1) {
            clean(tx, ty, d); //후진할 때 방향을 유지해야 한다.
        }
		
		
	}

}
