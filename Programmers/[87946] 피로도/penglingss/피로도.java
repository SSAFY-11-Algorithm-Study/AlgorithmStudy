class Solution {
    int answer, K, size;
    int[][] DGarray;
    boolean[] visit;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        K = k;
        size = dungeons.length;
        DGarray = new int[size][2];
        visit = new boolean[size];
        
        explore(0, dungeons);
        return answer;
    }
    
    public void explore(int cnt, int[][] dungeons) {
        if(cnt == size) {
            answer = Math.max(answer, count());
            return;
        }
        
        for(int i = 0; i < size; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            DGarray[cnt] = dungeons[i];
            explore(cnt + 1, dungeons);
            visit[i] = false;
        }
    }
    
    public int count() {
        int res = 0;
        int pVal = K;
        for(int[] dg : DGarray) {
            if(pVal >= dg[0] && pVal >= dg[1]) {
                pVal -= dg[1];
                res++;
            } else break;
        }
        
        return res;
    }
}
