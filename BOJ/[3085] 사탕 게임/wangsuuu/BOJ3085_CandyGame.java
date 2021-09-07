import java.io.BufferedReader;
import java.io.InputStreamReader;

// 선택한 행/열과 같은 행/열에 있는 사탕만 먹음에 주의!

public class BOJ3085_CandyGame {
	
	static int N;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int result = Integer.MIN_VALUE;
		
		N = Integer.parseInt(br.readLine()); //보드의 크기
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		} //입력 완료
		
		for(int x=0; x<N; x++) { //각 원소 하나씩 체크
			for(int y=0; y<N; y++) {
				
				for(int k=0; k<4; k++) { //4방탐색
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(nx>=0 && nx<N && ny>=0 && ny<N) {//범위 안이고
						if(map[x][y] == map[nx][ny]) //바꿀 수 없다면
							continue;
						//바꿀 수 있다면 바꿈
						swap(x, y, nx, ny);
						
						int max = chooseMax(map); //바꾼 상태에서의 사탕 최대 갯수 구함
						result = Math.max(max, result);
						
						//다시 원상태로 되돌려 놓기
						swap(x, y, nx, ny);
					}//if
				}//for
			}//for x
		}//for y
		
		System.out.println(result);
	}

	private static void swap(int x, int y, int nx, int ny) {
		char temp;
		temp = map[x][y];
		map[x][y] = map[nx][ny];
		map[nx][ny] = temp;
	}


	private static int chooseMax(char[][] map) {
		
		int max=-1;
		
		//열 별로 탐색
		for(int col=0; col<N; col++) {
			int colCnt=1;
			for(int row=0; row<N-1; row++) {
				if(map[row][col]==map[row+1][col]) {
					colCnt++;
					max = Math.max(max, colCnt); //언제 연속 부분이 끊길지 모르므로, 할 때마다 max 체크
				} else { //더 이상 연속하지 않으면 cnt 초기화
					colCnt=1;
				}
			}
		}
		
		//행 탐색
		for(int row=0; row<N; row++) {
			int rowCnt=1;
			for(int col=0; col<N-1; col++) {
				if(map[row][col]==map[row][col+1]) {
					rowCnt++;
					max = Math.max(max, rowCnt);
				} else {
					rowCnt=1;
				}
			}
		}
		return max;
	}
}
