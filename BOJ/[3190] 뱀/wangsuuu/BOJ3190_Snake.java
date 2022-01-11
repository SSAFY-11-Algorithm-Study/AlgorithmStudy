import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190_Snake {
	
	static class Info{
		int time;
		String dir;
		public Info(int time, String dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}
	static int N, K, L;
	static int[][] board; // 0 : 빈칸, 1 : 사과, 2 : 뱀
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static ArrayList<Info> info = new ArrayList<>(); //뱀의 방향 변환 정보를 담은 리스트

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); //보드 크기
		K = Integer.parseInt(br.readLine()); //사과의 갯수
		board = new int[N][N];
		
		for(int i=0; i<K; i++) { //사과 위치 정보 저장
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		L = Integer.parseInt(br.readLine()); //방향 변환 횟수
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			info.add(new Info(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		System.out.println(solve());
	}

	private static int solve() {
		
		int time=0;
		Queue<int[]> snake = new LinkedList<>(); //뱀 위치 저장
		int x=0, y=0, nx=0, ny=0;
		int list_idx=0;
		int cur_dir = 0; //동서남북 ==0123
		
		board[x][y] = 2;
		snake.add(new int[] {x, y});
		
		while(true) {
			time++; //일단 시간이 증가가 된 뒤에 움직여 보고 종료 조건이면 그 시간에 끝난 것이므로, 시간을 먼저 증가시켜 줌
			nx = x + dx[cur_dir];
			ny = y + dy[cur_dir];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N || board[nx][ny]==2) { //종료조건이면
				return time;
			}
			
			if(board[nx][ny]==0) { //빈칸이라면
				int[] snake_start = snake.poll();
				board[snake_start[0]][snake_start[1]] = 0;
			}
			board[nx][ny]=2;
			snake.add(new int[] {nx, ny});
			
			if(list_idx<L && time==info.get(list_idx).time) { //방향 전환
				String dir = info.get(list_idx).dir;
				if(dir.equals("L")) {
					cur_dir = changeDir_L(cur_dir);
				} else {
					cur_dir = changeDir_D(cur_dir);
				}
				list_idx++;
			}
			x = nx; y = ny; //빼먹지 말기
		}
	}


	private static int changeDir_L(int cur_dir) {
		if(cur_dir==0) return 3;
		else if(cur_dir==1) return 2;
		else if(cur_dir==2) return 0;
		else return 1;
		
	}

	private static int changeDir_D(int cur_dir) {
		if(cur_dir==0) return 2;
		else if(cur_dir==1) return 3;
		else if(cur_dir==2) return 1;
		else return 0;
	}
}
