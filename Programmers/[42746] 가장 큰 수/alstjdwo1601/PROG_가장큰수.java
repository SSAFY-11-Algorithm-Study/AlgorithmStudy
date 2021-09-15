package week8;



import java.util.*;

class Solution3 {
    public String solution(int[] numbers) {
        String answer = "";
        
        //정수배열을 스트링배열로 바꾸면서 0만 있는지 체크
        boolean flag = true;
        String [] strArr = new String[numbers.length];
        for(int i = 0 ; i < numbers.length; i++){
            if(numbers[i] != 0)
                flag = false;
            strArr[i] = Integer.toString(numbers[i]);
        }
        
        //전부 0으로 이루어져 있다면 정답은 그냥 0
        if(flag)
            answer = "0";
        else{
            //정렬 기준을 어떻게 할지가 어려웠는데, 검색으로 참고함
            Arrays.sort(strArr , new Comparator<String>(){
               @Override
                public int compare(String o1 , String o2){
                    return (o2+o1).compareTo(o1+o2);
                }
            });
            
            for(int i = 0 ; i < strArr.length ; i++)
                answer += strArr[i];
        }
        
        
        return answer;
    }
}

/*
import java.util.Arrays;
import java.util.Comparator;

class Solution implements Comparator<Integer>{
    
    public String solution(int[] numbers) {
        String answer = "";
      
        //전부 0인지 체크
        boolean flag = true;
        for(int i = 0 ; i < numbers.length; i ++)
            if(numbers[i] != 0)
                flag = false;
        
        if(flag) 
            answer = "0";
        else{
            Arrays.sort(numbers);
            
            for(int i = 0 ; i < numbers.length; i ++)
            	answer += numbers[i];
        }
        return answer;
    }

	@Override
	public int compare(Integer o1, Integer o2) {
		int l1= Integer.toString(o1).length();
		int l2= Integer.toString(o2).length();
		
		if(l1 == l2)
			return o2 - o1;
		else if(l1 > l2) {
			//자릿수 차이가 얼마나 나는지
			int diff = l1 - l2;
			int temp = 0;
			for(int i =1 ; i<= diff ; i++)
				temp = o2 * 10;
			
			//자리 바꾼다
			if(temp > o2)
				return Integer.compare(o2, o1*10);
			else return Integer.compare(o1*10, o2);
		}
		else  {
			//자릿수 차이가 얼마나 나는지
			int diff2 = l2 - l1;
			int temp2 =0;
			for(int i =1 ; i<= diff2 ; i++)
				temp2 = o2 * 10;
			
			if(temp2 > o2)
				return o1-o2;
			else return o2-o1;		
		}
	}
}
*/


/*
class Solution {
    static boolean [] visited ;
    static String [] selected;
    static long max;
    public String solution(int[] numbers) {
        String answer = "";
         
        visited = new boolean[numbers.length];
        selected = new String[numbers.length];
        
        boolean flag = true;
        for(int i = 0 ; i < numbers.length; i ++)
            if(numbers[i] != 0)
                flag = false;
        
        if(flag) 
            answer = "0";
        else{
            permutation(0,numbers);
            answer = Long.toString(max);
            
        }
        return answer;
    }
    
    static void permutation(int depth , int [] numbers){
        if(depth == numbers.length){
            String str = "";
            
            for(int i = 0 ; i < selected.length; i ++){
                str += selected[i];
            }
            
            long temp = Long.parseLong(str);
            max = Math.max(max, temp);
            
            return;
        }
        
        for(int i = 0 ; i < numbers.length ;i ++){
            if(!visited[i]){
                visited[i] = true;
                selected[depth] =Integer.toString(numbers[i]);
                permutation(depth+1, numbers);
                visited[i]= false;
            }
        }        
    }
}
*/