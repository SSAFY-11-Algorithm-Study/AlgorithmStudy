class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        while(true){
            if(a%2==0) a /= 2;
            else a = (a+1)/2;
            
            if(b%2==0) b/=2;
            else b = (b+1)/2;
            
            answer++;
            if(a==b){ //a와 b가 서로 붙게 됨
                break;
            }
        }

        return answer;
    }
    //ex) a: 4 - 2 - 1 - 1
    //    b: 7 - 4 - 2 - 1   -> answer=3
    
}
