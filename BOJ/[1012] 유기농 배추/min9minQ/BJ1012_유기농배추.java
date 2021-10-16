package time12;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1012_유기농배추 {

	static int M,N,K;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			
			map = new int[M][N];
			visit = new boolean[M][N];
			
			for(int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[x][y] = 1;
			}
			//System.out.println(Arrays.deepToString(map));
			int result = 0;
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1 && visit[i][j] == false) {
						dfs(i,j);
						result++;
					}
				}
			}
			System.out.println(result);
			
		}

	}

	private static void dfs(int x, int y) {
		visit[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >=0 && nx < M && ny >= 0 && ny < N) {
				if(map[nx][ny]==1 && visit[nx][ny] == false) {
					dfs(nx,ny);
				}
			}
		}
		
	}

}
