//엉뚱한 답이 나옴ㅠ

import java.util.*;

public class PRO_Advertise {

	public static void main(String[] args) {
		String play_time = "99:59:59"; 
		String adv_time = "25:00:00";
		String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		int answer = solution(play_time, adv_time, logs);
		System.out.println(answer);
	}

	private static int solution(String play_time, String adv_time, String[] logs) {
		int max = Integer.MIN_VALUE;
		int answer=0;
		//매개변수들을 초로 바꾸기
		String[] tmp1 = play_time.split(":");
		int play_second = 3600 * Integer.parseInt(tmp1[0]) + 60 * Integer.parseInt(tmp1[1]) + Integer.parseInt(tmp1[2]);
		
		String[] tmp2 = adv_time.split(":");
		int adv_second = 3600 * Integer.parseInt(tmp2[0]) + 60 * Integer.parseInt(tmp2[1]) + Integer.parseInt(tmp2[2]);
		
		int[][] log_second = new int[logs.length][2];
		
		for(int i=0; i<logs.length; i++) {
			String tmp3 = logs[i].substring(0, 8);
			String tmp4 = logs[i].substring(9);
			
			String tmp5[] = tmp3.split(":");
			String tmp6[] = tmp4.split(":");
			log_second[i][0] = 3600 * Integer.parseInt(tmp5[0]) + 60 * Integer.parseInt(tmp5[1]) + Integer.parseInt(tmp5[2]);
			log_second[i][1] = 3600 * Integer.parseInt(tmp6[0]) + 60 * Integer.parseInt(tmp6[1]) + Integer.parseInt(tmp6[2]);
		}
		
		for(int i=0; i<logs.length; i++){ //모든 구간의 시작지점부터 각각 시작해 봄
			
			int ad_start = log_second[i][0];
			int ad_end = ad_start + adv_second;
			if(ad_end > play_second) //만약 광고 시간이 영상 길이를 넘어간다면
				continue;
			ArrayList<Integer> list = new ArrayList<>();
			//누적합 계산
			for(int j=0; j<logs.length; j++) {
				for(int k=0; k<2; k++) {
					if(ad_start <= log_second[j][k] && log_second[j][k]<=ad_end) { //각 구간의 시작, 끝점이 광고 구간에 속해있다면
						list.add(log_second[j][k]);
					}
				}
			}
			if(!list.contains(ad_end))
				list.add(ad_end);
			
			Collections.sort(list);
			
			//구간합 구하기
			int sum=0;
			for(int j=0; j<list.size()-1; j++) {
				//구간의 시간 구하기
				int start = list.get(j);
				int end = list.get(j+1);
				int time = end - start; //구간의 끝 - 구간의 처음
				
				//해당 time에 몇 명이 시청 중인지 구하기
				int cnt=0;
				for(int k=0; k<logs.length; k++) { // '막대 시작 <= 구간의 처음과 끝 <= 막대 끝' 이면 시청중임.
					if(log_second[k][0]<=start && log_second[k][1] >= end) {
						cnt++;
					}
				}
				sum += time * cnt;
			}
			
			if(sum > max) {
				sum = max;
				answer = ad_start;
			}
		}
		
		
		return answer;
	}

}
