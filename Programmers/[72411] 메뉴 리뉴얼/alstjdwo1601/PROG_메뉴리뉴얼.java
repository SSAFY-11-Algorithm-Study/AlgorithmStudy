package week7;

import java.util.*;


class Solution {
    
    static char[] arr ;
    static char[] selected ; 
    static Map<String, Integer> menuHM;
    public String[] solution(String[] orders, int[] course) {
        
        menuHM = new HashMap<>(); //뽑은 조합을 중복없이 담을 해시맵
                
        //course 안에 갯수만큼 조합을 뽑아야됨
        for (int i = 0; i < course.length; i++) { 
            for (int j = 0; j < orders.length; j++) {
                //정렬을 미리해둠
                arr= orders[j].toCharArray();
                Arrays.sort(arr); 
                
                //cousre[i] 개 만큼 조합뽑아서 selected에 저장
                selected = new char[course[i]];
                comb(0, 0, course[i]); 
            }
        }
        
        ArrayList<String> answerList = new ArrayList<>();
        
        //해시맵에 ([AB], 3) 이런식으로 담겨있으니 
        //value가 2보다 같거나크면서 course[i]와 같은 것중에 value가 제일 큰거 찾기
        for(int i = 0 ; i < course.length; i++){
            int max = 0;
            for(Map.Entry<String, Integer> e : menuHM.entrySet()){
                if(e.getValue() >=2 && e.getKey().length() == course[i]){
                    if(max < e.getValue()){
                        max = e.getValue();
                    }
                }
            }
            
            //최대값인 애들을 정답배열에 추가
            for(Map.Entry<String, Integer> e : menuHM.entrySet()){
                if(e.getValue() >=2 && e.getKey().length() == course[i]){
                    if(max == e.getValue() && e.getKey() != null){
                        answerList.add(e.getKey());
                    }
                }
            }
        }
        
        String[] answer = new String[answerList.size()];
        
        for(int i = 0 ; i < answerList.size(); i ++){
            answer[i] = answerList.get(i);
        }
                                    
        
        Arrays.sort(answer);
        return answer;
    }
    
    //메뉴 조합뽑기
    public static void comb(int depth , int start , int R){
        if(depth == R ){
            //System.out.println(Arrays.toString(selected));
            
            //[A,B]  -> AB라는 문자열로 해쉬에 담음
            String str = "";
            for(int i = 0 ; i < selected.length; i ++){
                str += selected[i];
            }
            //System.out.println(str);
            
            //str이 있으면 value +1 해주고 아니면 1로 넣어줌
            if(menuHM.containsKey(str))
					menuHM.replace(str, menuHM.get(str) +1);
			else 
				menuHM.put(str, 1);
            
            return;
        }
        
        for(int i = start ; i < arr.length ; i++){
            selected[depth] = arr[i];
            comb(depth+1, i+1, R);
        }
    }
}