import java.util.*;

class Solution {
    int N;
    char[] numArr;
    boolean[] visit;
    HashSet<Integer> set;
    
    public int solution(String numbers) {
        N = numbers.length();
        numArr = numbers.toCharArray();
        visit = new boolean[N];
        set = new HashSet<>();
        
        makePrime(0, "");
        
        return set.size();
    }

    public void makePrime(int cnt, String num) {
        if(cnt == N) {
            if(isPrime(Integer.parseInt(num)))
                set.add(Integer.parseInt(num));
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(visit[i]) continue;
            if(num.length() > 0 && isPrime(Integer.parseInt(num)))
                set.add(Integer.parseInt(num));
            visit[i] = true;
            makePrime(cnt + 1, num + numArr[i]);
            visit[i] = false;      
        }
    }
    
    public boolean isPrime(int num) {     
        if(num == 0 || num == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0) return false;
        
        return true;
    }
}
