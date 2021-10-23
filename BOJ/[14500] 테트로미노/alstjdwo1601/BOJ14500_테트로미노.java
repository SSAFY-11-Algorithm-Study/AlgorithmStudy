package week13;

import java.util.Scanner;

//dfs 로 할 생각을 전혀 못했고 블로그 참고했음
//dfs로 퍼져가는 모양이 테트로미노의 경우의 수와 같았고
// ㅗ ㅏ ㅓ ㅜ 모양은 따로 체크해줘야한다.
public class BOJ14500_테트로미노 {
	static int N,M ,answer;
	static int [][] map;
	static boolean [][] visited;
	static int [] dx= {0,1,0,-1};
	static int [] dy= {1,0,-1,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map  = new int[N][M];
		visited = new boolean[N][M];
		
		//맵받기
		for(int i = 0 ; i < N; i ++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//모든지점마다 dfs돌려보면서 완탐
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1 , map[i][j]);
                visited[i][j] = false;
                
                // ㅗ ㅜ ㅏ ㅓ 모양은 따로 체크
                check(i, j);
            }
        }
		
        System.out.println(answer);
	}
	
	private static void dfs(int x, int y, int depth, int sum) {
		if(depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//배열 범위 안쪽이면 사방으로 퍼져간다
			if(nx>=0 && nx < N && ny>=0 && ny < M && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx,ny,depth+1 , sum + map[nx][ny]); //다음지점 값 더해가면서 ㄱㄱ
				visited[nx][ny] = false;
			}
		}
	}
	
	//요건 노가ㅏ다
	private static void check(int x, int y) {
		int sum = 0;
	    // 1. ㅜ
	    if(x>=0 && x+1<N && y>=0 && y+2<M){
	        sum = map[x][y] + map[x][y+1] + map[x][y+2] + map[x+1][y+1];
	        answer = Math.max(answer, sum);
	    }

	    // 2. ㅏ
	    if(x >= 0 && x+2 <N && y>=0 && y+1<M){
	        sum = map[x][y] + map[x+1][y] + map[x+2][y] + map[x+1][y+1];
	        answer = Math.max(answer, sum);
	    }

	    // 3. ㅗ
	    if(x-1 >= 0&& x <N && y >=0 && y+2 < M){
	        sum = map[x][y] + map[x][y+1] + map[x][y+2] + map[x-1][y+1];
	        answer = Math.max(answer, sum);
	    }

	    // 4. ㅓ
	    if(x-1 >= 0 && x+1 <N && y >=0 && y+1 <M){
	        sum = map[x][y] + map[x][y+1] + map[x-1][y+1] + map[x+1][y+1];
	        answer = Math.max(answer, sum);
	    }
		
	}

}
