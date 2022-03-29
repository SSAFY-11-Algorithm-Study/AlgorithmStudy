//66점짜리.. 다시 풀어서 업로드 예정

import java.util.*;

class Solution {
    class Result implements Comparable<Result>{
        int idx;
        int playTime;
        String title;
        public Result(int idx, int playTime, String title){
            this.idx = idx;
            this.playTime = playTime;
            this.title = title;
        }
        public int compareTo(Result r){ //1. 재생시간 내림차순 2. index 오름차순
            if(this.playTime == r.playTime)
                return this.idx - r.idx;
            return r.playTime - this.playTime;
        }
    }
    public String solution(String m, String[] musicinfos) {
        ArrayList<Result> list = new ArrayList<>();
        
        for(int i=0; i<musicinfos.length; i++){
            String curMusic = musicinfos[i];
            String[] splited = curMusic.split(",");
            String start = splited[0];
            String end = splited[1];
            String musicTitle = splited[2];
            String scale = splited[3];
            
            // 재생시간 구하기
            int s_hr = Integer.parseInt(start.substring(0, 2));
            int s_m = Integer.parseInt(start.substring(3));
            
            int e_hr = Integer.parseInt(end.substring(0, 2));
            int e_m = Integer.parseInt(end.substring(3));
            
            int result_hr = 0, result_m = 0;
            if(e_m - s_m < 0){
                result_m = e_m + 60 - s_m;
                result_hr = e_hr - 1 - s_hr;
            } else{
                result_m = e_m - s_m;
                result_hr = e_hr - s_hr;
            }
            int res = result_hr * 60 + result_m;
            
            //재생 시간에 따라 악보 정보 재편성
            String str = "";
            
            for(int j=0; j<res; j++){
                int idx = j % scale.length();
                str += Character.toString(scale.charAt(idx));
                if(idx+1 < scale.length() && scale.charAt(idx+1) == '#'){ //#은 알파벳과 한 덩어리이므로 관련 처리
                    str += "#";
                    j++; res++;
                }
            }
            
            //m이 포함되었는지 확인 (# 주의)
            int index = 0;
            int cnt=0;
            int res_idx = 0;
            for(int j=0; j<str.length(); j++){
                if(index<m.length() && m.charAt(index) == str.charAt(j)){
                    cnt++; index++;
                } else{
                    cnt = 0; index=0;
                }
                if(cnt==m.length()){
                    res_idx = j; break;
                }
            }
            
            if(cnt==m.length() && res_idx+1<str.length() && str.charAt(res_idx+1) != '#')
                list.add(new Result(i, res, musicTitle));
        }
        
        
        if(list.size()==0){
            return "(None)";
        }
        
        Collections.sort(list);
        
        return list.get(0).title;
    }
}
