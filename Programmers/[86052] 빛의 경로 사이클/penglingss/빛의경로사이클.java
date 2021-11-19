import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, -1, 0, 1};
    boolean[][][] visit;
    int R, C;
    
    public int[] solution(String[] grid) {
        ArrayList<Integer> list = new ArrayList<>();
        R = grid.length;
        C = grid[0].length();
        visit = new boolean[R][C][4];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visit[i][j][d])
                        list.add(getDistance(grid, i, j, d ));
                }
            }
        }
        
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public int getDistance(String[] grid, int r, int c, int d) {
        int count = 0;
 
        while (true) {
            if (visit[r][c][d]) break;
 
            count++;
            visit[r][c][d] = true;
            if (grid[r].charAt(c) == 'L') d = d == 0 ? 3 : d - 1;
            else if (grid[r].charAt(c) == 'R') d = d == 3 ? 0 : d + 1;
 
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
        }
        return count;
    }
}
