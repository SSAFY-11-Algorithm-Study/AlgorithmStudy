package week14;

import java.util.*;
class Solution4 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        //나오는 우선순위 반대인거 두개 
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0 ; i < operations.length ; i++) {
            String [] token = operations[i].split(" ");

            //빈 큐에 데이터를 삭제 요청 경우 연산 무시
            if (minPQ.size() < 1 && token[0].equals("D"))
                continue;

            //삽입 시 둘다 넣어줌
            if (token[0].equals("I")) {
                minPQ.offer(Integer.parseInt(token[1]));
                maxPQ.offer(Integer.parseInt(token[1]));
            }

            
            else if(token[0].equals("D")) {
                if(token[1].charAt(0) == '-'){
                    int min = minPQ.poll();
                    maxPQ.remove(min);
                }
                else{
                    //최대 힙에서 poll후 최소힙에서 해당 원소 삭제
                    int max = maxPQ.poll();
                    minPQ.remove(max);
                }
            }

            
        }
        if(minPQ.size()!= 0) {
            answer[0] = maxPQ.poll();
            answer[1] = minPQ.poll();
        }
        else{
            answer[0] = 0;
            answer[1] = 0;
        }
        return answer;
    }
}