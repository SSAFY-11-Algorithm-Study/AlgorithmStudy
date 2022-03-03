import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayList<int[]> prioritiesArr = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
            prioritiesArr.add(new int[] { priorities[i], i }); // [중요도, 원래 순서]
        }

        for (int i = 0; i < priorities.length; i++) {
            answer++; // 횟수 증가
            int max = prioritiesArr.get(0)[0]; // 맨 앞의 중요도가 제일 크다고 우선 가정
            int removePosition = prioritiesArr.get(0)[1]; // 맨 앞의 원래 순서
            int maxPosition = 0; // 현재 대기목록에서 가장 큰 중요도의 위치
            for (int j = 1; j < prioritiesArr.size(); j++) {
                if (max < prioritiesArr.get(j)[0]) { // 더 큰 중요도를 만났다면 값 바꾸기
                    max = prioritiesArr.get(j)[0];
                    removePosition = prioritiesArr.get(j)[1];
                    maxPosition = j;
                }
            }
            if (maxPosition != 0) { // 다 끝났는데 맨 앞보다 더 큰 중요도가 있다면
                for (int j = 0; j < maxPosition; j++) { // 가장 큰 중요도 앞의 값들을 맨 뒤로 옮기기
                    prioritiesArr.add(new int[] { prioritiesArr.get(j)[0], prioritiesArr.get(j)[1] });
                }
                for (int j = 0; j < maxPosition; j++) { // 가장 큰 중요도 앞의 값들을 모두 제거하기
                    prioritiesArr.remove(0); // 제거할 때 무조건 0번째만 계속 제거할 것 -> stack
                }
            }
            prioritiesArr.remove(0); // 가장 큰 중요도 제거 (원래 0번째가 제일 중요도가 컸어도 동일)
            if (removePosition == location) { // 가장 커서 제거한 중요도의 원래 순서가 구하고자하는 문서의 순서와 동일하면 끝내기
                break;
            }
        }
        return answer;
    }
}
