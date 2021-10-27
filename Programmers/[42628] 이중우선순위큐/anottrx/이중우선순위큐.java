import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 큰수->작은수
        PriorityQueue<Integer> pqReverse = new PriorityQueue<>(); // 작은수->큰수

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("D 1") && !pq.isEmpty()) { // 최댓값을 삭제
                int max = pq.poll();
                pqReverse.remove(max);
            } else if (operations[i].equals("D -1") && !pq.isEmpty()) { // 최솟값을 삭제
                int min = pqReverse.poll();
                pq.remove(min);
            } else if (operations[i].charAt(0) == 'I') { // 숫자를 삽입
                String[] splited = operations[i].split(" ");
                pq.offer(Integer.parseInt(splited[1]));
                pqReverse.offer(Integer.parseInt(splited[1]));
            }
        }

        int size = pq.size();
        if (size == 0) { // 큐가 비어있으면 [0,0]
            answer = new int[] { 0, 0 };
            return answer;
        }

        answer = new int[2]; // 비어있지 않으면 [최댓값, 최솟값]
        answer[0] = pq.poll();
        answer[1] = pqReverse.poll();
        return answer;
    }
}
