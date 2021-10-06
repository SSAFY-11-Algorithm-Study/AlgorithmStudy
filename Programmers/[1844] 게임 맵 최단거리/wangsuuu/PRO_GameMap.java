import java.util.*;
//BFS!
class Solution {
    
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[n][m];
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1));
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            
            Point p = q.poll();
            int x = p.x; int y = p.y;
            int dist = p.dist;
            
            if(x==n-1 && y==m-1){
                return dist;
            }
            
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && 
                   !visited[nx][ny] && maps[nx][ny]==1){
                    q.add(new Point(nx, ny, dist+1));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }
    
    public class Point{
        int x, y, dist;
        Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
}
