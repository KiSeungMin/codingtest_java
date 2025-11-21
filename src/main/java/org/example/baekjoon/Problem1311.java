package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem1311 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] cost = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[1 << N];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;  // 초기: 아무도 일 안함

        // 모든 상태를 순회
        for(int mask = 0; mask < (1 << N); mask++) {
            // 현재 몇 명이 일을 배정받았나?
            int person = Integer.bitCount(mask);

            // 이미 N명 다 배정됐으면 끝
            if(person == N) continue;

            // person번 사람이 아직 안 배정된 일 중에서 선택
            for(int work = 0; work < N; work++) {
                // work번 일이 아직 배정 안됐다면
                if((mask & (1 << work)) == 0) {
                    // work번 일 배정
                    int nextMask = mask | (1 << work);
                    dp[nextMask] = Math.min(dp[nextMask],
                            dp[mask] + cost[person][work]);
                }
            }
        }

        // 모든 일이 배정된 상태의 최소 비용
        System.out.println(dp[(1 << N) - 1]);
    }
}
