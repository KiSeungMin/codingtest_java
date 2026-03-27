package org.example.codetree;

import java.util.*;
import java.io.*;

public class Samsung_2025_First_2 {
    public static int N, Q;
    public static int[][] board;
    public static int[][] arr;
    public static int answer;
    public static int[][][] visited;
    public static int row_start;
    public static int col_start;
    public static int row_end;
    public static int col_end;

    public static void DFS(int row_now, int col_now, int jump, int time) {

        if(visited[row_now][col_now][jump] > 0 && time >= visited[row_now][col_now][jump]) return;

        //if(time > 0 && row_now == row_start && col_now == col_start && jump == 1) return;

        if(time > answer) {
            return;
        }

        if(row_now == row_end && col_now == col_end) {
            answer = time - 1;
            return;
        }

        visited[row_now][col_now][jump] = time;
        System.out.println("row_now: " + row_now + " col_now: " + col_now + " jump: " + jump + " time: " + time);

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        for(int n = 0; n < 4; n++) {

            boolean canGo = true;
            int row_next = row_now + dx[n] * jump;
            int col_next = col_now + dy[n] * jump;

            if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) continue;

            if(board[row_next][col_next] <= 0) continue;

            for(int j = 1; j < jump; j++) {
                if(board[row_now + dx[n] * j][col_now + dy[n] * j] < 0) {
                    canGo = false;
                    break;
                }
            }

            if(canGo) {
                DFS(row_next, col_next, jump, time + 1);
            }
        }

        if(jump - 1 >= 1) {
            DFS(row_now, col_now, jump - 1, time + 1);
        }

        if(jump + 1 <= 5) {
            DFS(row_now, col_now, jump + 1, (time + (jump + 1) * (jump + 1)));
        }
    }

    public static void findAnswer(int[] arr_now) {
        row_start = arr_now[0];
        col_start = arr_now[1];

        row_end = arr_now[2];
        col_end = arr_now[3];

        DFS(row_start, col_start, 1, 1);
    }

    public static void logic() {
        for(int i = 0; i < Q; i++) {

            visited = new int[N][N][6];
            answer = 2000000000;
            findAnswer(arr[i]);

            if(answer == 2000000000) answer = -1;
            System.out.println(answer);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int n = 0; n < N; n++) {
            String line = br.readLine();

            for(int i = 0; i < N; i++) {
                char c = line.charAt(i);

                if(c == '.') {
                    board[n][i] = 1;
                } else if(c == 'S') {
                    board[n][i] = 0;
                } else if(c == '#'){
                    board[n][i] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());
        arr = new int[Q][4];
        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 4; i++) {
                arr[q][i] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
    }

    public static void printBoard() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
