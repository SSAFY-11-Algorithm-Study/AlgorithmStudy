import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};

        int index = 1;
        char last = words[0].charAt(words[0].length() - 1);
        HashSet<String> wordSaid = new HashSet<>();
        wordSaid.add(words[0]);

        while (true) {
            String word = words[index];
            if (last != word.charAt(0) || wordSaid.contains(word)) {
                answer = new int[]{index % n + 1, (int) Math.ceil((index) / n + 1)};
                break;
            }
            wordSaid.add(word);
            last = word.charAt(word.length() - 1);
            index++;
            if (index == words.length) {
                answer = new int[]{0, 0};
                break;
            }
        }

        return answer;
    }
}
