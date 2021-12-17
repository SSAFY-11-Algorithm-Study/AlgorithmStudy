class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        while(true){
            int cnt=0;
            for(int c : citations){
                if(c >= answer)
                    cnt++;
                if(cnt>=answer)//for문 탈출
                    break;
            }
            if(cnt < answer) //while문 탈출
                break;
            answer++;
        }
        return --answer;
    }
}
