class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        // Lock이 가운데로 가는 2차원 배열 만들기
        int N = lock.length * 3 - 2;
        int[][] map = new int[N][N];
        for (int i = lock.length - 1; i < lock.length * 2 - 1; i++) {
            for (int j = lock.length - 1; j < lock.length * 2 - 1; j++) {
                map[i][j] = lock[i - lock.length + 1][j - lock.length + 1];
            }
        }

        // 왼쪽 위부터 오른쪽 아래까지 이동하면서 Key가 Lock에 맞춰지는지 확인
        for (int i = 0; i < lock.length * 2 - 1; i++) {
            for (int j = 0; j < lock.length * 2 - 1; j++) {
                answer = rotateFillCheck(i, j, key, map, lock);
                if (answer) { // true면 바로 끝내기
                    return answer;
                }
            }
        }

        return answer;
    }

    public static boolean rotateFillCheck(int x, int y, int[][] key, int[][] map, int[][] lock) {
        boolean result = false;

        for (int d = 0; d < 4; d++) {
            // Key를 90도 돌리기
            key = rotate(key);

            // 돌린 Key로 map 안의 Lock을 채우고 확인하기
            result = fillCheck(x, y, key, map, lock);
            if (result) { // true면 바로 끝내기
                return result;
            }
        }

        return result;
    }

    public static int[][] rotate(int[][] key) { // 90도 회전하기
        int[][] rotatedKey = new int[key.length][key.length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                rotatedKey[j][key.length - i - 1] = key[i][j];
            }
        }
        return rotatedKey;
    }

    public static boolean fillCheck(int x, int y, int[][] key, int[][] map, int[][] lock) {
        int[][] copyedMap = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) { // map 복사
            for (int j = 0; j < map.length; j++) {
                copyedMap[i][j] = map[i][j];
            }
        }

        // 채우기
        for (int i = x; i < x + key.length; i++) {
            for (int j = y; j < y + key.length; j++) {
                copyedMap[i][j] = copyedMap[i][j] + key[i - x][j - y];
            }
        }

        // Lock 모두 채워졌는지 확인하기
        for (int i = lock.length - 1; i < lock.length * 2 - 1; i++) {
            for (int j = lock.length - 1; j < lock.length * 2 - 1; j++) {
                if (copyedMap[i][j] == 0 || copyedMap[i][j] == 2) {
                    return false; // 빈칸이 있거나 열쇠와 자물쇠 돌기가 만난 경우 false
                }
            }
        }

        return true;
    }
}
