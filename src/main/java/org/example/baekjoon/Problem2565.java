package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem2565 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] li = new int[501];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            li[start] = end;
        }

        int[] arr = new int[501];

        int answer = 0;
        for(int i = 1; i <= 500; i++) {
            if(li[i] == 0) {
                continue;
            }

            int result = 0;
            for(int j = i - 1; j >= 0; j--){
                if(li[i] > li[j] && arr[j] > result) {
                    result = arr[j];
                }
            }

            arr[i] = result + 1;
            answer = Math.max(answer, arr[i]);
        }

        System.out.println(N - answer);
    }
}
