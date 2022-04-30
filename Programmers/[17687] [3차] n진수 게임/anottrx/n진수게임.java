class Solution {
    static char[] charList = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int tempNum = 0;
        String temp = "0"; // 직접 구현할 경우 0은 미리 넣어둔다
        while (true) {
            if (temp.length() > t * m) { // 답은 t 길이만큼 나와야하지만 인원수 m명만큼 반복해야지 답이 나온다
                break;
            }
            temp = temp + numToString(tempNum, n);
          
            // 아래처럼 Integer.toString()을 이용할 경우 numToString()처럼 직접 구현하지 않아도 된다. 대신 이렇게 할 경우 temp는 "0"이 아닌 빈 문자열부터 시작해야 한다
            // temp = temp + Integer.toString(tempNum, n).toUpperCase(); 
            
            tempNum++;
        }

        int cnt = 0;
        for (int i = p - 1; i < temp.length(); i = i + m) {
            answer = answer + temp.charAt(i);
            cnt++;
            if (cnt == t) {
                break;
            }
        }
        return answer;
    }
    
    private static String numToString(int tempNum, int n) {
        String result = "";
        while (true) {
            if (tempNum <= 0) {
                break;
            }
            result = charList[tempNum % n] + result;
            tempNum = tempNum / n;
        }
        return result;
    }
}
