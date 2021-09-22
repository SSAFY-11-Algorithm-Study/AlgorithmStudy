package week9;
import java.util.*;
class Solution2 {
    static ArrayList<String> aList;
    static ArrayList<String> bList;
    public int solution(String str1, String str2) {
        int answer = 0;
        
        //대소문자 무시하니까 일단 소문자로 다 통일
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        aList = new ArrayList<>();
        bList = new ArrayList<>();
        
        
        // 두 글자씩 끊기
        for(int i=0; i<str1.length()-1; i++) {
            //두글자 전부 문자일때만 리스트에 넣음
            if(Character.isLetter(str1.charAt(i)) && Character.isLetter(str1.charAt(i+1)))
                aList.add(str1.substring(i,i+2));
        }
       
        for(int i=0; i<str2.length()-1; i++) {
            //두글자 전부 문자일때만 리스트에 넣음
            if(Character.isLetter(str2.charAt(i)) && Character.isLetter(str2.charAt(i+1)))
                bList.add(str2.substring(i,i+2));
        }
        
        
        int inter_size = intersection(aList,bList).size();
        int union_size = aList.size() + bList.size() - inter_size;
        
        //나눗셈 정의 X
        if(inter_size == 0 && union_size == 0)
            return 65536;
        else
            answer = (int)((float)inter_size/union_size  * 65536); //형변환 조심
        return answer;
    }
    
    
    ArrayList<String> intersection(ArrayList<String> A, ArrayList<String> B) {
        //복사본 생성
        ArrayList<String> tempA = (ArrayList<String>) A.clone();
        ArrayList<String> tempB = (ArrayList<String>) B.clone();
        
        //교집합 담을 리스트
        ArrayList<String> list = new ArrayList<>();
        for(String s : tempA) {
            //겹치는 것만 담음
            if(tempB.contains(s)) {
                list.add(s);
                tempB.remove(s);
            }
        }
        //교집합 리턴
        return list;
    }

}