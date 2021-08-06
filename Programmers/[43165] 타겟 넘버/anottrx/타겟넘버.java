// 출처: 프로그래머스 타겟 넘버 https://programmers.co.kr/learn/courses/30/lessons/43165

class Solution {
    static boolean[] isSelected; // true라면 +, false라면 -
    static int totalCnt = 0; // 답
    public int solution(int[] numbers, int target) {
        isSelected = new boolean[numbers.length];
        
        add(0, numbers, target);
        return totalCnt; // 답 출력
    }
    
    private static void add(int cnt, int[] numbers, int target) {
        if(cnt==numbers.length) {
            int sum = 0;
            for(int i=0; i<numbers.length; i++) {
                int n = numbers[i];
                if(isSelected[i]) {
                    sum = sum + n;
                } else {
                    sum = sum - n;
                }
            }
            if(sum==target) { // 숫자를 다 더했을 때 타겟넘버와 같은 경우
                totalCnt++;
            }
            return;
        }
        
        isSelected[cnt] = true; // +
        add(cnt+1, numbers, target);
        
        isSelected[cnt] = false; // -
        add(cnt+1, numbers, target);
    }
}
