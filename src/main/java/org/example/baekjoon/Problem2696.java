package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem2696 {

    public static void getAnswer(int[] numbers) {
        PriorityQueue<Integer> low_pq = new PriorityQueue<>();
        PriorityQueue<Integer> high_pq = new PriorityQueue<>();

        if (numbers.length == 1) {
            System.out.println(1);
            System.out.println(numbers[0]);
            return;
        }

        low_pq.add(Math.min(numbers[0], numbers[1]) * -1);
        high_pq.add(Math.max(numbers[0], numbers[1]));

        System.out.println(numbers.length / 2 + 1);
        System.out.print(numbers[0] + " ");

        for (int i = 2; i < numbers.length; i++) {
            int num = numbers[i];

            int low_front = low_pq.peek() * -1;
            int high_front = high_pq.peek();

            if(num <= low_front) {
                low_pq.add(num * -1);

                if (low_pq.size() > high_pq.size() + 1) {
                    high_pq.add(low_pq.peek() * -1);
                    low_pq.poll();
                }
            } else {
                high_pq.add(num);
                if (high_pq.size() > low_pq.size()) {
                    low_pq.add(high_pq.peek() * -1);
                    high_pq.poll();
                }
            }

            if (i % 2 == 0) {
                System.out.print(low_pq.peek() * -1 + " ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            int[] numbers = new int[M];
            for (int i = 0; i <= M / 10; i++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; i * 10 + m < M && m < 10; m++) {
                    numbers[i * 10 + m] = Integer.parseInt(st.nextToken());
                }
            }

            getAnswer(numbers);
        }
    }
}
