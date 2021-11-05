package week15;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ16234_인구이동 {
	static int N,L,R, countCnt;
	static int answer = 0;
	static int [][] map,clone;
	static boolean [][] visited;
	static int [] dx= {1,0 ,-1,0};
	static int [] dy= {0,1 ,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		map = new int[N][N];
		clone = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		while(true) {
			//1. 연합가능한 나라 dfs 로 찾기.
			visited = new boolean[N][N];
			for(boolean[] b: visited)
				Arrays.fill(b, false);
			
			for(int[] c: clone)
				Arrays.fill(c, -1);
				
			
			int num = 0;  // 각각 연합의 갯수
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j++) {
					countCnt = 1; // 한번에 연결되는 나라의 갯수
					if(!visited[i][j]) {
						dfs(i,j,num);
						
						
						//연합할곳없으면 뒤에 인구이동을 할필요가없다
						if(countCnt==1) {
							num++;
							continue;
						}
						
						//2. 인구 이동
						int sum = 0;
						for(int k = 0 ; k < N ; k++) {
							for(int l = 0  ; l<N ;l++) {
								if(clone[k][l] ==num)
									sum+= map[k][l];
							}
						}
						
						int temp = sum / countCnt;
						
						for(int k = 0 ; k < N ; k++) {
							for(int l = 0  ; l<N ;l++) {
								if(clone[k][l] ==num)
									map[k][l] = temp;
							}
						}
						
						num++;
						
						
						/*
						for(int k = 0 ; k < N ; k++) {
							System.out.println();
							for(int l = 0  ; l<N ;l++) {
								System.out.print(map[k][l] + " ");
							}
						}
						System.out.println();
						*/
						
					}
				}
			}
			//System.out.println("num : "+num);
			//System.out.println("----------------");
			if(num == N*N) { //연합국 갯수가 n*n이면 연합된 나라가 없단뜻
				break;
			}
			 
			answer++;
		}
		
		System.out.println(answer);
	}
	private static void dfs(int x, int y, int cnt) {
		visited[x][y] = true;
		clone[x][y] = cnt; //클론 배열에 같이 연합되는곳엔 같은 숫자를 넣어둠

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {

				int diff = Math.abs(map[x][y] - map[nx][ny]);
				if(diff>=L && diff <=R) {

					//System.out.println("diff : " + diff);
					countCnt++;
					dfs(nx, ny, cnt);
				}
			}
		}
	}
}
