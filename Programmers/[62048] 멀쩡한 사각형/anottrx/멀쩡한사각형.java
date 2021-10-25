// https://taesan94.tistory.com/55로 이해했습니다

class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        long longW = (long) w; // long 형태
        long longH = (long) h; // long 형태

        boolean foundGCD = false; // 최대공약수 찾았는지 여부
        long gcd = Math.min(w, h); // 더 작은 수를 우선 최대공약수라고 초기화
        for (int i = (int) gcd; i > 1; i--) {
            if (longW % i == 0 && longH % i == 0) {
                gcd = (long) i; // 최대공약수
                foundGCD = true;
                break;
            }
        }
        if (!foundGCD) { // 못 찾았다면 최대공약수는 1
            gcd = 1;
        }

        answer = longW * longH - (longW + longH - gcd);

        return answer;
    }
}
