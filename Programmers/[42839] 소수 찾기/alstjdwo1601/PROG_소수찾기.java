package week8;

import java.util.*;
class Solution {
    public static String [] token;
    public static String [] selected;
    public static boolean [] visited;
    
    static ArrayList<Integer> list = new ArrayList<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        token = numbers.split("");
        selected = new String[token.length];
        visited = new boolean[numbers.length()];
        
        //1개씩 , 2개씩 ,,,, 이렇게 모든 경우의 수만큼 순열 돌리면서 찾음
        for(int i = 0; i < numbers.length(); i++)
            permutation(0, i+1);
        
        //소수인것만 뽑아서 정답
        for(int i = 0 ; i < list.size() ; i ++){
            if(isPrime_num(list.get(i)))
                answer++;
        }
        
        return answer;
    }
    
    
    //순열함수
    public void permutation(int depth, int R) {
        if (depth == R) {
            //System.out.println(Arrays.toString(selected));
            String str = "";
            for(int i = 0; i < R; i++)
                str += selected[i];
            
            int num = Integer.parseInt(str);
            if(!list.contains(num))
                list.add(num);

        }

        for (int i =0 ; i < token.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = token[i];
                permutation(depth + 1, R);
                visited[i] = false;
            }
        }
    }

        
    //소수 판정
    public boolean isPrime_num (int num){
        int cnt = 0;
        boolean isPrime = true;
        
        //2보다 작으면 소수x
        if(num < 2)
            return false;
        
        for(int i= 2 ; i <= num/2 ; i++){
            if(num %i == 0 )
                cnt++; 
        }
        
        if(cnt == 0) isPrime =  true;
        else isPrime = false;
        
        return isPrime;
    }
}
