package org.example.codetree;

import java.util.*;
import java.io.*;

public class Hyundai6_2 {

    static int N, M, S, T;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] reverseGraph;

    static void dfs(int now, int stop, ArrayList<Integer>[] g, boolean[] visited) {
        if (visited[now]) return;
        visited[now] = true;

        // 목적지에 도착하면 더 이상 이동하면 안 됨
        if (now == stop) return;

        for (int next : g[now]) {
            if (!visited[next]) {
                dfs(next, stop, g, visited);
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            reverseGraph[y].add(x);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        boolean[] fromS = new boolean[N + 1];
        boolean[] fromT = new boolean[N + 1];
        boolean[] toS = new boolean[N + 1];
        boolean[] toT = new boolean[N + 1];

        // 출근길: S -> ... -> T, T 이후 확장 금지
        dfs(S, T, graph, fromS);

        // 퇴근길: T -> ... -> S, S 이후 확장 금지
        dfs(T, S, graph, fromT);

        // 어떤 점에서 S로 갈 수 있는가? == 역그래프에서 S부터 도달 가능한가
        dfs(S, -1, reverseGraph, toS);

        // 어떤 점에서 T로 갈 수 있는가? == 역그래프에서 T부터 도달 가능한가
        dfs(T, -1, reverseGraph, toT);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (i == S || i == T) continue;

            if (fromS[i] && fromT[i] && toS[i] && toT[i]) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}