class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        recur(numbers, 0, 0, target);
        return answer;
    }
    
    public void recur(int[] numbers, int idx, int cur, int target) {
        if(idx == numbers.length) {
            if(cur == target) {
                answer++;
            }
            return;
        }
        
        recur(numbers, idx + 1, cur + numbers[idx], target);
        recur(numbers, idx + 1, cur - numbers[idx], target);
    }
}
