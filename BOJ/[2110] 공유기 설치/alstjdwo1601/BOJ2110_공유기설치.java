package week2;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2110_공유기설치 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		int [] houses = new int [N];
		for(int i = 0 ; i < N ; i++) {
			houses[i] = sc.nextInt();
		}
		
		Arrays.sort(houses);
		
		int answer = 0;
		
		int start = 1;
		int end = houses[N-1] - houses[0]; //houses[0] 을 빼줘야함
		
		while(start<=end) {
			int mid = (start+end)/2; //이 값이 집사이 길이를 의미
			int installed = houses[0]; // 첫집에 일단 설치하고 시작
 			int cnt = 1; // 공유기 갯수 카운팅
 			
 			for(int i = 1; i < N ; i++) {
 				if(houses[i]-installed >=mid) { //2번째 집부터 간격을 mid랑 비교해봄
 					//간격이 mid보다 같거나 크면 공유기 설치
 					cnt++;
 					//설치된 위치 초기화
 					installed = houses[i];
 				}
 			}
 			
 			//공유기갯수 동일함
 			//if(cnt == C) {
 			//	answer = mid;
 			//	break;
 			//}
 			
 			
 			if(cnt>= C) {
 				//공유기가  C 개 이상이면 일단 answer에 mid 저장하고
 				//더큰 범위 있는지 체크해본다.
 				answer = mid;
 				start = mid +1; 
 			}
 			else
 				end = mid-1; // 간격 줄여야됨
		}
		
		System.out.println(answer);
	}

}
