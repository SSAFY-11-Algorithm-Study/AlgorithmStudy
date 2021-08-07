package com.third;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj10158_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringTokenizer st3 = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int w = Integer.parseInt(st1.nextToken()); // AB
        int h = Integer.parseInt(st1.nextToken()); // AB
        int p = Integer.parseInt(st2.nextToken()); // AB
        int q = Integer.parseInt(st2.nextToken()); // AB
        int t = Integer.parseInt(st3.nextToken()); // AB

        /*
        p += t;
        q += t;

        p %= 2 * w;
        q %= 2 * h;

        if(p > w) {
            p = 2*w - p;
        }
        if(q > h) {
            q = 2*h - q;
        }
        */

        int divW = (t + p) / w;
        int modW = (t + p) % w;
        int divH = (t + q) / h;
        int modH = (t + q) % h;

        if (divW % 2 == 0) {
            p = modW;
        } else {
            p = w - modW;
        }

        if (divH % 2 == 0) {
            q = modH;
        } else {
            q = h - modH;
        }

//        System.out.println(p + " " + q);
        bw.write(Integer.toString(p));//출력
        bw.write(" ");//출력
        bw.write(Integer.toString(q));//출력
        bw.flush();//남아있는 데이터를 모두 출력시킴
        bw.close();//스트림을 닫음

    }

}
