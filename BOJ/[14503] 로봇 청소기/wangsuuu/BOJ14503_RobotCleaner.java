import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//알파벳 순서대로 구현하면 c,d의 경우 b에서 무한루프 걸리므로, c,d가 a,b보다 먼저 위치해야 함.
public class BOJ14503_RobotCleaner {
	
	// 0:북 1:동 2:남 3:서   -> 왼쪽 회전 시 방향 계산 : (dir+3)%4
	static int N, M, robot[], answer;
	static int[][] map; // 벽:1, 청소 안함:0, 청소함:2
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		robot = new int[3]; //위치, 방향
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			robot[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0) - '0';
			}
		}//입력 완료
		
		clean(robot[0], robot[1], robot[2]);
		System.out.println(answer);
		
	}

	private static void clean(int x, int y, int dir) {
		
		while(true) { //1단계 : 현재 위치 청소
			map[x][y]=2;
			answer++;
			int cnt=0;
			int nx, ny=0;
			while(true) { //2단계
				
				if(cnt==4) { //c, d라면
					int opp = oppDir(dir);
					nx = x + dx[opp];
					ny = y + dy[opp];
					if(map[nx][ny]==1) { //뒤쪽이 벽이라면 (d)
						return;
					} else {
						x = nx; y = ny; //한 칸 후진
						cnt=0; //다시 탐색해야 하므로 리셋!!!
						continue;//2번으로 돌아감
					}
				}
				
				dir = (dir+3) % 4; // a, b에서는 어쨌든 방향을 회전
				nx = x + dx[dir];
				ny = y + dy[dir];
				
				if(map[nx][ny]==0) { //아직 청소하지 않은 공간이 존재한다면(a)
					x = nx; y=ny;
					break; //1단계 진행
				} else { // 청소할 공간이 없다면(b)
					cnt++;
				}
			}
		}
	}

	private static int oppDir(int dir) { //반대 방향 구하기
		if(dir==0) {
			return 2;
		} else if(dir==1) {
			return 3;
		} else if(dir==2) {
			return 0;
		} else {
			return 1;
		}
	}
}
