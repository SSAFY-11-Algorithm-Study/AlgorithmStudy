package week10;

import java.util.*;

import java.util.*;

class Solution2 {
    static int [] selected;
    static boolean [] checked;
    static ArrayList<Long> numList;
    static ArrayList<Character> exprList;
    static long max = 0;
    public long solution(String expression) {
        long answer = 0;
        
        selected = new int[3];    //연산자 순열돌리기 위한 배열
        checked = new boolean[3]; //방문배열
        
        //스트링 연산자, 숫자로 쪼개기
        tokenize(expression);
       
        //연산자 우선순위 순열 뽑기
        permutation(0);
        
        return max;
    }
    
    
    
    public static void tokenize(String expression){
        numList = new ArrayList<>();
        exprList = new ArrayList<>();
        
        //스트링을 숫자 , 연산자로 나눠서 각각 리스트에 담기
        String num = "";
        for(int i = 0 ; i < expression.length() ; i ++){
            if(expression.charAt(i) =='+' || expression.charAt(i) =='-' || expression.charAt(i) =='*'){
                exprList.add(expression.charAt(i));
                numList.add(Long.parseLong(num));
                num = "";
            }
            else num += expression.charAt(i);   
        }
        numList.add(Long.parseLong(num)); //마지막 숫자도 넣어줘야됨
    }
    
    
    public static void permutation(int depth){
        if(depth == 3){
            //[2,0,1] 가 +, - , * 의 우선순위를 의미함 즉 , + -> * -> - 순서
            
            //우선순위가 정해진대로 연산을 진행한다.
            long result = calculate();
            //System.out.println(result);
            max = Math.max(max,Math.abs(result));
            return;
        }
        
        for(int i = 0 ; i < selected.length ; i++){
            if(!checked[i]){
                checked[i] = true;
                selected[depth] = i;
                permutation(depth+1);
                checked[i] = false;
            }
        }
    }
    
    
    public static long calculate(){
        int priority = 2;
        
        //리스트에 연산자를 삭제하고 숫자도 연산하고 삭제하고 이럴거라서 클론을함
        ArrayList<Long> numListClone = (ArrayList<Long>)numList.clone();
        ArrayList<Character> exprListClone = (ArrayList<Character>)exprList.clone();
        
        while(true){
            //종료조건
            if(priority == -1 || exprListClone.size() ==0)
                break;

                for(int i = 0 ; i < exprListClone.size() ; i++){
                    // * 가 지금 우선순위인 경우
                    if(exprListClone.get(i) == '+' && selected[0] == priority){
                        //곱셈계산하고 기존의 값과 연산자는 삭제후 새로운 값을 추가
                        long num = numListClone.get(i) + numListClone.get(i+1);
                        exprListClone.remove(i);
                        numListClone.remove(i);
                        numListClone.remove(i);
                    
                        numListClone.add(i,num);
                        
                        i--;
                    }   
                }

                for(int i = 0 ; i < exprListClone.size() ; i++){
                    // * 가 지금 우선순위인 경우
                    if(exprListClone.get(i) == '-' && selected[1] == priority){
                        //곱셈계산하고 기존의 값과 연산자는 삭제후 새로운 값을 추가
                        long num = numListClone.get(i) - numListClone.get(i+1);
                        exprListClone.remove(i);
                        numListClone.remove(i);
                        numListClone.remove(i);
                    
                        numListClone.add(i,num);
                        i--;
                        
                    }   
                }

                for(int i = 0 ; i < exprListClone.size() ; i++){
                    // * 가 지금 우선순위인 경우
                    if(exprListClone.get(i) == '*' && selected[2] == priority){
                        //곱셈계산하고 기존의 값과 연산자는 삭제후 새로운 값을 추가
                        long num = numListClone.get(i) * numListClone.get(i+1);
                        
                        exprListClone.remove(i);
                        numListClone.remove(i);
                        numListClone.remove(i);
                    
                        numListClone.add(i,num);
                        i--;
                    
                    }   
                }

            priority--;
        }
        
        return numListClone.get(0);
    }
}



