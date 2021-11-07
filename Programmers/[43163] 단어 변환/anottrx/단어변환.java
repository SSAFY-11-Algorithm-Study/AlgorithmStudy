class Solution {

    static int min;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        min = words.length + 1;
        boolean[] visited = new boolean[words.length];

        findWord(begin, 0, words, target, visited);

        answer = min;
        if (answer == words.length + 1) { // 변환할 수 없는 경우에는 0을 리턴
            answer = 0;
        }

        return answer;
    }

    private static void findWord(String curWord, int cnt, String[] words, String target, boolean[] visited) {
        if (curWord.equals(target)) { // targert과 같아지면 최솟값 구하기
            min = Math.min(min, cnt);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && countDiff(curWord, words[i])) {
                visited[i] = true;
                findWord(words[i], cnt + 1, words, target, visited);
                visited[i] = false;
            }
        }
    }

    private static boolean countDiff(String curWord, String nextWord) { // 중복되는 알파벳 개수 세기
        int len = curWord.length(), sameCnt = 0;

        for (int i = 0; i < len; i++) {
            if (curWord.charAt(i) != nextWord.charAt(i)) {
                sameCnt++;
                if (sameCnt > 1) {
                    return false; // 다른 알파벳이 2개 이상이라면 false
                }
            }
        }
        return true; // 중복되는 단어는 없기 때문에 여기로 오면 알파벳 1개만 다른 것
    }
}
