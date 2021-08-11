import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int[] time = new int[truck_weights.length];
        int cur = 0;
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        
        while(true) {
            answer++;
            
            if(!q.isEmpty() && answer == time[q.peek()]) {
                weight += truck_weights[q.peek()];
                q.poll();
            }
            
            if(cur < truck_weights.length && weight >= truck_weights[cur]) {
                weight -= truck_weights[cur];
                time[cur] = bridge_length + answer;
                q.add(cur);
                cur++;
            }
            
            if(cur >= truck_weights.length && q.isEmpty()) {
                break;
            }
        }
        
        return answer;
    }
}
