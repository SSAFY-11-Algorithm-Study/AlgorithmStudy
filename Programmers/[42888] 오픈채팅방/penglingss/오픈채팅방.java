import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String,String> id = new HashMap<String, String>();
        int count = 0;
        for(int i = 0; i < record.length; i++){
            String[] s = record[i].split(" ");
            if(s.length != 2){
                id.put(s[1], s[2]);
            }
            if(!s[0].equals("Change"))
                count++;
        }
        
        String[] answer = new String[count];
        int index = 0;
        for(int i = 0; i < record.length; i++){
            String[] s = record[i].split(" ");
            if(s[0].equals("Enter")){
                answer[index] = id.get(s[1]) + "님이 들어왔습니다.";
                index++;
            }
            else if(s[0].equals("Leave")){
                answer[index] = id.get(s[1]) + "님이 나갔습니다.";
                index++;
            }
        }
        
        return answer;
    }
}
