class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int m = arr2[0].length;
        
        int[][] answer = new int[n][m];
        int x=0, y=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int sum = 0;
                for(int k=0; k<arr2.length; k++){
                    sum += arr1[i][k] * arr2[k][j];
                }
                answer[x][y] = sum;
                if(y+1 >= m){
                x++; y=0;
                } else{
                    y++;
                }
            }
        }
        
        return answer;
    }
}
