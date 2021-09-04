
/* 아직 못 풀었습니다..
 * 
 * 생각 한 로직
 * 1. 먹을 수 있는 물고기 찾기(현재 아기상어의 크기와 비교를 통해)
 * 2. 여러개면 가장 가까운거( 거리가 같으면 가장 위, 왼쪽 순으로)
 * 3. 가서 먹고 반복 먹을 수 있는 물고기 없을 때 까지 반복
 * 
 * --> 문제점 가야하는 길에 자기보다 큰 물고기가 있으면 돌아가야 하므로 그 물고기가 가장 가까운게 아님
 * --> 해결하기 위해 BFS..?
 */

package time6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ16236_babyshark {
	
	static int N;
	static int[][] map;
	static Fish babyshark;
	static ArrayList<Fish> fish = new ArrayList<>();
	
	static class Fish{
		
		int x,y;
		int size;
		
		Fish(int x,int y,int size){
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		 
		
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					babyshark = new Fish(i, j, 2);
				} else if(map[i][j] >= 1 && map[i][j] <= 6){
					fish.add(new Fish(i,j,map[i][j]));
				}
			}
		}
		
		
	}
	
	static void caneat() {
		for(Fish Food : fish) {
			int mindistance = Integer.MAX_VALUE;
			if(Food.size < babyshark.size) {
				int distance = Math.abs(Food.x-babyshark.x)+Math.abs(Food.y-babyshark.y);
				
			}
		}
	}
	
	static void caldistance() {
		
	}

}
