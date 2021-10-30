import java.util.*;

class Solution {
    static int L, answer;
    static int[][] newBoard;
    static int[][] newTable;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(int[][] game_board, int[][] table) {
        answer = 0;
        L = game_board.length;
        newBoard = new int[L][L];
        newTable = new int[L][L];
        init(game_board, table); // 새로운 newBoard와 newTable을 만든다.
        
        for(int r = 0; r < 4; r++) { // newBoard를 90도씩 돌리며 4번 확인한다.
            for(int i = 0; i < L; i++) {
                for(int j = 0; j < L; j++) {
                    int n = newBoard[i][j]; // newBoard의 칸마다 보면서
                    if(n > 0) { // 0보다 크다면
                        for(int a = 0; a < L; a++) {
                            for(int b = 0; b < L; b++) {
                                if(n == newTable[a][b]) { // newTable에서 같은 수를 가진 칸을 찾아 채울수있는지 확인.
                                    fill(i, j, a, b);
                                }
                            }
                        }
                    }
                }
            }
            rotateBoard();
        }
        
        return answer;
    }
    
    public void init(int[][] game_board, int[][] table) { // 도형이 들어갈 칸은 그 크기의 수로 초기화한다.
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                if(game_board[i][j] == 0) {
                    setNum(game_board, 0, i, j);
                } else newBoard[i][j] = -1;
                
                if(table[i][j] == 1) {
                    setNum(table, 1, i, j);
                } else newTable[i][j] = -1;
            }
        }
    }
    
    public void setNum(int[][] map, int boardOrTable, int r, int c) {
        boolean[][] visit = new boolean[L][L];
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> tmpQ = new LinkedList<>();
        q.add(new int[]{r, c});
        visit[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            tmpQ.add(cur);
            
            for(int i = 0; i < 4; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                
                if(nextR < 0 || nextC < 0 || nextR >= L || nextC >= L || visit[nextR][nextC]) continue;
                if(boardOrTable == 0 && map[nextR][nextC] != 0) continue;
                else if(boardOrTable == 1 && map[nextR][nextC] != 1) continue;
                
                q.add(new int[]{nextR, nextC});
                visit[nextR][nextC] = true;
            }
        }
        
        int num = tmpQ.size();
        while(!tmpQ.isEmpty()) {
            int[] cur = tmpQ.poll();
            if(boardOrTable == 0) newBoard[cur[0]][cur[1]] = num;
            else if(boardOrTable == 1) newTable[cur[0]][cur[1]] = num;
        }
    }

    public void rotateBoard() {
        int[][] copy = new int[L][L];

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                copy[i][j] = newBoard[L - 1 - j][i];
            }
        }

        for (int i = 0; i < L; i++) {
            newBoard[i] = copy[i].clone();
        }
    }
    
    public void fill(int r, int c, int a, int b) { // bfs로 채워 넣을수 있는지 확인.
        int num = newBoard[r][c];
        boolean[][] visitB = new boolean[L][L];
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> tmpQ = new LinkedList<>();
        q.add(new int[]{r, c, a, b});
        visitB[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            tmpQ.add(cur);
            
            for(int i = 0; i < 4; i++) {
                int nextRB = cur[0] + dr[i];
                int nextCB = cur[1] + dc[i];
                int nextRT = cur[2] + dr[i];
                int nextCT = cur[3] + dc[i];

                if(nextRB < 0 || nextCB < 0 || nextRB >= L || nextCB >= L || visitB[nextRB][nextCB]) continue;
                if(nextRT < 0 || nextCT < 0 || nextRT >= L || nextCT >= L) continue;
                if(newBoard[nextRB][nextCB] != num) continue;
                if(newTable[nextRT][nextCT] != num) continue;
                
                q.add(new int[]{nextRB, nextCB, nextRT, nextCT});
                visitB[nextRB][nextCB] = true;
            }
        }
        
        if(num != tmpQ.size()) return; // num과 tmpQ의 크기가 같아야 채워 넣을수 있다.
        
        answer += num;
        while(!tmpQ.isEmpty()) { // 채워 넣을수 있다면 newBoard와 newTable의 숫자를 -1로 바꾼다.
            int[] cur = tmpQ.poll();
            newBoard[cur[0]][cur[1]] = -1;
            newTable[cur[2]][cur[3]] = -1;
        }
    }
}
