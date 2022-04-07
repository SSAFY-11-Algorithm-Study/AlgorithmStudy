import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684_Ladder {
	
	static int N, M, H;
	static int map[][], tmp[][]; //왼쪽 : 1, 아래 : 0, 오른쪽 : 2
	static int answer = -1;
	static boolean flag = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로선 갯수
		M = Integer.parseInt(st.nextToken()); //이미 그어져 있는 가로선 갯수
		H = Integer.parseInt(st.nextToken()); //가로선 갯수
		
		map = new int[H][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 2;
			map[a][b+1] = 1;
		} //입력 완료
		
		for(int i=0; i< 4; i++) {
			tmp = new int[H][N];
			clone(tmp);
			
			dfs(0, i); //0~3개의 가로선을 선택해서 그어 봄
			if(flag) break;
		}
		
		System.out.println(answer==-1 ? -1 : answer);
		
	}
	private static void clone(int[][] tmp) { //clone메서드는 얕은 복사라서 깊은 복사 작업 필요
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}
	private static void dfs(int cnt, int target) {
		
		if(flag) return;
		
		if(cnt == target) {
			if(iToi()) {
				answer = cnt;
				flag=true;
			}
			return;
		}
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N-1; j++) {
				if(tmp[i][j]==0 && tmp[i][j+1] == 0) { //가로선이 그어져 있지 않다면
					tmp[i][j] = 2;
					tmp[i][j+1] = 1;
					dfs(cnt+1, target); //++cnt가 아니라 cnt+1이어야 cnt는 변하지 않음
					tmp[i][j] = tmp[i][j+1] = 0;
				}
			}
			
		}
		
	}
	private static boolean iToi() { //i가 i로 도착하는지 알아보는 길찾기 메서드 -> 수정해야 함
		
		for(int i=0; i<N; i++) {
			int x = 0, y = i;
			
			for(int j=0; j<H; j++) { //한 칸 내려가는 것까지 1번으로 쳐야 함. 아니면 무한 루프 빠짐
				if(tmp[x][y] == 1) {
					y--;
				} else if(tmp[x][y] == 2) {
					y++;
				}
				x++; //한칸은 무조건 내려감
			}
			if(y!=i) {
				return false;
			}
			
		}
		return true;
	}

}
