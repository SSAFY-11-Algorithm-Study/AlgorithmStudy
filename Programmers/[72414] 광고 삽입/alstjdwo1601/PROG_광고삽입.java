package week21;

import java.util.*;


class Solution2 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        String [] total_length = play_time.split(":"); //전체플레이 타임의 시 분 초
        int total = 3600 * Integer.parseInt(total_length[0]) + 60 * Integer.parseInt(total_length[1]) + Integer.parseInt(total_length[2]);
        
        String [] adv_length = play_time.split(":"); // 광고플레이 타임의 시 분 초
        int adv = 3600 * Integer.parseInt(adv_length[0]) + 60 * Integer.parseInt(adv_length[1]) + Integer.parseInt(adv_length[2]);
        
        String [][] logs_start = new String[logs.length][3];
        String [][] logs_end = new String[logs.length][3];
        for(int i = 0 ; i < logs.length ; i ++){
            String [] temp = logs[i].split("-");
            
            logs_start[i] = temp[0].split(":");
            logs_end[i] = temp[1].split(":");
            //System.out.println(Arrays.toString(logs_start[i]));
            //System.out.println(Arrays.toString(logs_end[i]));
            
            //전부 초단위로 변환
            int startTime = 3600 * Integer.parseInt(logs_start[i][0]) + 60 * Integer.parseInt(logs_start[i][1]) + Integer.parseInt(logs_start[i][2]);
            int endTime = 3600 * Integer.parseInt(logs_end[i][0]) + 60 * Integer.parseInt(logs_end[i][1]) + Integer.parseInt(logs_end[i][2]);
            System.out.println(startTime + " " + endTime);
        }
        
        //string 을 초로 변환은 했는데 그 이후로 답봐도 뭔소린지 잘 모르겟음..
        
        return answer;
    }
}
