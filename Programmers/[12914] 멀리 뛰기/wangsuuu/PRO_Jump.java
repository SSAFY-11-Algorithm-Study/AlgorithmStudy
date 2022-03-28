class Solution {
    public long solution(int n) {
        long[] dp = new long[n+1]; //i번째 계단에 도달하는 모든 경우의 수 저장
        
        if(n==1) //이 조건 없으면 dp[2]에 값 들어가서 테케 1에 런타임 에러 남.
            return 1;
        
        dp[1] = 1;
        dp[2] = 2;
        
        for(int i=3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2]; //마지막에 2칸 뛰는 경우 + 마지막에 1칸 뛰는 경우
            dp[i] %= 1234567;
        }
        return dp[n] % 1234567;
    }
}
