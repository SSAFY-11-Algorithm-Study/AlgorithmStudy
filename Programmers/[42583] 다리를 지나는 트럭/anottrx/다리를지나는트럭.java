import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> tWeight = new LinkedList<>(); // 다리 위의 트럭의 무게
        Queue<Integer> tTime = new LinkedList<>(); // 다리 위 트럭의 남은 시간

        int tCnt = 0; // truck_weights의 인덱스
        while (true) {
            if (!tTime.isEmpty() && tTime.peek() == 0) {
                // 맨 위 트럭이 이동을 끝내서 0이라면 뺀다
                tWeight.poll();
                tTime.poll();
            }
            if (tCnt == truck_weights.length && tWeight.isEmpty()) {
                // 더 이상 넣을 트럭이 없고, 다리 위의 트럭도 없다면 1초 더하고 끝낸다
                answer++;
                break;
            } else {
                int tLen = tWeight.size(); // 다리 위의 트럭 개수
                if (tTime.isEmpty()) { // 다리가 비었다면
                    tWeight.offer(truck_weights[tCnt]); // 다리위 무게에 트럭 하나 추가
                    tTime.offer(bridge_length - 1); // 해당 트럭의 남은 시간은 (bridge_length - 1)
                    tCnt++; // 트럭 하나 넣었으니 인덱스++
                } else {
                    int wOnB = 0;
                    for (int i = 0; i < tLen; i++) {
                        int b = tTime.poll(); // 맨 위의 트럭의 남은 시간 하나 빼서
                        tTime.offer(--b); // 시간 1초 줄이고 다시 맨 뒤에 넣기
                        int w = tWeight.poll(); // 동일하게 방금 트럭의 무게를 맨 뒤에 넣기
                        wOnB = wOnB + w; // 다리위 트럭 무게 계산하기
                        tWeight.offer(w);
                    }
                    if ((tCnt < truck_weights.length) && (wOnB + truck_weights[tCnt]) <= weight) {
                        // 아직 다리 지나지 못한 트럭이 존재하고, 다리 위에 해당 트럭이 올라와도 무게가 초과하지 않는다면 트럭 올리기
                        tWeight.offer(truck_weights[tCnt]); // 다리위 무게에 트럭 하나 추가
                        tTime.offer(bridge_length - 1); // 해당 트럭의 남은 시간은 (bridge_length - 1)
                        tCnt++; // 트럭 하나 넣었으니 인덱스++
                    }
                }
            }
            answer++;
        }
        
        return answer;
    }
}
