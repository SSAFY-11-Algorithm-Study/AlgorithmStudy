import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        int cnt = 0;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i = 0; i < str1.length() - 1; i++){
            if(str1.charAt(i) < 'a' || str1.charAt(i) > 'z')
                continue;
            if(str1.charAt(i+1) < 'a' || str1.charAt(i + 1) > 'z')
                continue;
            list1.add(str1.substring(i, i + 2));
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            if(str2.charAt(i) < 'a' || str2.charAt(i) > 'z')
                continue;
            if(str2.charAt(i + 1) < 'a' || str2.charAt(i + 1) > 'z')
                continue;
            list2.add(str2.substring(i, i+2));
        }
        
        for(int i = 0; i < list1.size(); ){
            String s = list1.get(i);
            
            if(list2.contains(s)){
                cnt++;
                list1.remove(i);
                list2.remove(list2.indexOf(s));
            }
            else
                i++;
        }
        
        int sum = cnt + list1.size() + list2.size();
        if(sum == 0)
            return 65536;
        long x = cnt * 65536;
        x /= sum;
        
        return (int) x;
    }
}
