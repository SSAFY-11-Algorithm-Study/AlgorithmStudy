import java.util.*;

class Solution {
    public long solution(String expression) {
        
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        
        String num = "";
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'){
                ops.add(expression.charAt(i));
                nums.add(Long.parseLong(num));
                num = "";
            } else{
                num += expression.charAt(i);
            }
        }
        nums.add(Long.parseLong(num));
        
        long[] res = new long[6];
        res[0] = prize(nums, ops, '+', '-', '*');
        res[1] = prize(nums, ops, '+', '*', '-');
        res[2] = prize(nums, ops, '-', '+', '*');
        res[3] = prize(nums, ops, '-', '*', '+');
        res[4] = prize(nums, ops, '*', '-', '+');
        res[5] = prize(nums, ops, '*', '+', '-');
        
        long max = 0;
        
        for(int i = 0; i < res.length; i++){
            max = Math.max(max, Math.abs(res[i]));
        }
        
        return max;
    }
  
    public long calculate(long n1, long n2, char op){
        if(op == '+')
            return n1 + n2;
        else if(op == '-')
            return n1 - n2;
        else
            return n1 * n2;
    }
    
    public void cal(List<Long> nums, List<Character> ops, char op){
        for(int i = 0; i < ops.size(); i++){
            if(ops.get(i) == op){
                long n1 = nums.get(i);
                long n2 = nums.get(i+1);
                long res = calculate(n1, n2, op);
                nums.remove(i);
                nums.remove(i);
                nums.add(i, res);
                ops.remove(i);
                i--;
            }
        }
    }
  
    public long prize(List<Long> nums, List<Character> ops, char op1, char op2, char op3){
        List<Long> tmpnums = new ArrayList<>();
        List<Character> tmpops = new ArrayList<>();
        
        for(int i = 0; i < nums.size(); i++)
            tmpnums.add(nums.get(i));
        for(int i = 0; i < ops.size(); i++)
            tmpops.add(ops.get(i));
        
        cal(tmpnums,tmpops,op1);
        cal(tmpnums,tmpops,op2);
        cal(tmpnums,tmpops,op3);
        
        return tmpnums.get(0);
    }

}
