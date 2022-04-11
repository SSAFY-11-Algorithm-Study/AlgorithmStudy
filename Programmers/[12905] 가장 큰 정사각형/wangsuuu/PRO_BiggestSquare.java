class Solution
{
    public int solution(int [][]board)
    {   
        int[] dx = {0, -1, -1};
        int[] dy = {-1, 0, -1};
        // 각 위치에서 가능한 정사각형의 최대 한 변의 길이 저장
        int[][] dp = new int[board.length + 1][board[0].length + 1];
        
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                dp[i][j] = board[i-1][j-1];
            }
        }
        
        int answer = 0; //-1로 하면, 입력값이 모두 0일 경우에 답이 0이 아닌 1이 나옴. 0으로 초기화!
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[i].length; j++){
                if(dp[i][j] == 0) //0이면 정사각형 아예 못 만드니까 dp도 0
                    continue;
                int min = Integer.MAX_VALUE;
                for(int k=0; k<3; k++){
                    min = Math.min(min, dp[i + dx[k]][j+dy[k]]);
                }
                dp[i][j] = min + 1;
                answer = Math.max(answer, dp[i][j]);
            }
        }
        return answer*answer;
    }
}
