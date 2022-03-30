import java.util.*;

/* 변경 사항
1. 시간 계산을 좀 더 단순하게 함
2. # 붙어있는 부분은 그냥 하나의 알파벳으로 치환해서 처리
정확도 100%
*/

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
        // # 전처리
        m = change(m);
        for(int i=0; i<musicinfos.length; i++){
            String curMusic = musicinfos[i];
            String[] splited = curMusic.split(",");
            String start = splited[0];
            String end = splited[1];
            String musicTitle = splited[2];
            String scale = change(splited[3]); // # 전처리
                        
            // 재생시간 구하기
            int s_hr = Integer.parseInt(start.substring(0, 2));
            int s_m = Integer.parseInt(start.substring(3));
            
            int e_hr = Integer.parseInt(end.substring(0, 2));
            int e_m = Integer.parseInt(end.substring(3));
            
            int total_start = s_hr * 60 + s_m;
            int total_end = e_hr * 60 + e_m;
            
            int res = total_end - total_start;
            
            //재생 시간에 따라 악보 정보 재편성
            String str = "";
            
            for(int j=0; j<res; j++){
                int idx = j % scale.length();
                str += Character.toString(scale.charAt(idx));
            }
            //m이 포함되었는지 확인
            if(str.contains(m))
                list.add(new Result(i, res, musicTitle));
            
        }
        if(list.size()==0){
            return "(None)";
        }
        
        Collections.sort(list);
        
        return list.get(0).title;
    }

    public String change(String str){
        str = str.replace("C#", "H");
        str = str.replace("D#", "I");
        str = str.replace("F#", "J");
        str = str.replace("G#", "K");
        str = str.replace("A#", "L");
        
        return str;
    }
}
