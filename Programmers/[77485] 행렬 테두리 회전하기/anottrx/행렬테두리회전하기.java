class Solution {
    static int min;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = num++;
            }
        }

        for (int tc = 0; tc < queries.length; tc++) {
            min = Integer.MAX_VALUE;
            moveAndCopy(queries[tc], map);
            answer[tc] = min; // 답
        }

        return answer;
    }

    static private void moveAndCopy(int[] queries, int[][] map) {
        int[] upRow = moveRight(queries, map); // 윗줄 오른쪽으로 이동
        int[] rightCol = moveDown(queries, map); // 오른쪽줄 아래로 이동
        int[] downRow = moveLeft(queries, map); // 아랫줄 왼쪽으로 이동
        int[] leftCol = moveUp(queries, map); // 왼쪽줄 위로 이동

        // 이동하면서 저장한 거 복사붙여넣기
        for (int i = queries[1], j = 0; j < upRow.length; i++, j++) {
            map[queries[0] - 1][i] = upRow[j];
            min = Math.min(min, upRow[j]);
        }
        for (int i = queries[0], j = 0; j < rightCol.length; i++, j++) {
            map[i][queries[3] - 1] = rightCol[j];
            min = Math.min(min, rightCol[j]);
        }
        for (int i = queries[3] - 2, j = 0; j < downRow.length; i--, j++) {
            map[queries[2] - 1][i] = downRow[j];
            min = Math.min(min, downRow[j]);
        }
        for (int i = queries[2] - 2, j = 0; j < leftCol.length; i--, j++) {
            map[i][queries[1] - 1] = leftCol[j];
            min = Math.min(min, leftCol[j]);
        }
    }

    static private int[] moveRight(int[] queries, int[][] map) {
        int len = queries[3] - queries[1];
        int[] copied = new int[len];
        for (int i = 0, j = queries[1] - 1; i < len; i++, j++) {
            copied[i] = map[queries[0] - 1][j];
        }
        return copied;
    }

    static private int[] moveDown(int[] queries, int[][] map) {
        int len = queries[2] - queries[0];
        int[] copied = new int[len];
        for (int i = 0, j = queries[0] - 1; i < len; i++, j++) {
            copied[i] = map[j][queries[3] - 1];
        }
        return copied;
    }

    static private int[] moveLeft(int[] queries, int[][] map) {
        int len = queries[3] - queries[1];
        int[] copied = new int[len];
        for (int i = 0, j = queries[3] - 1; i < len; i++, j--) {
            copied[i] = map[queries[2] - 1][j];
        }
        return copied;
    }

    static private int[] moveUp(int[] queries, int[][] map) {
        int len = queries[2] - queries[0];
        int[] copied = new int[len];
        for (int i = 0, j = queries[2] - 1; i < len; i++, j--) {
            copied[i] = map[j][queries[1] - 1];
        }
        return copied;
    }
}
