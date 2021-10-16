class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        
        int num = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = num++;
            }
        }
        
        for(int k = 0; k <queries.length; k++){
            int x1 = queries[k][0]-1;
            int y1 = queries[k][1]-1;
            int x2 = queries[k][2]-1;
            int y2 = queries[k][3]-1;
            int temp = map[x1][y1]; 
            answer[k] = temp;
            
            for(int i = x1; i < x2; i++){
                map[i][y1] = map[i+1][y1];
                if(map[i][y1] < answer[k]){
                    answer[k] = map[i][y1];
                }
            }
            
            for(int i = y1; i < y2; i++){
                map[x2][i] = map[x2][i+1];
                if(map[x2][i] < answer[k]){
                    answer[k] = map[x2][i];
                }
            }
            
            for(int i = x2; i > x1; i--){
                map[i][y2] = map[i-1][y2];
                if(map[i][y2] < answer[k]){
                    answer[k] = map[i][y2];
                }
            }
            
            for(int i = y2; i > y1+1; i--){
                map[x1][i] = map[x1][i-1];
                if(map[x1][i] < answer[k]){
                    answer[k] = map[x1][i];
                }
            }
            map[x1][y1+1] = temp;
        }
        return answer;
    }
}