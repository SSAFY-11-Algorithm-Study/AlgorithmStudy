import java.util.*;

//bfs를 노드 수만큼 돌렸음 -> 8,9번 시간초과 나서 블로그 참고
//BFS는 가장 처음 방문한 게 최단경로임이 보장되기 때문에, bfs를 노드 수만큼 돌릴 필요가 없음.

class Solution {
    
    static ArrayList<Integer>[] graph;
    static int max = Integer.MIN_VALUE;
    static int[] dist;
    
    public int solution(int n, int[][] edge) {
        graph = new ArrayList[n+1];
        dist = new int[n+1];
        
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<edge.length; i++){
            int start = edge[i][0];
            int end = edge[i][1];
            graph[start].add(end);
            graph[end].add(start);
        }//그래프 생성 완료
        
        bfs(n);
        
        int answer=0;
        
        for(int i=1; i<=n; i++){
            if(dist[i]==max)
                answer++;
        }
        return answer;
    }
    
    public void bfs(int n){
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[] {1, 0}); //노드 번호, 거친 간선 갯수
        visited[1] = true;
        dist[1] = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int e_num = cur[1];
            
            for(int i : graph[node]){
                if(!visited[i]){
                    int n_num = e_num+1;
                    q.add(new int[] {i, n_num});
                    visited[i]=true;
                    dist[i] = n_num;
                    max = Math.max(max, n_num);
                }
            }
        }
    }
}
