class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n]; // 네트워크 연결되었는지 확인용
        for (int i=0; i<n; i++) { // 모든 컴퓨터 확인
            if(visited[i]) { // 만약 해당 컴퓨터가 네트워크 연결되었다면 아래 무시
                continue;
            }
            visited[i] = true; // 그외의 경우는 네트워크 연결시키고, answer 1 추가
            answer++;
            
            findNetwork(n, i, computers, visited); // dfs
        }
        
        return answer;
    }
    
    private static void findNetwork(int n, int from, int[][] computers, boolean[] visited) {
        visited[from] = true; // 네트워크 연결되었다고 체크
        for (int i=0; i<n; i++) { // 모든 컴퓨터 확인
            if(computers[i][from]==1 && !visited[i] && i!=from) { // 컴퓨터끼리 연결되었고, 해당 컴퓨터가 네트워크 연결 안되었고, 두 컴퓨터 번호가 다른 경우
                findNetwork(n, i, computers, visited);
            } 
        }
    }
}
