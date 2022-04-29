import java.util.*;
class Solution {
    // string -> char : String.valueOf(char)
    static int answer;
    static boolean[] isSelected;
    static char[] result;
    static char[] input;
    //전체 경우의 수 - 조건에 하나라도 어긋나는 경우들
    public int solution(int n, String[] data) {
        answer = 8 * 7 * 6 * 5 * 4 * 3 * 2;
        isSelected = new boolean[8];
        result = new char[8];
        input = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        
        perm(0, n, data);
        
        return answer;
    }
    
    public void perm(int cnt, int n, String[] data){
        if(cnt == 8){
            
            for(int i=0; i<n; i++){//result가 조건에 모두 맞는지 확인
                String cur = data[i];
                char A = cur.charAt(0);
                char B = cur.charAt(2);
                char op = cur.charAt(3);
                int num = cur.charAt(4) - '0';
                int idxA = 0, idxB=0;
                for(int j=0; j<8; j++){
                    if(result[j] == A){
                        idxA = j;
                    } else if(result[j] == B){
                        idxB = j;
                    }
                }
                int dist = Math.abs(idxA - idxB) - 1; //-1 안해줘서 틀렸다! 간격은 "둘 사이에 있는 다른 프렌즈의 수"
                if(op == '>' && dist <= num){
                    answer--;
                    break;
                } else if(op == '=' && dist != num){
                    answer--;
                    break;
                } else if(op == '<' && dist >= num){
                    answer--;
                    break;
                }
            }
            return;
        }
        
        for(int i=0; i<8; i++){
            if(isSelected[i])
                continue;
            result[cnt] = input[i];
            isSelected[i] = true;
            perm(cnt+1, n, data);
            isSelected[i] = false;
        }
    }
}
