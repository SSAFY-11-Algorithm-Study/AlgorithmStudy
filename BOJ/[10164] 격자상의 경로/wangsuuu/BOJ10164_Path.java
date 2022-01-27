import java.util.Scanner;

public class BOJ10164_Path {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		int k_x=0, k_y=0;
		
		int number = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(number == K) { //K의 위치 저장
					k_x = i; k_y = j;
				}
				map[i][j] = number++;
			}
		}
		
		if(K==0) {
			//초깃값 세팅
			for(int i=0; i<M; i++) {
				map[0][i] = 1;
			}
			for(int i=0; i<N; i++) {
				map[i][0] = 1;
			}
			
			for(int i=1; i<N; i++) {
				for(int j=1; j<M; j++) {
					map[i][j] = map[i-1][j] + map[i][j-1];
				}
			}
			
			System.out.println(map[N-1][M-1]);
			return;
		} else {
			//초깃값 세팅. 
			for(int i=0; i<=k_y; i++) {
				map[0][i] = 1;
			}
			for(int i=0; i<=k_x; i++) {
				map[i][0] = 1;
			}
			
			//K에 도착할 때까지의 모든 경로의 수 구함
			for(int i=1; i<=k_x; i++) {
				for(int j=1; j<=k_y; j++) {
					map[i][j] = map[i-1][j] + map[i][j-1];
				}
			}
			
			//map[k_x][k_y] 값을 기준으로 새롭게 사각형 만들어서 map[N-1][M-1]까지 동일한 방식으로 구함
			
			//초기값 세팅
			for(int i=k_y; i<M; i++) {
				map[k_x][i] = map[k_x][k_y];
			}
			for(int i=k_x; i<N; i++) {
				map[i][k_y] = map[k_x][k_y];
			}
			
			//도착지에 도착할 때까지의 모든 경로의 수 구함
			for(int i=k_x+1; i<N; i++) {
				for(int j=k_y+1; j<M; j++) {
					map[i][j] = map[i-1][j] + map[i][j-1];
				}
			}
			
			System.out.println(map[N-1][M-1]);
			return;
		}
	}
}
