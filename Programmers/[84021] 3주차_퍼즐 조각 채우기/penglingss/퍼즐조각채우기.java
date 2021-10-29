// 6 ~ 10, 13 테케 실패

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
        init(game_board, table);
        
        for(int r = 0; r < 5; r++) {
            for(int rT = 0; rT < 5; rT++) {
                for(int i = 0; i < L; i++) {
                    for(int j = 0; j < L; j++) {
                        if(newBoard[i][j] > 0) {
                            int[] index = findFirstIndex(newBoard[i][j]);
                            if(index[0] == -1) continue;
                            else fill(i, j, index);
                        }
                    }
                }
                rotateTable();
                // rotateBoard();
            }
            rotateBoard();
            // rotateTable();
        }
        
        return answer;
    }
    
    public void init(int[][] game_board, int[][] table) {
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
    
    public void rotateTable() {
        int[][] copy = new int[L][L];

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                copy[i][j] = newTable[L - 1 - j][i];
            }
        }

        for (int i = 0; i < L; i++) {
            newTable[i] = copy[i].clone();
        }
    }
    
    public int[] findFirstIndex(int num) {
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                if(newTable[i][j] == num) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }
    
    public void fill(int r, int c, int[] index) {
        int num = newBoard[r][c];
        boolean[][] visitB = new boolean[L][L];
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> tmpQ = new LinkedList<>();
        q.add(new int[]{r, c, index[0], index[1]});
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
        
        if(num != tmpQ.size()) return;
        
        answer += num;
        while(!tmpQ.isEmpty()) {
            int[] cur = tmpQ.poll();
            newBoard[cur[0]][cur[1]] = -1;
            newTable[cur[2]][cur[3]] = -1;
        }
    }
}
