import java.util.*;

//첫번째 원소 -> n번 등장
//두번쨰 원소 -> n-1번 등장
//세번째 원소 -> n-2번 등장....

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    
    public int[] solution(String s) {
        
        s = s.substring(1, s.length()-1); //양끝 {} 제거
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '{'){
                String tmp = "";
                i++;
                while(s.charAt(i)!='}'){
                    tmp += Character.toString(s.charAt(i)); //char -> String
                    i++;
                }
                count(tmp); //tmp는 하나의 집합 안의 숫자와 콤마로만 이루어 짐
            }
        }
        
        ArrayList<int[]> res = new ArrayList<>();
        
        for(int key : map.keySet()){
            int value = map.get(key);
            int[] arr = {key, value};
            res.add(arr);
        }
        
        Collections.sort(res, new Comparator<int[]>(){ //value를 기준으로 내림차순 정렬
            public int compare(int[] o1, int[] o2){
                return o2[1] - o1[1];
            }
        });
        
        int[] answer = new int[res.size()];
        
        for(int i=0; i<res.size(); i++){
            answer[i] = res.get(i)[0];
        }
        
        return answer;
    }
    
    public void count(String str) {
        String[] tmp = str.split(",");
        for(int i=0; i < tmp.length; i++){
            int num = Integer.parseInt(tmp[i]);
            map.put(num, map.getOrDefault(num, 0)+1);
        }
    }
}
