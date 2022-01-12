//그냥 dfs는 시간초과..
// memoization (피보나치할때 기억해둬서 계산 줄이는 방법) 사용

package time23;

import java.util.Scanner;

public class BJ1937_욕심쟁이판다 {
	
	private static int n;
	private static int[][] map;
	private static int[][] memo;
	private static int[] dx = {-1, 1, 0 ,0};
	private static int[] dy = {0, 0, -1 ,1};
	private static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		memo = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				answer = Math.max(answer, dfs(i,j));
			}
		}
		
		System.out.println(answer);

	}

	private static int dfs(int x, int y) {
		
		if(memo[x][y] != 0) {
			return memo[x][y];//이미 방문했을때 그 값 리턴
		}
		
		memo[x][y] = 1; //첫칸 초기화
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[x][y] < map[nx][ny]) {
				memo[x][y] = Math.max(memo[x][y], dfs(nx,ny)+1);
			}
		}
		return memo[x][y];
	}

}
