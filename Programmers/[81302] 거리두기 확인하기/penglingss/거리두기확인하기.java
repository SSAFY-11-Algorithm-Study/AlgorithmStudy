class Solution {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, -1}; // 상 우상 우 우하 하 좌하 좌 좌상 상
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i = 0; i < 5; i++) {
            answer[i] = 1; // 1로 초기화
        }
        
        for(int placeIdx = 0; placeIdx < 5; placeIdx++) { // 5개의 대기실
            one : for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(places[placeIdx][i].charAt(j) == 'P') { // P가 나오면
                        if(chkDist1(places[placeIdx], i, j)) { // 거리 1 확인
                            answer[placeIdx] = 0;
                            break one; // 한 명이라도 지키지 않고 있으면 break
                        }
                        if(chkDist2(places[placeIdx], i, j)) { // 거리 2 확인
                            answer[placeIdx] = 0;
                            break one;
                        }
                        if(chkDiagonal(places[placeIdx], i, j)) { // 대각선 확인
                            answer[placeIdx] = 0;
                            break one;
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    public boolean chkDist1(String[] place, int i, int j) {
        for(int d = 0; d < 7; d += 2) {
            int nextR = i + dr[d];
            int nextC = j + dc[d];
            
            if(idxChk(nextR, nextC)) {
                if(place[nextR].charAt(nextC) == 'P') // 거리 1에 P라면 무조건 거리두기 지키지 않은것
                    return true;
            }
        }
        return false;
    }
    
    
    public boolean chkDist2(String[] place, int i, int j) {
        for(int d = 0; d < 7; d += 2) {
            int nextR = i + dr[d] * 2;
            int nextC = j + dc[d] * 2;
            
            if(idxChk(nextR, nextC)) {
                if(place[nextR].charAt(nextC) == 'P') { // 거리 2에 P라면
                    int tmpR = i + dr[d];
                    int tmpC = j + dc[d];
                    if(place[tmpR].charAt(tmpC) != 'X') // 사이에 파티션이 없다면 거리두기 지키지 않은것
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean chkDiagonal(String[] place, int i, int j) {
        for(int d = 1; d <= 7; d += 2) {
            int nextR = i + dr[d];
            int nextC = j + dc[d];
            
            if(idxChk(nextR, nextC)) {
                if(place[nextR].charAt(nextC) == 'P') { // 대각선에 P라면
                    int near1R = i + dr[d - 1]; // 근처 index 확인
                    int near1C = j + dc[d - 1];
                    int near2R = i + dr[d + 1];
                    int near2C = j + dc[d + 1];
                    if(place[near1R].charAt(near1C) != 'X' || place[near2R].charAt(near2C) != 'X') // 두 곳 모두에 파티션이 있지 않다면 거리두기 지키지 않은것
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean idxChk(int r, int c) { // index 범위 확인
        if(r < 0 || r >= 5 || c < 0 || c >= 5) return false;
        return true;
    }
}
