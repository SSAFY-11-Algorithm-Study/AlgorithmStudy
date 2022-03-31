class Solution {
    static long[] memo;
    
    public long solution(int n) {
        long answer = 0;
        memo = new long[n + 3];
        answer = dp(n + 1);
       return answer % 1234567;
    }
    
    public static long dp(int n) {
        memo[n] = memo[n] % 1234567;
        if (n <= 1) {
            return n;
        } else if (memo[n] > 0) {
            return memo[n];
        } else {
            return memo[n] = dp(n - 1) + dp(n - 2);
        }
    }
}
