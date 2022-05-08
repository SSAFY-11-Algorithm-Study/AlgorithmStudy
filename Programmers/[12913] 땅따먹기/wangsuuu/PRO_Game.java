//전형적인 dp문제
class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int[][] dp = new int[land.length][4]; //각 칸 밟았을 때 최고점 저장
        
        for(int i=0; i<4; i++){
            dp[0][i] = land[0][i]; //초기값 세팅
        }
        
        for(int i=1; i<land.length; i++){
            for(int j=0; j<4; j++){
                int max = 0;
                if(j==0){
                    max = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][3]);
                } else if(j==1){
                    max = Math.max(Math.max(dp[i-1][0], dp[i-1][2]), dp[i-1][3]);
                } else if(j==2){
                    max = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][3]);
                } else {
                    max = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
                }
                dp[i][j] = land[i][j] + max;
            }
        }
        
        for(int i=0; i<4; i++){
            if(answer < dp[land.length-1][i])
                answer = dp[land.length-1][i];
        }
        
        return answer;
    }
}
