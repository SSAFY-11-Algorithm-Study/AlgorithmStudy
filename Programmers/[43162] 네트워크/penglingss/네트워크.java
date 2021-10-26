class Solution {
    public int solution(int n, int[][] computers) {
        
        boolean[] chk = new boolean[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            if(!chk[i]) {
                dfs(computers, chk, i);
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int[][] computers, boolean[] chk, int idx) {
        
        for(int i = 0; i < computers.length; i++) {
            if(!chk[i] && computers[idx][i] == 1) {
                chk[i] = true;
                dfs(computers, chk, i);
            }
        }
    }
}
