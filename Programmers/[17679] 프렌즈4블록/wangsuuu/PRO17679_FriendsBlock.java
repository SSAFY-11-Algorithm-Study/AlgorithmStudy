import java.util.*;

class Solution {
    static char[][] map;
    static boolean[][] erase;
    static int answer;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int M, N;
    
    public int solution(int m, int n, String[] board) {
        M = m; N = n;
        map = new char[m][n];
        
        for(int i=0; i<m; i++){
            map[i] = board[i].toCharArray();
        }
        
        while(true){
            erase = new boolean[M][N];
            
            //1. 3방향 탐색 후 다 같으면 visited배열 true로
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] != '.') //이거 없으면 시간초과 남.
                        check(i, j);
                }
            }
            //2. true인 것들은 지우고 빈칸으로 바꿈
            int erasedNum = erasePicture();
            answer += erasedNum;
            if(erasedNum == 0) //더이상 지워질 게 없으면 종료
                break;
            
            //3. 블록들을 밑으로 내림
            goDown();
        }
        return answer;
    }
    
    public void check(int x, int y){
        
        int cnt=0;
        char pic = map[x][y];
        for(int i=0; i<3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || nx>=M || ny<0 || ny>=N)
                return;
            if(map[nx][ny]==pic)
                cnt++;
        }
        
        if(cnt==3){ //4개가 다 같으면
            erase[x][y] = true;
            for(int i=0; i<3; i++){
                erase[x + dx[i]][y + dy[i]] = true;
            }
        }
    }
    
    public int erasePicture(){
        int cnt=0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(erase[i][j]){
                    cnt++;
                    map[i][j] = '.'; //빈칸으로 바꿈
                }
            }
        }
        return cnt;
    }
    
    public void goDown(){
        for(int y=0; y<N; y++){ //y 한줄씩 봄
            for(int x=M-1; x>=0; x--){ //x는 밑에서부터 봄
                if(map[x][y]=='.'){ //x가 빈칸이면, 가장 가까운 위의 그림 찾기
                    for(int a=x-1; a>=0; a--){
                        if(map[a][y]!='.'){ //그림이면 그림과 빈칸의 위치를 바꿈(내려줌)
                            map[x][y] = map[a][y];
                            map[a][y] = '.'; 
                            break; //이거 해줘야 함!!
                        }
                    }
                }
            }
        }
    }
    
}
