package week15;

import java.util.*;
class Solution {
    //단어 클래스가 필요하다는 것 참고했음
    public static class Word{ 
        String word;
        int cnt;
        public Word(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
    }
    
    static int answer = 0;
    public int solution(String begin, String target, String[] words) {
        //words 안에 target 이 아예없으면 0 리턴
        boolean flag = false;
        for(int i = 0 ; i < words.length ; i++){
            if(target.equals(words[i]))
                flag = true;
        }
        
        if(!flag)
            return 0;
        else
            bfs(begin, target, words);
        
        
        return answer;
    }
    
    public static void bfs(String begin, String target, String[] words ){
        Queue<Word> q = new LinkedList<>();
        q.add(new Word(begin,0));
        boolean[] visited = new boolean[words.length];
        
        while(!q.isEmpty()){
            
            Word w = q.poll();
            String cur_w = w.word;
            int cur_cnt = w.cnt;
            
            //target나오면 종료
            if(cur_w.equals(target)){
                answer = cur_cnt;
                //System.out.println("무야호");
                return;
            }
            
            for(int i = 0 ; i < words.length; i++){
                int diff = 0;
                for(int j = 0 ; j < begin.length(); j++){
                    if(cur_w.charAt(j) != words[i].charAt(j))
                        diff++;

                }
                //딱 한글자 차이나면 큐에 담음
                if(diff==1 && !visited[i]){
                    q.add(new Word(words[i],cur_cnt+1));
                    visited[i] = true;
                    //System.out.println(words[i]);
                }
            }
        }
    }
    
    
}
