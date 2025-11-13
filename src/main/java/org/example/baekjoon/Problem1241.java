package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem1241 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            numbers.add(num);
        }

        int[] number_count = new int[1000001];
        int[] count = new int[1000001];

        for (int num : numbers) {
            number_count[num]++;
        }

        for (int i = 1; i < number_count.length; i++) {
            if (number_count[i] > 0) {
                for (int n = 1; n * i < count.length; n++) {
                    count[n * i] += number_count[i];
                }
            }
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(count[numbers.get(i)] - 1);
        }
    }
}
