//DFS로 풀다가 실패해서, 그 부분만 블로그 참고함. (https://zoonvivor.tistory.com/145)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
	//하, 좌, 우
	static int[] dx = {1, 0, 0};
	static int[] dy = {0, -1, 1};
	static int N, M, answer = -1;
	static int[][] paper;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0); //nx, ny부터 visited 체크 하므로, sum과 depth는 0부터.
			}
		}
		
		Rest(); // 凸 모양은 dfs로 처리 안되므로
		
		System.out.println(answer);
	}


	private static void dfs(int x, int y, int sum, int depth) {
		
		if(depth==4) { 
			answer= Math.max(answer, sum);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny])
				continue;
			
			visited[nx][ny]=true;
			dfs(nx, ny, sum+paper[nx][ny], depth+1);
			visited[nx][ny]=false; //원상복구
		}
	}
	
	private static void Rest() {
		
		for (int i = 0; i < M; i++) { // 세로 세칸
			for (int j = 0; j < N-2; j++) {
				int sum = (paper[j][i] + paper[j+1][i] + paper[j+2][i]);
				//중간에 앞, 뒤로 한칸씩 붙여보기
				if(i+1<M) {
					int tmp = sum;
					tmp += paper[j+1][i+1];
					answer = Math.max(answer, tmp);
				}
				if(i-1>=0) {
					int tmp = sum;
					tmp += paper[j+1][i-1];
					answer = Math.max(answer, tmp);
				}
			}
		}
		
		for (int i = 0; i < N; i++) { // 가로 세칸
			for (int j = 0; j < M-2; j++) {
				int sum = (paper[i][j] + paper[i][j+1] + paper[i][j+2]);
				//중간에 위, 아래로 한칸씩 붙여보기
				if(i+1<N) {
					int tmp = sum;
					tmp += paper[i+1][j+1];
					answer = Math.max(answer, tmp);
				}
				if(i-1>=0) {
					int tmp = sum;
					tmp += paper[i-1][j+1];
					answer = Math.max(answer, tmp);
				}
			}
		}
	}
}
