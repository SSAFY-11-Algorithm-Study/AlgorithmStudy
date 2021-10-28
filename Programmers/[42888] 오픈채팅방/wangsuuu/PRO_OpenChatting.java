import java.util.*;
import java.io.*;

class Solution {
    
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        ArrayList<String> list= new ArrayList<>();
        StringTokenizer st;
        
        for(int i=0; i<record.length; i++){ //record를 쭉 돌아보면서 id에 따른 닉네임 변경사항을 최종적으로 map에 저장
            
            if(record[i].startsWith("E") || record[i].startsWith("C")){ //Enter 또는 Change의 경우
            st = new StringTokenizer(record[i]);
                String command = st.nextToken();
                String id = st.nextToken();
                String nickname = st.nextToken();
                map.put(id, nickname); //어차피 id에 대한 변경사항은 덮어씌워짐 
            }
        }
        
        for(int i=0; i<record.length; i++){ //다시 record를 traverse하며 최종 결정된 닉네임으로 출력
            String[] rec = record[i].split(" ");
            if(record[i].startsWith("E")){
                list.add(map.get(rec[1])+"님이 들어왔습니다.");
            } else if(record[i].startsWith("L")){
                list.add(map.get(rec[1]) + "님이 나갔습니다.");
            }
        }
        
        //리턴 타입에 맞게 옮겨담기
        String[] answer = new String[list.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
