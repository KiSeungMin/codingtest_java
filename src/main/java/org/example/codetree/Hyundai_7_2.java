package org.example.codetree;

import java.io.*;
import java.util.*;


public class Hyundai_7_2 {

    public static int N, M;
    public static int[][] board;
    public static ArrayList<int[]> round;
    public static int answer = 0;

    public static void DFS(int row, int col, int round_now, boolean[] visited) {
        if(row == round.get(round_now)[0] && col == round.get(round_now)[1]) {
            round_now++;

            if(round_now == round.size()) {
                answer++;
                return;
            }
        }

        visited[row * N + col] = true;

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        for(int i = 0; i < dx.length; i++) {
            int row_next = row + dx[i];
            int col_next = col + dy[i];

            if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) continue;

            if(visited[row_next * N + col_next]) continue;

            if(board[row_next][col_next] == 1) continue;

            DFS(row_next, col_next, round_now, visited.clone());
        }
    }

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        round = new ArrayList<>();
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                board[n][i] = Integer.parseInt(st.nextToken());
            }
        }

        for(int m = 0; m < M; m++) {
            int x, y;
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            round.add(new int[]{x - 1, y - 1});
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        boolean[] visited = new boolean[N * N];
        DFS(round.get(0)[0], round.get(0)[1], 1, visited);

        System.out.println(answer);
    }
}
