package week8;


//조이스틱을 위아래로 움직인후 좌우를 고를때 어디로 가야되는지
//고르는게 너무 어려움..
class Solution4 {
    static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String [] arr;
    static String [] token;
    public int solution(String name) {
        int answer = 0;
        
        arr= name.split("");
        token = alpha.split("");
        
        boolean flag = true;
        for(int i = 0 ; i < arr.length; i ++){
            if(!arr[i].equals("A"))
                flag = false;
        }
        
        if(flag) 
            answer = 0;
        else{
            //1. A~M 이면 위로 올리고 N~Z이면 밑으로 내리는게 최소
            //2. 현재 커서위치에서 앞으로 가야되는지 뒤로가야되는지 골라야함
            
            boolean [] visited = new boolean[arr.length];
            for(int i = 0 ; i < arr.length ; i ++){
                if(arr[i].compareTo("M") <= 0 ){  // A ~ M 의 알파벳인 경우
                    if(arr[i].equals("A")) 
                        continue;
                    answer += findIdx(arr[i]); //위로 조이스틱 옮긴 횟수
                    visited[i] = true;
                }
                else if(arr[i].compareTo("Z") <=0 && arr[i].compareTo("N") >=0){ //N ~ Z 알파벳인경우 밑으로 내려야됨
                    answer += findIdx2(arr[i]); //아래로 조이스틱 옮긴 횟수
                    visited[i] = true;
                }
                
                //다음 위치가 왼쪽인지 오른쪽인지
                //JBAACAAAAAKAAAAAN  
                //이경우면  J에서 거꾸로 N찍고 다시 돌아온뒤 오른쪽 쭉가는게 더 효율적인데
                //고르는 기준을 정할 수가 없음..
                int nextPos = findPos(i); 
            }
        }
        return answer;
    }
    
    //A ~ M 의 인덱스 뽑기
    public static int findIdx(String str){
        int idx = 0;
        for(int i = 0 ; i < token.length ; i ++){
            if(token[i].equals(str)){
                idx =  i;
                break;
            }
        }
        return idx;
    }
    
    //N ~ Z 인덱스 뽑기
    public static int findIdx2(String str){
        int idx = 0;
        for(int i = token.length -1 ; i >=0 ; i --){
            if(token[i].equals(str)){
                idx = token.length - i;
                break;
            } 
        }
        return idx+1;
    }
    
    
    //다음 위치를 왼쪽으로 가야되는지 오른쪽으로 가야되는지
    public static int findPos(int current){
        int nextPos = 0;
        
        return nextPos;
    }
}