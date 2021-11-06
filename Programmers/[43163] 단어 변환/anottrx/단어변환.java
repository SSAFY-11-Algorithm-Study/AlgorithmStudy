// bfs로 안되길래 다시 dfs로 시도는 했는데, 단어 알파벳 차이 개수 세는 것 자체가 이상해서
// https://mungto.tistory.com/53 이곳 참고해서 좀더 직관적으로 다시 풀어볼 예정입니다ㅠ

public class PROG43163 {

    static int min;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

        int answer = 0;

        min = words.length + 1;
        boolean[] visited = new boolean[words.length];

        findWord(begin, -1, 0, words, target, visited);

        answer = min;
        if (answer == words.length + 1) {
            answer = 0;
        }
        System.out.println(answer);
        // return answer;
    }

    private static void findWord(String curWord, int x, int cnt, String[] words, String target, boolean[] visited) {
        if (x == words.length) {
            return;
        }

        for (int i = x + 1; i < words.length; i++) {
            if (!visited[i]) {
                int diffCnt = countDiff(curWord, words[i], target);
                if (diffCnt == 0) {
                    min = Math.min(min, cnt);
                    break;
                } else if (diffCnt > 0) {
                    visited[i] = true;
                    findWord(words[i], i, cnt + 1, words, target, visited);
                }
            }
        }

    }

    private static int countDiff(String curWord, String nextWord, String target) {
        int nth = 0, len = curWord.length(), sameTarget = 0;
        boolean flag = false;

        for (int i = 0; i < len; i++) {
            if (!flag && curWord.charAt(i) != nextWord.charAt(i)) {
                if (nextWord.charAt(i) == target.charAt(i)) {
                    nth = i;
                    flag = true;
                }
            }
            if (nextWord.charAt(i) == target.charAt(i)) {
                sameTarget++;
            }
        }

        if (sameTarget == len) {
            return 0;
        }
        if (nth == 0) {
            return -1;
        }
        return nth;
    }
}
