import java.util.*;

class Solution {
    class Node {
        String word;
        int lev;
        public Node(String word, int lev) {
            this.word = word;
            this.lev = lev;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));
        boolean[] visit = new boolean[words.length];
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.word.equals(target)) {
                answer = cur.lev;
                break;
            }
            for(int i = 0; i < words.length; i++) {
                if(!visit[i] && isNext(cur.word, words[i])) {
                    visit[i] = true;
                    q.add(new Node(words[i], cur.lev + 1));
                }
            }
        }
        return answer;
    }
    
    public boolean isNext(String a, String b) {
        int cnt = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                cnt++;
                if(cnt > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
