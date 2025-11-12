package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem33071 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());


            ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(n2, n1));
            arr.add(tmp);
        }

        arr.sort((a, b) -> a.get(0) - b.get(0));

        int left = 0;
        int right = arr.size() - 1;
        int result = -2100000001;

        while (left <= right) {
            //System.out.println("left = " + left);
            while (right < arr.size() - 1 && arr.get(left).get(0) + arr.get(right).get(0) < K) {
                right++;
            }
            //System.out.println("right = " + right);
            while (right > left + 1 && (arr.get(left).get(0) + arr.get(right).get(0) > K || arr.get(left).get(1).equals(arr.get(right).get(1)))) {
                right--;
            }
            //System.out.println("right = " + right);

            //System.out.println("left value: " + arr.get(left).get(0) + " right value: " + arr.get(right).get(0));

            if (right > left && !arr.get(left).get(1).equals(arr.get(right).get(1)) && arr.get(left).get(0) + arr.get(right).get(0) <= K) {
                result = Math.max(result, arr.get(left).get(0) + arr.get(right).get(0));
            }

            left++;
        }

        if (result == -2100000001) {
            System.out.println("NO");
        } else {
            System.out.println(result);
        }
    }
}
