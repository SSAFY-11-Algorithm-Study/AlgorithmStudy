package week10;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ14502_연구소 {
	static int N,M;
	static int max = 0;
	static int [][] map;
	static boolean [][] visited;	
	static int [] dx = {-1,0,1,0};
	static int [] dy = {0,-1,0,1};
	static int [] WallArr;
	static boolean [] Wallvisited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int [N][M];
		visited = new boolean [N][M]; //확산할때 방문배열
		WallArr = new int[N*M];  //조합을 담기 위한 배열
		Wallvisited = new boolean[N*M];  //빈공간 아닌곳은 true체크
		
		//맵 입력받기
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = sc.nextInt();
				//빈공간 아니면 벽을 세울수 없으니 여긴 미리 true체크
				if(map[i][j] > 0) {
					Wallvisited[(i*M +j)] = true;
				}
			}
		}
		//System.out.println(Arrays.toString(Wallvisited));
		
		
		makeWall(0,0);
		System.out.println(max);
	}
	
	
	//배열에서 0인 지점중에 임의로 3개를 뽑고 1로 바꿈
	private static void makeWall(int depth, int start) {
		if(depth ==3) {
			int x=0;
			int y=0;
			
			int [][] temp = new int[3][2];
			int cnt = 0;
			//System.out.println(Arrays.toString(WallArr));
			//WallArr은 2차원배열을 1차원배열로 옮긴거니까 인덱스를 맞춰줘야함
			for(int i = 0 ; i < WallArr.length ; i++) {
				if(WallArr[i] > 0) {
					//x,y는 이차원배열 어디 좌표에 벽을 세울지 뽑은 조합 경우의수
					x = (WallArr[i]-1)/M;
					y = (WallArr[i]-1)%M;
					//System.out.printf("(x : %d, y : %d)",x,y);
					
					
					map[x][y] = 1; //벽세우기
					
					//벽 위치 저장해두기
					temp[cnt][0] = x;
					temp[cnt][1]  =y;
					cnt++;
				}
			}
			
			//바이러스 확산 , 방문배열 초기화
			for(boolean [] b : visited)
				Arrays.fill(b, false);
			
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(map[i][j] ==2)
						spread(i,j);
				}
			}
			
			/* 맵상태 출력해보기
			for(int i = 0 ; i < N ; i++) {
				System.out.println();
				for(int j = 0 ; j < M ; j++) {
					System.out.print(map[i][j] + " ");
				}
			}
			*/
			
			//안전영역 크기 재기
			int area=0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(map[i][j] ==0)
						area++;
				}
			}
			//System.out.println();
			//System.out.println(area);
			max = Math.max(max, area);
			
			//벽세운곳 다시 없애고 원위치
			for(int i = 0 ; i < 3; i ++) {
				map[temp[i][0]][temp[i][1]] = 0;
			}
			
			//확산되었던 바이러스 원위치
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(map[i][j] ==3)
						map[i][j] =0;
				}
			}
			return;
		}
		
		for(int i = start ; i < N*M ; i++) {
			//벽 못세우는 곳은 거르고
			if(Wallvisited[i]) continue;
			
			WallArr[depth] = i+1;
			makeWall(depth+1, i+1);
		}
	}
	
	
	private static void spread(int x , int y) {
		visited[x][y] = true;
		
		boolean flag = false;
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && ny>=0 && nx<N && ny< M) {
				if(!visited[nx][ny] && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					//원래 바이러스위치랑 확산된곳을 구별해야 나중에 확산된곳만 원위치 가능해서 3으로 표시
					map[nx][ny] = 3;
					flag = true;
					spread(nx,ny);
				}
			}
		}
		
		//더이상 확산이 안될때 리턴
		if(!flag) return;
	}
	

}
