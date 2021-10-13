package week12;

class Solution {
    static int [][] arr ;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        arr = new int[rows][columns];
        int cnt = 1;
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j< columns ; j++){
                arr[i][j] = cnt++;
            }
        }
        
        
        for(int i = 0 ; i < queries.length ; i++){
            //배열 인덱스 맞추면서 인자 넘기고 리턴되는 최소값을 정답배열 추가
            answer[i] = rotate(queries[i][0]-1, queries[i][1]-1 
                               ,queries[i][2]-1 ,queries[i][3]-1);
            /*
            for(int j = 0 ; j < rows ; j++){
                System.out.println();
                for(int k = 0 ; k< columns ; k++){
                    System.out.print(arr[j][k] + " ");
                }
            
            }
            */
        }
        
        return answer;
    }
    
    public int rotate(int x1, int y1, int x2, int y2){
        
        int start = arr[x1][y1]; //시작값은 마지막에 넣어줘야되므로 빼놓음
        int min = start;
        
        //돌리는 순서가 맞아야함
        //상-> 하
        for(int i = x1 ; i < x2  ; i++){
            arr[i][y1] = arr[i+1][y1];
            if(min > arr[i][y1])
                min = Math.min(arr[i][y1], arr[i+1][y1]);
        }
        //좌 -> 우
        for(int i = y1 ; i < y2  ; i++){
            arr[x2][i] = arr[x2][i+1];
            if(min > arr[x2][i])
                min = Math.min(arr[x2][i], arr[x2][i+1]);
        }
        //하-> 상
        for(int i = x2 ; i > x1 ; i--){
            arr[i][y2] = arr[i-1][y2];
            if(min > arr[i][y2])
                min = Math.min(arr[i][y2], arr[i-1][y2]);
        }
        // 우-> 좌
        for(int i = y2 ; i > y1  ; i--){
            arr[x1][i] = arr[x1][i-1];
            if(min > arr[x1][i])
                min = Math.min(arr[x1][i], arr[x1][i-1]);
        }
        
        
        
        //임시저장해둔거 마지막 부분에 넣음
        arr[x1][y1+1] = start;
        
        return min;
    }
}