package week31;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11559_PuyoPuyo {
	
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		for(int i=0; i<12; i++) {
			map[i] = br.readLine().toCharArray();
		}//입력 완료
		
		int answer=0;
		while(true) { //매 사이클마다
			
			//1. 터뜨릴 거 터뜨림
			visited = new boolean[12][6];
			int cnt=0; //한 사이클 당 연쇄가 일어나는 횟수
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(!visited[i][j] && map[i][j]!='.') {
						count=0; //1이 아니라 0..
						dfs1(i, j, map[i][j]);
						if(count>=4) { //터뜨려서 빈칸으로 만듦
							dfs2(i, j, map[i][j]);
							cnt++;
						}
					}
				}
			}
			if(cnt==0) //더 이상 연쇄가 일어나지 않으면
				break;
			else
				answer++;
			
			//2. 밑으로 옮김
			for(int y=0; y<6; y++) {
				int x = 11;
				while(x>=0) {
					int tmp1 = 0, tmp2 = 0;
					if(map[x][y] == '.') {
						tmp1 = x;
						tmp2 = x;
						while(tmp2 >= 0) { //밑에서부터 빈칸이 아닌 가장 가까운 것 찾기
							if(map[tmp2][y]!='.') {
								break;
							}
							tmp2--;
						}
						//바꿔치기
						if(tmp2>=0) {
							map[tmp1][y] = map[tmp2][y];
							map[tmp2][y] = '.';							
						}
						x = tmp1-1;
					} else {
						x--;						
					}
				}
			}
//			for(int i=0; i<12; i++) {
//				for(int j=0; j<6; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		System.out.println(answer);
	}
	private static void dfs2(int x, int y, char c) {
		 map[x][y] = '.';
		 for(int i=0; i<4; i++) {
			 int nx = x + dx[i];
			 int ny = y + dy[i];
			 if(nx<0 || nx>=12 || ny<0 || ny>=6)
				 continue;
			 if(map[nx][ny] == c)
				 dfs2(nx, ny, c);
		 }
	}
	private static void dfs1(int x, int y, char c) {
		visited[x][y] = true;
		count++;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=12 || ny<0 || ny>=6)
				 continue;
			if(!visited[nx][ny] && map[nx][ny]==c) {
				dfs1(nx, ny, c);
			}
		}
	}

}
