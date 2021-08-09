import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> deployCnt = new ArrayList<>();
        int N = progresses.length;
        int[] leftDays = new int[N];
        
        for(int i = 0; i < N; i++) {
            leftDays[i] = 100 - progresses[i];
            
            if(leftDays[i] % speeds[i] != 0) {
                leftDays[i] = leftDays[i] / speeds[i] + 1;
            } else leftDays[i] /= speeds[i];
            
        }
        
        int date = leftDays[0];
        int idx = 1;
        int cnt = 1;
        while(idx < N) {
            int d = leftDays[idx];
            if(date >= d) {
                cnt++;
                idx++;
            } else {
                deployCnt.add(cnt);
                cnt = 0;
                date = d;
            }
        }
        deployCnt.add(cnt);
        
        
        int[] answer = new int[deployCnt.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = deployCnt.get(i);
        }
        return answer;
    }
}
