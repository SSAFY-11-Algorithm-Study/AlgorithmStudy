class Solution {
    int[][] map;
    int[] dist;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        dist = new int[N + 1];
        map = new int[N + 1][N + 1];
        
        addEdge(road);
        setDist(N);
        dijkstra(N);
        
        
        for(int i = 1; i < N + 1; i++) {
            if(dist[i] <= K) answer++;
        }

        return answer;
    }
    
    public void addEdge(int[][] road) {
        for(int[] r : road) {
            if(map[r[0]][r[1]] > 0) {
                if(map[r[0]][r[1]] < r[2]) continue;
            }
            map[r[0]][r[1]] = r[2];
            map[r[1]][r[0]] = r[2];
        }
    }
    
    public void setDist(int N) {
        for(int i = 0; i < N + 1; i++) {
            if(map[1][i] == 0) {
                dist[i] = Integer.MAX_VALUE;
            } else {
                dist[i] = map[1][i];
            }
        }
        dist[1] = 0;
    }
    
    public void dijkstra(int N) {
        boolean[] chk = new boolean[N + 1];
        chk[1] = true;
        for(int i = 0; i < N - 1; i++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            
            for(int j = 1; j < N + 1; j++) {
                if(!chk[j] && min > dist[j]) {
                    idx = j;
                    min = dist[j];
                }
            }
            
            chk[idx] = true;
            
            for(int j = 1; j < N + 1; j++) {
                if(!chk[j] && map[idx][j] != 0 && dist[j] > dist[idx] + map[idx][j]) {
                    dist[j] = dist[idx] + map[idx][j];
                }
            }
        }
    }
}
