import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<int[]> q = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++){
            q.add(new int[] {i, priorities[i]});
        }
        
        while(!q.isEmpty()){
            
            boolean flag = false;
            int[] cur = q.poll();
            int l = cur[0]; int p = cur[1];
            
            for(int[] e : q){ //linkedlist는 forEach로 순회가 가능하다!
                if(e[1]>p){
                    q.add(new int[] {l, p});
                    flag = true;
                    break;
                }
            }
            
            if(!flag){
                answer++;
                if(l==location)
                    break;   
            }
        }      
        return answer;
    }
}
