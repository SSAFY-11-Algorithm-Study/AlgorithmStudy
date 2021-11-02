//처음에 시간초과 남 -> for문을 최대한 줄이는 방식으로 다시 리팩토링 하였음.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16234_MovingPopulation {

	static int N, L, R;
	static int[][] land, clustering;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int totalCnt, pplSum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 가로 세로 길이
		L = Integer.parseInt(st.nextToken()); //최소
		R = Integer.parseInt(st.nextToken()); //최대
		land = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		int answer = 0;
		ArrayList<int[]> list = new ArrayList<>();
		while(true) { //하루 동안에 일어나는 인구 이동
			
			//1. 연합이 되는 나라끼리 군집화 -> DFS
			int cnt=1;
			//초기화
			visited = new boolean[N][N];
			clustering = new int[N][N];
			list.clear();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						totalCnt=0; pplSum=0;
						dfs(i, j, cnt);
						list.add(new int[] {pplSum, totalCnt});
						cnt++;
					}
				}
			}
			cnt--;
			
			if(cnt==N*N) //모든 그룹이 단위그룹(1*1)일 경우 : 더 이상 인구이동이 일어나지 않음
				break;
			
			//2. 인구 수정
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					int num = clustering[j][k]; //속한 그룹 숫자
					int dividedPpl = list.get(num-1)[0] / list.get(num-1)[1];
						land[j][k] = dividedPpl;
				}
			}
			answer++;
		}//while
		System.out.println(answer);
	}

	private static void dfs(int x, int y, int cnt) { 
		//pplSum : cnt clustering에 속하는 인구의 총 합
		//totalCnt : cnt clustering에 속하는 나라의 갯수
		
		visited[x][y] = true;
		clustering[x][y] = cnt;
		totalCnt++;
		pplSum += land[x][y];
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny])
				continue;
			
			int diff = Math.abs(land[x][y] - land[nx][ny]);
			if(diff<L || diff>R)
				continue;
			
			//국경선을 허물 수 있다면
			dfs(nx, ny, cnt);
		}
	}
}
