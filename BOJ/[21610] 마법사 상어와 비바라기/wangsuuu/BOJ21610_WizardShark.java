import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//맵 형태를 정확히 이해 못해서 대각선 이동 시 이상하게 이동시켰음ㅠ

public class BOJ21610_WizardShark {
	
	static int N, M;
	static int[][] map;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	//대각선만
	static int[] n_dx = {-1, -1, 1, 1};
	static int[] n_dy = {-1, 1, 1, -1};
	static Queue<int[]> clouds = new LinkedList<>();
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N*N 맵 크기
		M = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clouds.add(new int[] {N-1, 0});
		clouds.add(new int[] {N-1, 1});
		clouds.add(new int[] {N-2, 0});
		clouds.add(new int[] {N-2, 1});
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			
			//1. 구름 이동 & 해당 구름에서 비가 내림
			int size = clouds.size();
			for(int j=0; j<size; j++) {
				int[] c = clouds.poll();
				int x = c[0]; int y = c[1];
				int nx = Math.abs(N + dx[d] * (s%N) + x) % N; //s가 아니라 s % N
				int ny = Math.abs(N + dy[d] * (s%N) + y) % N;
				clouds.add(new int[] {nx, ny});
				visited[nx][ny] = true;
				map[nx][ny]++;
			}
			//2. 구름이 모두 사라짐 && 물복사 버그 마법 시전
			for(int j=0; j<size; j++) {
				int[] c = clouds.poll();
				int x = c[0]; int y = c[1];
				
				for(int k=0; k<4; k++) { //대각선 이동
					int nx = x + n_dx[k];
					int ny = y + n_dy[k];
					if(nx<0 || nx >=N || ny<0 || ny>=N)
						continue;
					if(map[nx][ny] > 0)
						map[x][y]++;
				}
			}
			
			//3. 바구니 물의 양이 2이상인 모든 칸에 구름 생기고, 물 줄어듦
			for(int a=0; a<N; a++) {
				for(int b=0; b<N; b++) {
					if(map[a][b] >= 2 && !visited[a][b]) {
						clouds.add(new int[] {a, b});
						map[a][b] -= 2;
					}
				}
			}
		}
		
		int answer = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] !=0)
					answer+=map[i][j];
			}
		}
		System.out.println(answer);
	}
}
