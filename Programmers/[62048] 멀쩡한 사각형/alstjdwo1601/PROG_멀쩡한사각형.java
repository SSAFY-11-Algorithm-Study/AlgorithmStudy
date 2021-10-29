package week14;

class Solution3 {
    public long solution(int w, int h) { //long 변환
        long answer = 0;
        
        //y = h/w x 꼴 그래프라고 생각
        for(int i = 0; i < w; i++)
            answer += (long)(h * i) /w; //y 절편에서 소수점 뗀것이 사각형 갯수
        
        return answer*2; //반씩 나눠서 생각햇으므로 마지막에 2곱함
    }
}
