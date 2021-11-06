import java.util.*;
//최소의 경우의 수 -> BFS
class Solution {
    
    public class Word{ //단어와 해당 단어가 몇 번째 단계인지 저장
        String w;
        int cnt;
        public Word(String w, int cnt){
            this.w = w;
            this.cnt = cnt;
        }
    }
    static int answer=0;
    public int solution(String begin, String target, String[] words) {
        boolean flag=false;
        for(int i=0; i<words.length; i++){
            if(target.equals(words[i])){
                flag = true;
                break;
            }
        }
        
        if(!flag){ //words에 target이 없으면 답은 0
            return 0;
        }
        bfs(begin, target, words);
        return answer;
    }
    
    public void bfs(String begin, String target, String[] words){
        Queue<Word> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        q.add(new Word(begin, 0));
        
        while(!q.isEmpty()){
            Word word = q.poll();
            String w = word.w; int cnt = word.cnt;
            
            if(w.equals(target)){
                answer = cnt;
                return;
            }
            
            for(int i=0; i<words.length; i++){
                if(!visited[i] && change(w, words[i])){
                    q.add(new Word(words[i], cnt+1));
                    visited[i]=true;
                }
            }
        }
    }
    
    public boolean change(String a, String b){
        int c = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i))
                c++;
        }
        //딱 한 글자만 다르면 true
        return c==1 ? true : false;
    }
}
