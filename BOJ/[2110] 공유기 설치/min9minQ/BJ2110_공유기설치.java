// 이해가 안돼서 블로그 참조후 이해..

package time23;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2110_공유기설치 {

	private static int N, C;
	private static int[] house;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		C = sc.nextInt();
		house = new int[N];
		
		for(int i = 0; i < N; i++) {
			house[i] = sc.nextInt();
		}
		
		Arrays.sort(house);
		
		// 어떤 거리가 최적(가장 인접한 두 공유기 사이거리를 최대로)일지 이분탐색을 통해 찾기
		
		int start = 1; //집 간격 최소거리
		int end = house[N - 1] - house[0]; // 가장 끝집과 첫집의 거리 차이가 최대 거리
		
		int distance; // 집 사이 거리
		int answer = 0;
		
		while(start <= end) {
			
			int mid = (start + end) / 2; // 설치할 공유기 거리
			int before = house[0]; // 직전에 공유기가 설치된 집
			int cnt = 1; // 설치 공유기 수
			
			for(int i = 1; i < N; i++) {
				distance = house[i] - before;
				if(mid <= distance) { //집사이 거리가 공유기 한계 거리보다 크면 설치해야됨
					cnt++;
					before = house[i];
				}
			}
			
			if(cnt >= C) { // 간격을 넓혀서 공유기 수를 줄여야 됨 
				answer = mid;
				start = mid + 1;
			} else {// 간격 좁혀서 공유기가 더 설치해야함.
				end = mid - 1;
			}
		}
		
		System.out.println(answer);
	}

}
