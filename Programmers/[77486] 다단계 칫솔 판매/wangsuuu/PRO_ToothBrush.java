import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<enroll.length; i++){
            map.put(enroll[i], i); //어차피 i++되니까 i++이 아닌 i를 넣어야 함.
        }
        
        for(int i=0; i<amount.length; i++){
            String b_name = seller[i];
            int benefit = amount[i] * 100;
            
            ArrayList<int[]> list = new ArrayList<>(); //인덱스, 이익 저장
            while(!b_name.equals("-")){
                
                int idx=map.get(b_name); //for문 돌면 시간초과 나서, HashMap으로 접근했음
                
                if((int)(benefit*0.1) < 1){ //자신이 이익 다 가짐
                    list.add(new int[] {idx, benefit});
                    break;
                }
                
                list.add(new int[] {idx, benefit - (int)(benefit*0.1)}); 
                //benefit * 0.9 하면 틀림. ex) 12의 0.9%는 10.8이라 10이 되는데, 답은 11이어야 함.
                
                b_name = referral[idx];
                benefit = (int)(benefit*0.1);
                
            }
            
            for(int[] l : list) {
                answer[l[0]] += l[1];
            }
        }
        
        return answer;
    }
}
