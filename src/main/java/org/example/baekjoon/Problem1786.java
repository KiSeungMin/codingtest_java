package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem1786 {

    public static String text;
    public static String pattern;

    public static int[] lps;
    public static int answer = 0;
    public static ArrayList<Integer> answer_index;

    public static void KMP() {

        int j = 0;
        for(int i = 0; i < text.length(); i++) {

            while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            if(text.charAt(i) == pattern.charAt(j)) {
                j++;

                if(j == pattern.length()) {
                    answer++;
                    j = lps[j - 1];
                    answer_index.add(i - pattern.length() + 2);
                }
            }
        }
    }

    public static void makeLPS() {

        int cnt = 0;
        for(int i = 1; i < pattern.length(); i++) {
            while(cnt > 0 && pattern.charAt(i) != pattern.charAt(cnt)) {
                cnt = lps[cnt - 1];
            }

            if(pattern.charAt(i) == pattern.charAt(cnt)) {
                cnt++;
                lps[i] = cnt;
            }
        }
    }

    public static void printLPS() {
        System.out.println("==== printLPS() ====");
        for(int i = 0; i < pattern.length(); i++) {
            System.out.println(pattern.charAt(i) + " : " + lps[i]);
        }
    }

    public static void logic() {
        makeLPS();
        //printLPS();
        KMP();

        System.out.println(answer);
        for(int i = 0; i < answer_index.size(); i++) {
            System.out.println(answer_index.get(i));
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
        pattern = br.readLine();

        lps = new int[pattern.length()];
        answer_index = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
    }
}
