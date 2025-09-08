package org.example.baekjoon;
import java.io.*;
import java.util.*;

public class Problem1912 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        int answer = -1000;
        int[] dp = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[i] = num;
            } else {
                dp[i] = Math.max(dp[i - 1] + num, num);
            }

            answer = Math.max(answer, dp[i]);
        }

        sb.append(answer).append('\n');
        System.out.print(sb);
    }
}
