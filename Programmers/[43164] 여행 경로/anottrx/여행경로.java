// 1번과 2번 테스트케이스 실패

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        HashMap<String, PriorityQueue<String>> hm = new HashMap<>(); // [출발지, 출발지에서 갈 수 있는 곳 리스트]
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            PriorityQueue<String> cur = new PriorityQueue<>(); // 가능한 경로가 2개 이상일 경우 알파벳 순서대로 정렬
            if (hm.containsKey(start)) {
                cur = hm.get(start);
                cur.add(end);

            } else {
                cur.add(end);
            }
            hm.put(start, cur);
        }

        ArrayList<String> answerList = new ArrayList<>();
        String from = "ICN"; // 항상 "ICN" 공항에서 출발
        answerList.add(from);
        while (!hm.isEmpty()) {
            PriorityQueue<String> cur = hm.get(from);
            String next = cur.poll(); // 갈 경로가 이미 정렬된 상태이기 때문에 그냥 빼기
            
            if (cur.size() == 0) { // 더 이상 경로가 존재하지 않는다면 해시맵에서 제거
                hm.remove(from);
            } else { // 경로가 존재할 경우 다시 넣기
                hm.put(from, cur); 
            }
            from = next;
            answerList.add(from);
        }

        answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}
