package week12;

class Solution2 {
    //도저히 감안와서 풀이참고했는데 dp는 이해못하겠고
    //그나마 dfs방식으로 이해해보려했는데 이것도 이해가안됨
    
    static int num,target;
    static int answer = Integer.MAX_VALUE;
    public int solution(int N, int number) {
        
        
        num = N;  //사용할 숫자
        target = number; //목표 숫자
        
        dfs(0,0);
        return answer;
    }
    
    public static void dfs(int depth, int sum){
        if(depth >8){
            answer = -1;
            return;
        }
        
        if(sum == target){
            answer = Math.min(answer, depth);
            return;
        }
        
        dfs(depth+1 , sum+ num);
        dfs(depth+1 , sum- num);
        dfs(depth+1 , sum* num);
        dfs(depth+1 , sum/ num);
    }
}