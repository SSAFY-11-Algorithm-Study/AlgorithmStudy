class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int[2];
        
        for(int i=1; i<=yellow; i++){
            
            if(yellow % i == 0){ //나누어 떨어지면
                int v = i; //세로
                int h = yellow / i; //가로
                if(v > h) break;
                
                if(v*2 + h*2 + 4 == brown){
                    answer = new int[] {h+2, v+2};
                    break;
                }
            }
        }
        return answer;
    }
}
