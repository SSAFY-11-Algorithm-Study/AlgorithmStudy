import java.util.*;

class Solution {
    ArrayList<Song> playlist;
    ArrayList<Song> bestalbum;
    HashMap<String, Integer> genremap;
    HashMap<String, Integer> albummap;
    
    class Song implements Comparable<Song>{
        int id;
        String genre;
        int play;
        
        public Song(int id, String genre, int play) {
            this.id = id;
            this.genre = genre;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song s) {
            if(this.play == s.play) {
                return this.id - s.id;
            } else if(this.play > s.play) {
                return -1;
            } else {
                return 1;
            }
        }
        
    }
    public int[] solution(String[] genres, int[] plays) {

        playlist = new ArrayList<>();
        bestalbum = new ArrayList<>();
        genremap = new HashMap<>();
        albummap = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
            int id = i;
            String genre = genres[i];
            int play = plays[i];
            
            playlist.add(new Song(id, genre, play));
            
            if(!genremap.containsKey(genre)) {
                genremap.put(genre, play);
            } else {
                genremap.put(genre, genremap.get(genre) + play);
            }
        }
        
        Collections.sort(playlist, new Comparator<Song>(){
            @Override
            public int compare(Song s1, Song s2) {
                if(s1.genre.equals(s2.genre)) {
                    return s1.compareTo(s2);
                } else {
                    return genremap.get(s2.genre) - genremap.get(s1.genre);
                }
            }
        });
        
        for(Song s : playlist) {
            if(!albummap.containsKey(s.genre)) {
                albummap.put(s.genre, 1);
                bestalbum.add(s);
            } else {
                if(albummap.get(s.genre) > 1) {
                    continue;
                } else {
                    albummap.put(s.genre, 2);
                    bestalbum.add(s);
                }
            }
        }
        
        int[] answer = new int[bestalbum.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = bestalbum.get(i).id;
        }
        
        return answer;
    }
}
