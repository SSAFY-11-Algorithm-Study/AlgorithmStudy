package week9;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        //두번째 줄부터 시작
        for(int i = 1 ; i < triangle.length ; i++){
            for(int j = 0 ; j < triangle[i].length ; j++){
                
                //삼각형 왼쪽변은 그냥 바로 위에 값 받아야됨
                if(j ==0){
                    triangle[i][j] += triangle[i-1][j];
                }
                //삼각형 오른쪽 변은 바로 위에 값 받아야됨
                else if(j==triangle[i].length -1){
                    triangle[i][j] += triangle[i-1][j-1];
                }
                //중간에 있는 값들은 최대값을 골라서 받아야됨
                else{
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                }
                    
            }
        }
        
        //최대값을 누적하면서 받아왔기때문에 마지막배열에 최종 값들이 있는데 이중 최대값을 고르면됨
        for(int i = 0 ; i < triangle.length ; i ++){
            System.out.println(triangle[triangle.length-1][i]);
            if(answer < triangle[triangle.length-1][i])
                answer = triangle[triangle.length-1][i];
        }
        return answer;
    }
}