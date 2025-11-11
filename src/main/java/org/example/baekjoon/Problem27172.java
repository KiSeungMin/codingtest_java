package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem27172 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] dp = new int[1000001];
        boolean[] exist = new boolean[1000001];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            arr[n] = num;
            exist[num] = true;
        }

        for (int n = 0; n < N; n++) {
            int num = arr[n];

            for (int i = 2; i * num < dp.length; i++) {
                dp[i * num]--;

                if (exist[i * num]) {
                    dp[num]++;
                }
            }
        }

        for (int n = 0; n < N; n++) {
            System.out.print(dp[arr[n]] + " ");
        }
        System.out.println();
    }
}
