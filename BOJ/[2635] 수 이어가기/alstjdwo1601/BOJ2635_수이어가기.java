package week5;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ2635_수이어가기 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int answer = 0;

		//첫번째 수
		int first = sc.nextInt();
		

		//몇개 나올지 몰라서 리스트에 담음
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> answerList = new ArrayList<>();
		
		for(int i = 1 ; i <= first; i++) {
			//first 를 뒤에서 최신화해야해서 temp에 담아둠
			int tempFirst = first;
			list.add(first);
			
			int second = i;
			list.add(second);
			
			while(true) {
				int nextNum = tempFirst - second;
				//다음 숫자가 0보다 같거나 크다면 리스트에 담음
				if(nextNum >= 0) {
					list.add(nextNum);
					
					//값 최신화
					tempFirst = second;
					second = nextNum;
				}
				
				else break;
			}
			
			//기존의 크기보다 큰 경우에 answer 과 answerList에 값을 담아둠
			if(list.size() > answer) {
				answer = list.size();
				answerList.clear();
				for(int j= 0 ; j < list.size() ; j++)
					answerList.add(list.get(j));
			}
			list.clear();
		}
		
		// 최종적인 값과 리스트 요소를 출력
		System.out.println(answer);
		for(int i = 0 ; i < answerList.size() ; i++)
			System.out.print(answerList.get(i) + " ");
		 
	}
}
