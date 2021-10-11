import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        Set<Integer>[] set = new Set[9];
        int t = N;
        
        for(int i = 1; i < 9; i++) {
            set[i] = new HashSet<>();
            set[i].add(t);
            t = t * 10 + N;
        }
        
        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < i; j++) {
                for(int n1 : set[j]) {
                    for(int n2 : set[i - j]) {
                        set[i].add(n1 + n2);
                        set[i].add(n1 - n2);
                        set[i].add(n2 - n1);
                        set[i].add(n1 * n2);
                        if(n2 != 0) {
                            set[i].add(n1 / n2);
                        }
                        if(n1 != 0) {
                            set[i].add(n2 / n1);
                        }
                    }
                }
            }
        }
        
        for(int i = 1; i < 9; i++) {
            if(set[i].contains(number)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}
