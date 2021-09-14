package week8;

import java.util.*;
class Solution2 {
    static int R;
    static String [] selected;
    public String solution(String number, int k) {
        String answer = "";
        
        int size = number.length();
        int ptr = 0;
    
        for(int i=0; i < size - k; i++){ //최대 길이는 size - k
            char max = '0';
            
            for(int j = ptr; j<= k+i ; j++){ 
                // 9가 있는 경우엔 뒤에 더 볼것없이 선택하면됨
                if(number.charAt(j)== '9'){
                    max = number.charAt(j);
                    ptr = j+1;
                    break;
                }
                if(max < number.charAt(j)){
                    max = number.charAt(j);
                    
                    ptr = j+1; // 최대값 위치 그 다음부터 다시 시작
                }
            }
            answer += max; //최대값 뽑은거 붙임
        }
        return answer;
    }
}



//조합으로 뽑으려했으나 자릿수가 백만개라 재귀가 너무 깊어서 런타임 에러

/*
import java.util.*;
class Solution {
    static int R,max;
    static char [] selected;
    public String solution(String number, int k) {
        String answer = "";
        
        int size = number.length();
        selected = new char[size - k]; //조합으로 size-k개 만큼만 뽑을거임
        R= size - k;
        
        comb(0,0,number);
        answer = Integer.toString(max);
        return answer;
    }
    
    public static void comb(int depth, int start, String number){
        if(depth == R){
            System.out.println(Arrays.toString(selected));
            String str = "";
            for(int i = 0 ; i < selected.length; i++){
                str += selected[i];
            }
            
            int num = Integer.parseInt(str);
            max = Math.max(max, num);
        }
        
        for(int i = start; i<number.length() ; i++){
            selected[depth] = number.charAt(i);
            comb(depth+1, i+1, number);
        }
    }
}
*/