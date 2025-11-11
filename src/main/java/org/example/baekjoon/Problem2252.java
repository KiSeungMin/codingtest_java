package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem2252 {

    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> arr;

    public static void dfs(int node) {
        visited[node] = true;

        if (arr.get(node).size() != 1) {
            for (int i = 1; i < arr.get(node).size(); i++) {
                int adj_node = arr.get(node).get(i);

                if (!visited[adj_node]) {
                    dfs(adj_node);
                }
            }
        }

        System.out.print(node + " ");
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        arr = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(i);
            arr.add(row);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            arr.get(n2).add(n1);
        }

        //arr.sort((a, b) -> b.size() - a.size());
        ArrayList<ArrayList<Integer>> arr_cpy = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            ArrayList<Integer> row_cpy = (ArrayList<Integer>) arr.get(i).clone();
            arr_cpy.add(row_cpy);
        }
        arr_cpy.sort((a, b) -> b.size() - a.size());

        for (int i = 0; i < arr_cpy.size(); i++) {
            int startNode = arr_cpy.get(i).get(0);

            if (startNode == 0) {
                continue;
            }

            if (!visited[startNode]) {
                dfs(startNode);
            }
        }
    }
}
