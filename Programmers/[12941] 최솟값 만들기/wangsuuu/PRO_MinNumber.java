import java.util.*;
//각 곱이 최소여야 하므로 각자 오름차순으로 정렬한 뒤에, 서로 반대 방향 인덱스로 곱해 나감 
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0; i<A.length; i++){
            answer += A[i] * B[B.length-1-i];
        }

        return answer;
    }
}
