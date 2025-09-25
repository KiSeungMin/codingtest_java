package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) {
                    arr[i][j] = 0;
                } else{
                    arr[i][j] = (int) Integer.MAX_VALUE;
                }
            }
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[start][end] = Math.min(arr[start][end], weight);
            arr[end][start] = Math.min(arr[end][start], weight);
        }

        st = new StringTokenizer(br.readLine());
        int node1 = Integer.parseInt(st.nextToken());
        int node2 = Integer.parseInt(st.nextToken());

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    if (arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int answer;
        if ((arr[1][node1] == Integer.MAX_VALUE && arr[1][node2] == Integer.MAX_VALUE) || (arr[node1][node2] == Integer.MAX_VALUE) || (arr[node1][N] == Integer.MAX_VALUE && arr[node2][N] == Integer.MAX_VALUE)) {
            answer = -1;
        } else if(arr[1][node1] == Integer.MAX_VALUE){
            answer = arr[1][node2] + arr[node2][node1] + arr[node1][N];
        } else if (arr[1][node2] == Integer.MAX_VALUE) {
            answer = arr[1][node1] + arr[node1][node2] + arr[node2][N];
        } else {
            answer = arr[1][node1] + arr[node1][node2] + arr[node2][N];
            answer = Math.min(arr[1][node2] + arr[node2][node1] + arr[node1][N], answer);
        }

        System.out.println(answer);
    }
}
