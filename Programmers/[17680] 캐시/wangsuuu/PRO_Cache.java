import java.util.*;

class Solution {
    static int answer=0;
    static String[] cache; //캐시
    static int[] usedTime; //사용이 된 순서. 값이 작을수록 least recently used
    public int solution(int cacheSize, String[] cities) {
        
        if(cacheSize==0){ //이거 안해주면 ArrayBoundException 남.
            return cities.length * 5;
        }
        
        for(int i=0; i<cities.length; i++){ //소문자로 바꾸기
            String tmp = cities[i].toLowerCase();
            cities[i] = tmp;
        }
        cache = new String[cacheSize];
        
        for(int i=0; i<cacheSize; i++) { //String 배열의 경우, 디폴트 초기화가 안 되므로 초기화 해줘야 함!!
        	cache[i] = "";
        }
        
        usedTime = new int[cacheSize];
        
        for(int i=0; i<cities.length; i++){
            int idx = isExist(cities[i]);
            if(idx != -1){ //cache hit
                answer++;
                int max = Integer.MIN_VALUE;
                for(int j=0; j<cacheSize; j++){
                    if(max < usedTime[j])
                        max = usedTime[j];
                }
                usedTime[idx] = ++max; //현재 max 값에서 하나 증가시킨 값을 넣어줌
            } else{ //cache miss
                answer += 5;
                
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int val = 0;
                for(int j=0; j<cacheSize; j++){ //들어갈 곳을 찾음 : usedTime이 가장 작은 곳
                    if(min > usedTime[j]){
                        min = usedTime[j];
                        val = j; 
                    }
                    if(max < usedTime[j]){
                        max = usedTime[j];
                    }
                }
                cache[val] = cities[i];
                usedTime[val] = ++max; //현재 max 값에서 하나 증가시킨 값을 넣어줌
            }
        }
        return answer;
    }
    
    public static int isExist(String str){
        for(int i=0; i<cache.length; i++){
            if(cache[i].equals(str))
                return i;
        }
        return -1;
    }
}
