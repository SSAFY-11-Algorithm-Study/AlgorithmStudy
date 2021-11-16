package week17;

class Solution{
    public int solution(int n, int a, int b){
        int answer = 0;
       
        
        //b가 무조건 크게끔 정렬
        if(a>b){
            int temp = a;
            a= b;
            b= temp;
        }
        while(true){
            //b가 짝수고 a가 1작으면 같은 라운드에 매칭
            if((b-a ==1) && b%2==0){
                answer++;
                break;
            }
            
            //다음 라운드에 부여받는 번호
            a = a/2 + a%2;
            b = b/2 + b%2;
            answer++;
        }

        return answer;
    }
}
