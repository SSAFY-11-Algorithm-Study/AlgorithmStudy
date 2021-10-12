package week12;


import java.util.Scanner;

public class BOJ1012_유기농배추 {
	static int map[][]; // 인접배열
	static boolean visited[][];
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int row;
	static int col;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int a = 0;

		while(a<tc) {
			row= sc.nextInt();
			col = sc.nextInt();
			map = new int [row][col];
			visited = new boolean [row][col];
			int num = sc.nextInt();

			//기본 맵 세팅
			for(int i=0; i< num ; i++) {
				int x= sc.nextInt();
				int y = sc.nextInt();
				map[x][y] = 1;
				visited[x][y] = false;
			}

			int answer = 0;
			for(int i = 0 ; i < row ; i ++) {
				for(int j = 0 ; j < col ; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						//DFS 호출하는 횟수가 곧 유기농 배추 모여있는 갯수가됨
						answer++;
						dfs(i,j);
					}
				}
			}

			System.out.println(answer);
			a++;
		}
	}

	static void dfs(int x, int y) {
		visited[x][y] = true;

		for(int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx >= 0 && ny >= 0 && nx < row && ny < col) {
				if(map[nx][ny]==1 && !visited[nx][ny])
					dfs(nx,ny);
			}
		}
	}
}


