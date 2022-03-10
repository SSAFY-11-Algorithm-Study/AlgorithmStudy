// https://ilmiodiario.tistory.com/91 보고 변형해서 따라했는데 틀렸습니다

import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";

        if (isRight(p)) {
            return p;
        } else {
            return sss(p);
        }
    }
    
    public static String sss(String str) {
        if (str.length() == 0) {
            return str;
        }
        int open = 0;
        int close = 0;
        String u = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            u = u + str.charAt(i);
            if (open != 0 && open == close) {
                break;
            }
        }
        String v = str.substring(open + close, str.length());
        if (isRight(u)) {
            String str2 = "(" + sss(v);
            str2 = str2 + ")";
            u = u.substring(1, u.length() - 1);
            u = u.replace("(", ".");
            u = u.replace(")", "(");
            u = u.replace(".", ")");
            str2 = str2 + u;
            return str2;
        } else {
            return u + sss(v);
        }
    }

    public static boolean isRight(String str) {
        int open = 0;
        boolean isRight = true;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    return false;
                }
            }
        }
        if(open>0) {
            return false;
        }
        return true;
    }
}
