class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) { // 제일 왼쪽에 있다면 그대로
                    triangle[i][j] += triangle[i - 1][j];
                } else if (j == triangle[i].length - 1) { // 제일 오른쪽에 있다면 그대로
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else { // 그외는 윗줄의 왼쪽과 오른쪽 중 더 큰 값 가지기
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }

        for (int j = 0; j < triangle[triangle.length - 1].length; j++) {
            answer = Math.max(answer, triangle[triangle.length - 1][j]); // 최댓값
        }

        return answer;
    }
}
