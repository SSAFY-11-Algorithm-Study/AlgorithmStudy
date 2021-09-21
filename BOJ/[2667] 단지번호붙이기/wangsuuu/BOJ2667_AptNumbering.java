import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2667_AptNumbering {

	static int N;
	static int[][] apt;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		apt = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String tmp = br.readLine();
			for(int j=0; j<N; j++) {
				apt[i][j] = tmp.charAt(j)-'0';
				if(apt[i][j]==1) //아파트 단지 번호를 1부터 붙이기 위해서
					apt[i][j] = -1;
			}
		}//입력 완료
		
		int num=1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(apt[i][j]==-1) {
					dfs(i, j, num); //단지 번호 붙이기
					num++;
				}
			}
		}
		
		System.out.println(--num); //총 단지 수
		
		int[] cnts = new int[num];
		
		//단지 내 집의 수 각각 구하기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(apt[i][j]!=0) {
					cnts[apt[i][j]-1]++;
				}
			}
		}
		
		Arrays.sort(cnts);
		for(int i : cnts)
			System.out.println(i);
		
	}
	
	private static void dfs(int x, int y, int num) {
	
		apt[x][y] = num;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<N && nx>=0 && ny<N && ny>=0 && apt[nx][ny]==-1) { 
				//범위 안에 있고 단지 번호 붙일 수 있다면
				dfs(nx, ny, num);
			}
		}
	}
}
