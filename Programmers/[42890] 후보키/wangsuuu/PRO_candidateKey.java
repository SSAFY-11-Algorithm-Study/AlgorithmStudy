import java.util.*;

class Solution {
    
    static int answer;
    static ArrayList<HashSet<Integer>> list; //후보키들을 저장
    static boolean[] isSelected;
    static int col_cnt;
	
    public int solution(String[][] relation) {
        
        answer=0;
        list = new ArrayList<>();
        col_cnt = relation[0].length; //컬럼 갯수
        
    	//부분집합으로 풀면 길이가 적은 순서대로 구해지는게 아니라 제대로 답이 안나옴.. 재귀로 태워서 풀기
        for(int i=1; i<=col_cnt; i++) {
        	candidateKey(0, i, new HashSet<Integer>(), relation);
        }

        return answer;
    }
    
    public void candidateKey(int cnt, int target, HashSet<Integer> set, String[][] relation){ //후보키가 될 수 있는 컬럼 번호 조합을 구함
        if(cnt==target){
            
            for(HashSet<Integer> s : list){
                if(set.containsAll(s)) //후보키가 포함되어 있다면 -> 최소성 만족 못하므로 탈락
                	//s.containsAll(set)으로 해서 한참 헤맸음..
                    return;
            }
            if(isUnique(set, relation)){
                answer++;
                list.add(set);
            }
            return;
        }
        
        for(int i = cnt; i<col_cnt; i++) {
        	HashSet<Integer> newSet = new HashSet<>(set);
        	newSet.add(i);
        	candidateKey(cnt+1, target, newSet, relation);
        }
        
    }
	
	public boolean isUnique(HashSet<Integer> set, String[][] relation){
	        ArrayList<String> l = new ArrayList<>();
	        for(int i=0; i<relation.length; i++) { //튜플 하나 하나
	            String tmp = "";
	            for(int idx : set){ //for each문으로 set 조회 가능
	                tmp += relation[i][idx];
	            }
	            if(l.contains(tmp))
	                return false;
	            l.add(tmp);
	        }
	        return true;
	    }
}
