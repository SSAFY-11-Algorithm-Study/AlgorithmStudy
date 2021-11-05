import java.util.*;

class Solution {
    static int[] result;
    static boolean[] isSelected;
    static int answer = -1;
    static int size=0;
    static int p;
  
    public int solution(int k, int[][] dungeons) {
        p = k;
        size = dungeons.length;
        result = new int[size];
        isSelected = new boolean[size];

        perm(0, dungeons);
        return answer;
    }
    
    public void perm(int cnt, int[][] dungeons){
        if(cnt==size){
            int sum=0;
            int tmp=p;
            for(int i=0; i<size; i++){
                //맵 더이상 진행 불가하다면 끝냄
                if(tmp < dungeons[result[i]][0]){
                    break;
                }
                //더 진행 가능하다면
                sum++;
                tmp -= dungeons[result[i]][1];
            }
            answer = Math.max(answer, sum);
            return;
        }
        
        for(int i=0; i<size; i++){
            if(isSelected[i])
                continue;
            isSelected[i] = true;
            result[cnt] = i;
            perm(cnt+1, dungeons);
            isSelected[i] = false;
        }
    }
}
