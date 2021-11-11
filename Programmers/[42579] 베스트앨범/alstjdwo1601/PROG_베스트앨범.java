package week16;

import java.util.*;
class Solution {
    class Song implements Comparable<Song>{
        int num;
        String genre;
        int play;
        
        public Song(int num, String genre, int play) {
            this.num = num;
            this.genre = genre;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song s) {
            //재생수 같으면 num 낮은거 먼저
            if(this.play == s.play) 
                return this.num - s.num;
            //앞에가 재생수 높으면 뒤로 위치 바꾸기
            else if(this.play > s.play) 
                return -1;
            else return 1;
            
        }
    }
    HashMap<String, Integer> totalPlay_hm;
    ArrayList<Song> singList ;
    ArrayList<Song> bestAlbum ;
    public int[] solution(String[] genres, int[] plays) {
        
        totalPlay_hm = new HashMap<>();
        singList = new ArrayList<>();
        bestAlbum = new ArrayList<>();
        
        
        for(int i = 0 ; i < genres.length ; i ++){
            //각각의 노래 재생수를 리스트에 저장
            singList.add(new Song(i,genres[i],plays[i]));
            
            //해쉬맵에 (장르,재생횟수) 로 넣는데, 없으면 만들고 있으면 횟수 추가
            if(!totalPlay_hm.containsKey(genres[i]))
                totalPlay_hm.put(genres[i], plays[i]);
            else
                totalPlay_hm.put(genres[i], totalPlay_hm.get(genres[i]) + plays[i]);
        }
        
        //singList 를 재생수에 따라 정렬
        Collections.sort(singList, new Comparator<Song>(){
            @Override
            public int compare(Song s1, Song s2) {
                if(s1.genre.equals(s2.genre)) 
                    return s1.compareTo(s2);
                 else 
                    return totalPlay_hm.get(s2.genre) - totalPlay_hm.get(s1.genre);
                
            }
        });
        
        
        //여기서 조건에 따라 2개의 곡을 베스트앨범에 추가하는 로직인데
        //여기서 뭔가 조건이 이상한거같은데.. 뭔지 모르겠음 테스트케이스는되는데 돌리면 4개말곤 틀렸다고함
        int genreCnt = 0;
        String beforeGenre= "";
        for(int i = 0 ; i < singList.size()-1; i++){
            //System.out.println(singList.get(i).genre + " , " +singList.get(i).play+ " , " +singList.get(i).num);
            
        	//장르가 같은게 연속으로 나오면 카운트 늘리면서 담음
            if(singList.get(i).genre.equals(singList.get(i+1).genre)){
                if(genreCnt>=2)
                    continue;
                genreCnt++;
                bestAlbum.add(new Song(singList.get(i).num, singList.get(i).genre , singList.get(i).play));
                
                
            }
            //장르가 다른게 나오면 카운트 초기화하면서 담음
            else{
                if(genreCnt<2){
                    genreCnt = 0;
                    bestAlbum.add(new Song(singList.get(i).num, singList.get(i).genre , singList.get(i).play));
                }
                else {
                    genreCnt = 0;
                    bestAlbum.add(new Song(singList.get(i+1).num, singList.get(i+1).genre , singList.get(i+1).play));
                }
                    
            }
            
        }
         
        
        
        
        
        
        int[] answer = new int[bestAlbum.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = bestAlbum.get(i).num;
        }
        return answer;
    }
}