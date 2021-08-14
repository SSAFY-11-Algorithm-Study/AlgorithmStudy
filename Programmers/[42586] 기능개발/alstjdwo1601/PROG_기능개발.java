package week3;

import java.util.*;

public class PROG_기능개발 {
	class Solution {
		public int[] solution(int[] progresses, int[] speeds) {

			int size = progresses.length;

			//배포까지 남은 날짜를 담는 배열
			int [] restDate = new int[size];
			for(int i = 0 ; i < size; i++){
				if((100 - progresses[i]) % speeds[i] == 0)
					restDate[i] = (100 - progresses[i]) / speeds[i];
				else restDate[i] = (100 - progresses[i]) / speeds[i] +1;
			}

			ArrayList<Integer> list = new ArrayList<>();

			int temp = restDate[0];
			int cnt =1;
			for(int i = 1 ; i < restDate.length ; i++){
				//뒤에 작업이 기다려야되면 배포 같이해야되니까 cnt++
				if(restDate[i] <= temp) 
					cnt++;
				//기다리지 않아도 된다면
				else {
					//그때까지의 카운트 담고 , temp와 cnt를 초기화
					list.add(cnt);
					temp = restDate[i];
					cnt=1;
				}
			}
			//마지막에 하나 남는거 추가해주고 종료해야함
			list.add(cnt);

			//리스트에 담긴걸 정답배열에 복사후 출력
			int[] answer = new int [list.size()]; 
			for(int i = 0 ; i < list.size(); i++)
				answer[i] = list.get(i);
			return answer;
		}
	}

}
