package org.example.codetree;

import java.util.*;
import java.io.*;

public class Hyundai11_1 {
    static String text;
    static int K, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int n = text.length();

        if (K > n) {
            System.out.println(0);
            return;
        }

        HashMap<Long, Integer> countMap = new HashMap<>();

        long value = 0L;

        for (int i = 0; i < K; i++) {
            value = (value << 1) | (text.charAt(i) - '0');
        }

        countMap.put(value, 1);
        if (1 >= M) {
            System.out.println(1);
            return;
        }

        long mask = (1L << K) - 1;

        for (int i = K; i < n; i++) {
            value = ((value << 1) & mask) | (text.charAt(i) - '0');

            int count = countMap.getOrDefault(value, 0) + 1;
            if (count >= M) {
                System.out.println(1);
                return;
            }
            countMap.put(value, count);
        }

        System.out.println(0);
    }
}
