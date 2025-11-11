package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem1766 {
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static int[] counts;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++) {
            ArrayList<Integer> row = new ArrayList<>();
            arr.add(row);
        }
        counts = new int[N + 1];
        visited = new boolean[N + 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int n1, n2;

            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            arr.get(n1).add(n2);
            counts[n2]++;
        }

        int print_count = 0;
        while (true) {
            if (print_count == N) {
                break;
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && counts[i] == 0) {
                    visited[i] = true;
                    System.out.print(i + " ");
                    print_count++;

                    for (int adj : arr.get(i)) {
                        counts[adj]--;
                    }

                    break;
                }
            }
        }

        System.out.println();
    }
}
