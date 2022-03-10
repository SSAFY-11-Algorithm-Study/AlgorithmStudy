class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long max = times[0]; // 최댓값 구하기 (long)
        for (int i = 1; i < times.length; i++) {
            max = Math.max(max, times[i]);
        }
        
        long start = 0;
        long end = max * n; // 제일 큰 값은 (가장 오래 걸리는 시간 * 사람 수)
        long mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum = sum + mid / times[i];
            }
            if (sum < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
