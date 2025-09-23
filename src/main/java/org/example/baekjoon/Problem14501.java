package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem14501 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];
        int[][] DP = new int[N + 1][2];

        int answer = 0;

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            arr[n][0] = T;
            arr[n][1] = P;
        }

        for(int i = 1; i <= N; i++){

            if (arr[i][0] + i > N + 1) {
                continue;
            }

            int maxValue = 0;
            for(int j = i - 1; j >= 0; j--) {
                if (DP[j][1] > maxValue && i >= DP[j][0]) {
                    maxValue = DP[j][1];
                }
            }

            DP[i][0] = i + arr[i][0];
            DP[i][1] = maxValue + arr[i][1];

            answer = Integer.max(answer, DP[i][1]);
        }

        System.out.println(answer);
    }
}
