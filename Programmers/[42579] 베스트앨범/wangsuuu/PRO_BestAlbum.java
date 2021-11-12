import java.util.*;
//블로그 참고함. 
//너무 어렵게 생각하지 않기. Map을 정렬해야 한다면 일일히 for 돌면서 최댓값 찾아나가면 된다.

class Solution {
    
    public class Music{
        int id;
        int playNum;
        public Music(int id, int playNum){
            this.id = id;
            this.playNum = playNum;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        //1. 장르 별 총 재생횟수 구하기
        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }
        
        //2. 베스트 엘범에 수록될 장르의 순서 정하기 : 최대 재생수를 가진 key를 찾고 지움.
        ArrayList<Integer> album = new ArrayList<>(); //답을 담을 리스트
        
        while(map.size()!=0){
            String max_genre = "";
            int max_play=-1;
            for(String key : map.keySet()){ 
                if(max_play<map.get(key)){//최댓값 갱신
                    max_genre=key;
                    max_play=map.get(key);
                }
            }
            map.remove(max_genre);
            
            //3. 각 장르별 곡 두개 정하기
            ArrayList<Music> tmp = new ArrayList<>(); //max_genre의 각 곡의 고유번호와 재생수를 저장할 리스트
            for(int i=0; i<genres.length; i++){
                if(genres[i].equals(max_genre)){
                    tmp.add(new Music(i, plays[i])); //고유번호가 작은 순서대로 담기기 때문에, 재생횟수가 같은 노래 중에서는 자동으로 고유번호가 낮은 노래가 앞에 위치
                }
            }
            
            Collections.sort(tmp, (o1, o2) -> o2.playNum - o1.playNum); //재생수를 기준으로 내림차순 정렬
            album.add(tmp.get(0).id);
            if(tmp.size()>1){ //장르에 속한 곡이 2곡 이상이면
                album.add(tmp.get(1).id);
            }
        }
        
        int[] answer = new int[album.size()];
        for(int i=0; i<album.size(); i++){
            answer[i] = album.get(i);
        }
        
        return answer;
    }
}
