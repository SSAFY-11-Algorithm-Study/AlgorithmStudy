import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> a = new ArrayList<>(); // 답 정리 위함
        
        Queue<Integer> work = new LinkedList<>();
        int s = 0; // speeds[]의 인덱스
        int w = 0; // 큐에 넣을 기능 정리 값
        for (int p:progresses) {
            w = (100-p)/speeds[s]; // (100-기능)/speed 
            if (((100-p)%speeds[s]) != 0) { 
                w++; // 나눴을 때 나머지가 0이 아니라면 1 더하기
            } 
            work.offer(w);
            s++; 
        }
        
        while (!work.isEmpty()) {
            int t = work.poll(); // 맨 위에 있는 기능 빼기
            int cnt = 1; // answer에 넣을 기능 개수. 우선 1로 초기화.
            while (true) {
                if (!work.isEmpty() && work.peek()<=t) { // 그 다음 기능이 앞서 뺀 기능보다 덜 걸릴 경우
                    work.poll(); // 위에 있는 기능 빼고
                    cnt++; // 횟수 더하기
                } else  { // 그렇지 않은 경우 while문 끝내기
                    break;
                }
            }
            a.add(cnt);
        }
        
        answer = new int[a.size()]; // 답 정리
        for (int i=0; i<a.size(); i++) {
            answer[i] = a.get(i);
        }
        return answer;
    }
}
