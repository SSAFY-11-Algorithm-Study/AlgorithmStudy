import java.util.*;

class Solution {
    
    static ArrayList<Integer> list = new ArrayList<>();
    
    public int[] solution(String[] operations) {
        
        for(String s : operations){ //총 연산의 수만큼 반복
            char c = s.charAt(0);
            String s2 = s.substring(2, s.length());//숫자만 추출
            int num = Integer.parseInt(s2);  //문자열 -> int
            
            if(c=='I'){ //삽입의 경우라면
                list.add(num);
            } else{
                if(!list.isEmpty()){
                    Collections.sort(list);
                    
                    if(num==1){ //최댓값 삭제
                        list.remove(list.size()-1);
                    } else{ //최솟값 삭제
                        list.remove(0);
                    }
                }
            }
        }
        int[] answer;
        if(list.isEmpty()){
            answer=new int[] {0,0};
        } else{
            Collections.sort(list); //마지막으로 정렬 해 줘야 함!!
            answer = new int[] {list.get(list.size()-1), list.get(0)};
        }
        return answer;
    }
}
