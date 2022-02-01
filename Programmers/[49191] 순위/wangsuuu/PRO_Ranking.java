//인터넷 참고..

class Solution {
    public int solution(int n, int[][] results) {
        
        //정확하게 순위를 매길 수 있는 경우 : 자기 자신을 제외한 모든 사람들과 경기하여 승/패 기록이 각각 있어야 함(총 n-1개)
        //graph[i][j] = true -> i가 j를 이김
        //graph[i][j] = false -> i가 j에게 패함 or 서로 승패를 가릴 수 없음(서로 연결 X)
        
        boolean[][] graph = new boolean[n+1][n+1];
        
        for(int i=0; i<results.length; i++){
            int x = results[i][0];
            int y = results[i][1];
            graph[x][y] = true;
        }
        
        //플로이드 와샬 : 승리를 결정지을 수 있는 애들은 다 결정짓기 위해 모든 정점을 거쳐서 탐색!
        //(a->b 이고 b->c이면 a->c임을 이용)
        for(int k=1; k<=n; k++){ //경유지
            for(int i=1; i<=n; i++){ //출발지
                if(k==i) continue;
                for(int j=1; j<=n; j++){ //도착지
                    if(k==j || i==j) continue;
                    if(graph[i][k] && graph[k][j])
                        graph[i][j] = true;
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++){
            int cnt=0;
             for(int j=1; j<=n; j++){
                 if(graph[i][j] || graph[j][i]) //i가 j에게 승리하거나 패한다면
                     cnt++;
             }
            if(cnt==n-1) answer++;
        }
        
        return answer;
    }
}
