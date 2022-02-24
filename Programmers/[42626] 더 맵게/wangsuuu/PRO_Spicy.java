import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++){ //옮겨 담음
            pq.add(scoville[i]);
        }
        
        int cnt=0;
        int answer = 0;
        int s1, s2, newS;
        
        while(cnt<scoville.length-1){
            s1 = pq.poll();
            s2 = pq.poll();
            if(s1>= K)
                break;
            
            newS = s1 + s2*2;
            pq.add(newS);
            answer++;
            cnt++;
        }
        
        if(cnt==scoville.length-1 && pq.poll()<K){ //총 하나가 될 때까지 섞었는데도 스코빌을 못 넘으면
            return -1;
        }
        
        return answer;
    }
}
