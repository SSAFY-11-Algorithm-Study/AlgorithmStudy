package week18;

import java.util.Scanner;

public class BOJ1743_음식물피하기 {
	static int N,M,K;
	static int answer =-1;
	static int map[][] ;
	static boolean visited[][] ;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N= sc.nextInt();
		M= sc.nextInt();
		K= sc.nextInt();

		map = new int[N][M];


		for(int i = 0 ; i < K ; i ++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			map[x-1][y-1] = 1;
		}

		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map[i][j]==1) {
					visited = new boolean[N][M];
					dfs(i,j);
				}
			}
		}

		System.out.println(answer);
	}
	private static void dfs(int x, int y) {
		visited[x][y] = true;

		boolean flag = false;
		for(int i = 0 ; i < 4; i ++) {
			int nx =x + dx[i];
			int ny =y + dy[i];

			if(nx>=0 && ny>=0 && nx<N && ny<M 
					&&!visited[nx][ny]&& map[nx][ny]==1) {
				flag = true;
				dfs(nx,ny);
			}
		}

		if(!flag) {
			int cnt = 0;
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < M ; j ++) {
					if(visited[i][j]) {
						cnt++;
					}
				}
			}
			answer = Math.max(answer, cnt );
			return;
		}

		

	}

}
