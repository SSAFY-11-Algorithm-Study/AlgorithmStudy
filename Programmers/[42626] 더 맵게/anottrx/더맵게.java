import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        int cnt = 0;
        while (true) {
            // 스코빌 지수 K 이상으로 못 만드는지 확인
            if (pq.size() <= 1) {
                answer = -1;
                break;
            }

            cnt++;

            // 첫번째와 두번째 빼서 음식 섞고 리스트에 넣기
            int first = pq.remove();
            int second = pq.remove();
            int mixed = first + second * 2;
            pq.add(mixed); // 자동으로 정렬

            // 모두 스코빌 지수 K 이상인지 확인하기
            boolean isFinished = true;
            Iterator iter = pq.iterator();
            while (iter.hasNext()) { // Iterator로 값 확인 가능
                if ((int) iter.next() < K) { // Iterator는 Object
                    isFinished = false;
                    break;
                }
            }
            if (isFinished) {
                answer = cnt;
                break;
            }
        }
        return answer;
    }
}
