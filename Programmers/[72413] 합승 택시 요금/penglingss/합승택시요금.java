import java.util.*;

class Solution {
    int[][] map;
    int N;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        N = n;
        
        initMap(fares);
        
        int[] distFromS = dijkstra(s);
        int[] distFromA = dijkstra(a);
        int[] distFromB = dijkstra(b);
        
        for(int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, distFromS[i] + distFromA[i] + distFromB[i]);
        }
        
        return answer;
    }
    
    public void initMap(int[][] fares) {
        map = new int[N + 1][N + 1];
        for(int[] fare : fares) {
            // if(map[fare[0]][fare[1]] != 0 && map[fare[0]][fare[1]] < fare[2]) continue;
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }
    }
    
    public int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);
        pq.add(new int[]{start, 0});
        int[] result = new int[N + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[start] = 0;
        
        while(!pq.isEmpty()) { 
            int[] cur = pq.poll();
            
            if(cur[1] > result[cur[0]]) continue;
            
            for(int i = 1; i < N + 1; i++) {
                int cost = map[cur[0]][i] + result[cur[0]];
                if(map[cur[0]][i] > 0 && result[i] > cost) {
                    result[i] = cost;
                    pq.offer(new int[]{i, cost});
                }
            }
        }
        
        return result;
    }
}
