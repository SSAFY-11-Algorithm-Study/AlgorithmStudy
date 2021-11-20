package time17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620_꽃길 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	static int n;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(answer);
	}

	private static void dfs(int cnt, int cost) {
		if (cnt == 3) {
			answer = Math.min(answer, cost);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				if(isPossible(i,j)==false) continue;
				
				int temp = map[i][j];
				visited[i][j] = true;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					temp += map[nx][ny];
					visited[nx][ny] = true;
				}
				
				dfs(cnt+1,cost+temp);
				
				visited[i][j] = false;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					visited[nx][ny] = false;
				}
				
			}
		}
	}

	private static boolean isPossible(int i, int j) {
		if (visited[i][j]) {
			return false;
		}
		for (int k = 0; k < 4; k++) {
			int nx = i +dx[k];
			int ny = j +dy[k];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= n)
				return false;
			if(visited[nx][ny])
				return false;
		}
		return true;
	}
	

}
