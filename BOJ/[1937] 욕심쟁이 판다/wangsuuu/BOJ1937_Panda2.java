import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//완탐 dfs -> 시간초과
// dfs + dp 메모이제이션 활용해야 함. (https://steady-coding.tistory.com/39 참고)

public class BOJ1937_Panda2 {
	
	static int N;
	static int answer= -1;
	static int[][] map, dp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //입력 완료
		
		dp = new int[N][N]; //해당 위치에서 출발할 경우 갈 수 있는 최대 칸수를 저장.
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int n = dfs(i, j);
				answer = Math.max(answer, n);
			}
		}
		System.out.println(answer+1);
	}

	private static int dfs(int x, int y) {
		
		if(dp[x][y]!=0) { //이미 최댓값이 구해져 있는 상태라면 바로 그 값을 반환(메모이제이션)
			return dp[x][y];
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny] > map[x][y]) {
				//무조건 그 다음 값이 큰 쪽으로만 이동하기 때문에 visit 배열 필요없음.
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny)+1);
			}
		}
		return dp[x][y];
	}
}
