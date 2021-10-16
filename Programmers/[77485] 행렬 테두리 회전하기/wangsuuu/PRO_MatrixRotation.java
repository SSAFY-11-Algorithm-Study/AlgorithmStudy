import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int size = queries.length;
        int[] answer = new int[size];
        
        int[][] matrix = new int[rows+1][columns+1]; //1행 1열부터 시작
        
        //값 채워넣기
        int k=1;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                matrix[i][j] = k++;
            }
        }
        
        for(int i=0; i<size; i++){//쿼리 수만큼 반복
            int[] query = queries[i]; //쿼리 하나 꺼냄
            
            int srow=query[0]; int scol=query[1];
            int erow=query[2]; int ecol=query[3];
            
            //회전
            int tmp = matrix[srow][ecol];
            for(int j=ecol; j>scol; j--){ //위쪽
                matrix[srow][j] = matrix[srow][j-1];
            }
            for(int j=srow; j<erow; j++){ //왼쪽
                matrix[j][scol] = matrix[j+1][scol];
            }
            for(int j=scol; j<ecol; j++){ //아래쪽
                matrix[erow][j] = matrix[erow][j+1];
            }
            for(int j=erow; j>srow+1; j--){ //오른쪽
                matrix[j][ecol] = matrix[j-1][ecol];
            }
            matrix[srow+1][ecol] = tmp;
            
            //최솟값 뽑기
            int min = 100000;
            for(int x = scol; x<=ecol; x++){ //위, 아래
                min = Math.min(Math.min(min, matrix[srow][x]), matrix[erow][x]);
            }
            for(int x=srow; x<=erow; x++){ //좌, 우
                min = Math.min(Math.min(min, matrix[x][scol]), matrix[x][ecol]);
            }
            answer[i] = min;
        }
        
        return answer;
    }
}
