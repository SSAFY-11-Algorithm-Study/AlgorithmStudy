import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node>{
        int v, c; //vertex, cost
        public Node(int v, int c){
            this.v = v;
            this.c = c;
            }
        public int compareTo(Node n){ //cost가 작은 순서대로 정렬
            return this.c - n.c;
        }
    }   
    
    static int[] dist;
    static ArrayList<Node>[] graph;
    public int solution(int N, int[][] road, int K) {
        
        //그래프 생성
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<road.length; i++){
            int s = road[i][0];
            int e = road[i][1];
            int c = road[i][2];
            graph[s].add(new Node(e, c));
            graph[e].add(new Node(s, c)); //양방향 그래프
        }
        
        dijkstra(N);
        
        int answer=0;
        for(int i=1; i<=N; i++){
            if(dist[i] <= K)
                answer++;
        }

        return answer;
    }
    
    public void dijkstra(int N){
        final int INF = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N+1]; //넘버링은 1번부터
        dist = new int[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        Arrays.fill(dist, INF);
        dist[1]=0;
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()){
            //1. 방문하지 않은 노드들 중에서 최소비용 노드를 꺼냄
            Node cur = pq.poll();
            if(visited[cur.v])
                continue;
            
            visited[cur.v]=true;
            //2. 꺼낸 노드를 경유지로 삼을 시 방문하지 않은 노드 중 업데이트 하고 enqueue
            for(Node node : graph[cur.v]){
                if(!visited[node.v] && dist[node.v] > cur.c + node.c){
                    dist[node.v] = cur.c + node.c;
                    pq.add(new Node(node.v, dist[node.v]));
                }
            }
        }
    }
}
