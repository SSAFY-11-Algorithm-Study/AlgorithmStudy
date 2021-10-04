import java.util.*;

class Solution {
    class Node {
        int x;
        int y;
        int cnt;
        
        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        int r = maps.length;
        int c = maps[0].length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean[][] visit = new boolean[r][c];
        int answer = 0;
        
        q.add(new Node(0, 0, 1));
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            if(node.x == r - 1 && node.y == c - 1) {
                return node.cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];
                if(nextX >= 0 && nextX < r && nextY >= 0 && nextY < c && maps[nextX][nextY] == 1 && !visit[nextX][nextY]) {
                    visit[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY, node.cnt + 1));
                }
            }
        }
        
        return -1;
    }
}
