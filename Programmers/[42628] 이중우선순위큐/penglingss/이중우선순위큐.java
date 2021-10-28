import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String s : operations) {
            String[] op = s.split(" ");
            if(op[0].equals("I")) {
                pq.add(Integer.parseInt(op[1]));
            } else {
                if(pq.isEmpty()) {
                    continue;
                }
                if(op[1].equals("1")) {
                    PriorityQueue<Integer> tmp = new PriorityQueue<>();
                    for(int i = 0; i < pq.size() - 1; i++) {
                        tmp.add(pq.peek());
                        pq.remove();
                    }
                    pq = tmp;
                } else {
                    pq.remove();
                }
            }
        }
        
        if(pq.isEmpty()) {
            return answer;
        } else {
            int max = pq.peek();
            int min = pq.peek();
            for(int i : pq) {
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
            answer[0] = max;
            answer[1] = min;
            
        }
        
        return answer;
    }
}
