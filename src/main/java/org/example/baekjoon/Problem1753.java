package org.example.baekjoon;

import java.io.*;
import java.util.*;

public class Problem1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<ArrayList<Integer>>> arr = new ArrayList<>();
        for (int v = 0; v <= V; v++) {
            arr.add(new ArrayList<>());
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            ArrayList<Integer> edge = new ArrayList<>(Arrays.asList(w, v));
            arr.get(u).add(edge);
        }

        int[] distance = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            distance[i] = (int) Integer.MAX_VALUE;
        }
        distance[K] = 0;

        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(
                (a, b) -> {
                    return Integer.compare(a.get(0), b.get(0));
                }
        );
        pq.add(new ArrayList<>(Arrays.asList(0, K)));

        while(true) {
            if(pq.isEmpty()){
                break;
            }

            ArrayList<Integer> top = pq.peek(); pq.remove();
            int dist = top.get(0);
            int start = top.get(1);

            for (ArrayList<Integer> adj : arr.get(start)) {
                int distNow = dist + adj.get(0);
                if (distNow < distance[adj.get(1)]) {
                    distance[adj.get(1)] = distNow;
                    pq.add(new ArrayList<>(Arrays.asList(distNow, adj.get(1))));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(distance[i] == (int) Integer.MAX_VALUE ? "INF" : distance[i]);
        }
    }
}
