import java.util.*;

//전역변수를 solution 함수 안에서 초기화 해야 통과됨..^^...

class Solution {
    
    static int M, N;
    static boolean[][] visited;
    static int tmp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        M = m; N = n;
        visited = new boolean[m][n];
        int numberOfArea = 0; 
        int maxSizeOfOneArea = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(picture[i][j]!=0 && !visited[i][j]){
                    tmp=0;
                    dfs(picture[i][j], i, j, picture); //해당 번호의 영역을 모두 구함
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, tmp);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public static void dfs(int num, int x, int y, int[][] picture){
        visited[x][y] = true;
        tmp++;
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || nx>=M || ny<0 || ny>=N) continue;
            if(picture[nx][ny] == num && !visited[nx][ny]){
                dfs(num, nx, ny, picture);
            }
        }
    }
}
