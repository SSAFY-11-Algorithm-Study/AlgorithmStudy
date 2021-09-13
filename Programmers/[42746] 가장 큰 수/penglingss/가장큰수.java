import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();

        for(int i = 0; i < numbers.length; i++)
            list.add(Integer.toString(numbers[i]));

        Collections.sort(list, new Comparator<String>(){
            public int compare(String a, String b){
                int x = Integer.parseInt(a + b);
                int y = Integer.parseInt(b + a);

                return y - x;
            }
        });

        if(list.get(0).equals("0"))
            return "0";

        String answer = "";

        for(int i = 0; i < list.size(); i++)
            answer += list.get(i);
        return answer;
    }
}
