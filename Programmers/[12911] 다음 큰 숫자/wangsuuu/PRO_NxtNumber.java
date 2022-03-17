// 정수를 이진수 문자열로 바꿔주는 함수, Integer.toBinaryString() 기억하기...

class Solution {
    public int solution(int n) {
        int answer = 0;
        int one_number = toBinaryAndCount(n);
        
        int num = n+1;
        while(true){
            int tmp_number = toBinaryAndCount(num);
            if(tmp_number == one_number){
                answer = num;
                break;
            }
            num++;
        }
        return answer;
    }
    
    public int toBinaryAndCount(int n){
        
        //이진수로 변환
//         String tmp = "";
//         while(true){
            
//             if(n/2==1){
//                 if(n%2==0){
//                     tmp = "10" + tmp;   
//                 } else {
//                     tmp = "11" + tmp;
//                 }
//                 break;
//             }
            
//             tmp = (n%2) + tmp;
//             n = n/2;
//         }
// 이 위 코드 쓰면 효율성에서 통과 못함
        
        String tmp = Integer.toBinaryString(n);
      
        int cnt = 0;
        
        for(int i=0; i<tmp.length(); i++){
            if(tmp.charAt(i) == '1')
                cnt++;
        }
        return cnt;
    }
}

/*
10진수 -> 2진수 string
Integer.toBinaryString(정수)

n진수 -> 10진수 정수형
Integer.parseInt(n진수 문자열, n)

참고 : https://dpdpwl.tistory.com/92
*/
