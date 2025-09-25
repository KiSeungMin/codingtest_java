package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem1197 {

    public static Integer getParent(int[] parents, int index) {
        while (parents[index] != index) {
            index = parents[index];
        }

        return index;
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(
                (a, b) -> {
                    return Integer.compare(a.get(0), b.get(0));
                }
        );

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new ArrayList<>(Arrays.asList(weight, start, end)));
        }

        int answer = 0;
        int[] parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parents[i] = -1;
        }

        while (!pq.isEmpty()) {
            ArrayList<Integer> top = pq.peek();
            pq.remove();

            int start = top.get(1);
            int end = top.get(2);

            if (parents[start] == -1 && parents[end] == -1) {
                parents[start] = start;
                parents[end] = start;

                answer += top.get(0);
            } else if (parents[start] != -1 && parents[end] == -1) {
                parents[end] = parents[start];
                answer += top.get(0);
            } else if (parents[start] == -1 && parents[end] != -1) {
                parents[start] = parents[end];
                answer += top.get(0);
            } else {
                int startParent = getParent(parents, start);
                int endParent = getParent(parents, end);

                if (startParent != endParent) {
                    parents[endParent] = startParent;
                    parents[start] = startParent;
                    parents[end] = startParent;
                    answer += top.get(0);
                }
            }
        }

        System.out.println(answer);
    }
}