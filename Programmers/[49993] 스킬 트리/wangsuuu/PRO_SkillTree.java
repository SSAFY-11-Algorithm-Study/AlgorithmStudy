import java.util.*;

//String.indexOf("문자열") <- 못 찾을 경우 -1 반환!

/*
i가 -1이면,
0 ~ i-1까지는 < < < 순서, (이 경우, -1 주의!)
i+1 ~ 끝까지는 -1이어야 함
*/
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int t=0; t<skill_trees.length; t++){
            String cur = skill_trees[t];
            int[] idx = new int[skill.length()]; //skill 순서대로 skill_trees의 인덱스 값 저장  
            for(int i=0; i<skill.length(); i++){
                idx[i] = cur.indexOf(Character.toString(skill.charAt(i)));
            }
            
            boolean flag=true;
            int cnt=0;
            
            here : for(int i=0; i<idx.length; i++){
                if(idx[i]==-1){
                    cnt++;
                    if(i - 1 >=0){
                        int[] tmp1 = new int[i]; // 0 ~ i-1
                        int[] tmp2 = new int[i];
                        for(int j=0; j<tmp1.length; j++){
                            if(idx[j]== -1){ //배열 정렬할 경우, -1이 맨 처음으로 오게 되므로 맨 뒤로 보내기 위해 최댓값을 저장해 줌 
                                tmp1[j] = 100;
                                tmp2[j] = 100;
                            } else{
                                tmp1[j] = idx[j];
                                tmp2[j] = idx[j];                                
                            }
                        }
                        Arrays.sort(tmp2);
                        for(int j=0; j<tmp1.length; j++){
                            if(tmp1[j] != tmp2[j]){
                                flag=false;
                                break here;
                            }
                        }
                    }
                    
                    if(i+1<idx.length){
                        for(int j=i+1; j<idx.length; j++){
                            if(idx[j]!=-1){
                                flag=false;
                                break here;
                            }
                        }
                    }
                }
            }
            //한번도 -1이 없을 경우
            if(cnt==0){
                int[] tmp = idx.clone();
                Arrays.sort(tmp);
            
                for(int i=0; i<idx.length; i++){
                    if(tmp[i] != idx[i]){
                        flag=false;
                        break;
                    }
                }   
            }
            if(flag)
                answer++;
        }
        return answer;
    }
}
