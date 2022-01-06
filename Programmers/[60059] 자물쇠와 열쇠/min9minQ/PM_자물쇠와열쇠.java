// 풀이방법 생각하기 어려움..
// 유튜브 풀이 시청.. 
// https://www.youtube.com/watch?v=I1App3qLi6o

package time22;

public class PM_자물쇠와열쇠 {

	public static void main(String[] args) {
		int[][] key = {{0,0,0},{1,0,0},{0,1,1}};
		int[][] lock = {{1,1,1},{1,1,0},{1,0,1}};

		boolean answer = false;
		
		int point = key.length-1;
        for(int x = 0; x < point + lock.length; x++) { //가로 이동 거리
            for(int y = 0; y < point + lock.length; y++) { // 세로 이동 거리
                for(int r = 0; r < 4; r++) {
                    int[][] bigLock = new int[lock.length + key.length * 2][lock.length + key.length * 2]; 
                    for(int i = 0; i < lock.length; i++) {
                        for(int j = 0; j < lock.length; j++) {
                            bigLock[point + i][point + j] = lock[i][j]; // 크게 만든 lock 가운데에 원래의 lock 값 복사해넣기
                        }
                    }
                    match(bigLock, key, r, x, y);  //newLock배열에 key배열을 더해준다
                    if(check(bigLock, key.length - 1, lock.length)) answer = true; //자물쇠 영역이 모두 유효한 값인지 확인
                }
            }
        }
		
		System.out.println(answer);
	}

	private static void match(int[][] bigLock, int[][] key, int r, int x, int y) {
        int n = key.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(r == 0) { 
                    bigLock[x + i][y + j] += key[i][j]; //그냥 키값 더해주기
                } else if(r == 1) { //90도 회전
                    bigLock[x + i][y + j] += key[j][n - i - 1]; //90도 돌렸을 때 열의 좌표를 가지고 행의 좌표로 만드는 식으로 비교해서
                } else if(r == 2) { //180
                    bigLock[x + i][y + j] += key[n - i - 1][n- j - 1];
                } else { //270
                    bigLock[x + i][y + j] += key[n - j - 1][i];
                }
            }
        }
    }
    
    private static boolean check(int[][] bigLock, int point, int length) {
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(bigLock[point + i][point + j] != 1) return false;
            }
        }
        return true;
    }
}
