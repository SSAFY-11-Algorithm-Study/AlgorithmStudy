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

        int w = Integer.parseInt(st1.nextToken());
        int h = Integer.parseInt(st1.nextToken());
        int p = Integer.parseInt(st2.nextToken());
        int q = Integer.parseInt(st2.nextToken());
        int t = Integer.parseInt(st3.nextToken());

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

        bw.write(Integer.toString(p));
        bw.write(" ");
        bw.write(Integer.toString(q));
        bw.flush();
        bw.close();

    }

}
