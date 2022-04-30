class Solution {
    static int N, total;
    static char[] nameList = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    public int solution(int n, String[] data) {
        int answer = 0;
        
        N = 8; // 전역변수 초기화
        total = 0; // 전역변수 초기화
        int[] selected = new int[8];
        boolean[] visited = new boolean[8];
        permutation(0, visited, selected, data); // 순열

        answer = total;
        return answer;
    }
    
    private static void permutation(int cnt, boolean[] visited, int[] selected, String[] data) {
        if (cnt == N) {
            if (canSet(selected, data)) {
                total++;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[cnt] = i;
                permutation(cnt + 1, visited, selected, data);
                visited[i] = false;
            }
        }
    }

    private static boolean canSet(int[] selected, String[] data) {
        for (int i = 0; i < data.length; i++) {
            String cur = data[i];
            char start = cur.charAt(0), end = cur.charAt(2), sign = cur.charAt(3);
            int width = Character.getNumericValue(cur.charAt(4));
            int startIndex = 0, endIndex = 0;
            for (int j = 0; j < N; j++) { // 해당하는 글자 위치 찾기
                if (nameList[selected[j]] == start) {
                    startIndex = j;
                } else if (nameList[selected[j]] == end) {
                    endIndex = j;
                }
            }
            int dif = Math.abs(startIndex - endIndex) - 1; // 두 글자의 거리 구하기
            if (sign == '=' && dif == width) {
            } else if (sign == '>' && dif > width) {
            } else if (sign == '<' && dif < width) {
            } else { // 위 조건에 해당하지 않는다면 false 리턴
                return false;
            }
        }
        return true; // 모든 조건에 해당되었다면 true 리턴
    }
}
