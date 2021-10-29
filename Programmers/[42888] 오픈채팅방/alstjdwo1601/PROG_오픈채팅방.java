package week14;

import java.util.*;
class Solution2 {
    public String[] solution(String[] record) {
        
        
        ArrayList<String> answerList = new ArrayList<>();
        HashMap<String, String> userMap = new HashMap<>(); //id , nickname 들어감
        
        String [] userInfo = new String[3]; 
        for(int i = 0 ; i < record.length ; i++){
            userInfo = record[i].split(" "); //명령어 , id, nickname
            
            if(userInfo[0].equals("Enter")){
                userMap.put(userInfo[1], userInfo[2]); //id , nickname
                String str = userInfo[1] + "님이 들어왔습니다.";
                answerList.add(str);
                
            }
            else if(userInfo[0].equals("Leave")){
                String str = userInfo[1] + "님이 나갔습니다.";
                answerList.add(str);
            }
            else if(userInfo[0].equals("Change")){
                userMap.put(userInfo[1], userInfo[2]);
            }
        }
        //정답배열로 옮기기
        String[] answer = new String[answerList.size()];
        for(int i = 0 ; i < answer.length ; i++)
            answer[i] = answerList.get(i);
        
        
        for(int i= 0 ; i < answerList.size() ; i++){
            String str = answerList.get(i);
            int idIdx = str.indexOf("님"); //id 인덱스
            String userId = str.substring(0, idIdx);
            
            answer[i] = str.replace(userId, userMap.get(userId)); //id 를 닉네임으로 변경
            
        }
        
        
        return answer;
    }
}
