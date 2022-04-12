class Solution {
    static int[] answer, result, info2;
    static int N, max;
    public int[] solution(int n, int[] info) {
        N = n;
        answer = new int[11];
        result = new int[n];
        max = -1;
        
        comb(0,0,info); //각 원소에서 0~10을 고르는 중복조합
        
        if(max == -1){
            return new int[] {-1};
        } else {
            return answer;
        }
    }
    
    public void comb(int cnt, int start, int[] info){
        if(cnt==N){ //다 뽑았다면
            info2 = new int[11]; //라이언의 점수표. 중복조합 다 뽑았을 때마다 새롭게 초기화 필요!
            for(int i : result){ //info2 채움
                info2[i]++;
            }
            //라이언과 어피치 점수 구함
            int lion=0, apche=0;
            for(int i=0; i<11; i++){
                if(info[i] > info2[i]){ //어피치가 점수 가져감
                    apche += (10-i); //점수는 인덱스 0이 10인 것을 기억할 것.
                } else if(info[i] < info2[i]) { //라이언이 점수 가져감
                    lion += (10-i);
                } else {
                    if(info[i] == info2[i] && info[i]!= 0)
                        apche += (10-i);
                }
            }
            int diff = lion-apche;
            if(diff > 0 && max < diff){ //max 업데이트
                max = diff;
                answer = info2;
            } else if(diff > 0 && max == diff){ //같은게 있을 경우 - 낮은 점수 더 많이 맞춘걸로 update
                for(int i=10; i>=0; i--){
                    if(answer[i] < info2[i]){
                        answer = info2;
                        break;
                    } else if(answer[i] > info2[i]){ //answer는 그대로.
                        break;
                    }
                }
            }
            return;
        }
        
        for(int i=start; i<11; i++){
            result[cnt] =i;
            comb(cnt+1, i, info);
        }
        
    }
}
