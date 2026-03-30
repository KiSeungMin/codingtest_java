package org.example.codetree;

import java.util.*;
import java.io.*;


public class Hyundai9_2 {
    public static int N, T;
    public static int[][] board;
    public static Node[][] dp;
    public static Node[][] answer;

    public static class Node {

        public int notT;
        public int useT;
        public int alreadyUseT;

        public Node() {
            this.notT = 0;
            this.useT = 0;
            this.alreadyUseT = 0;
        }
    }

    public static int getMaxValue(int row_start, int col_start, int row_end, int col_end) {

        int[][] board_tmp = new int[N][N];

        for(int r = row_start; r <= row_end; r++) {
            for(int c = col_start; c <= col_end; c++) {
                board_tmp[r][c] = board[r][c];
                if(r == row_start && c == col_start) {
                    continue;
                } else if(r == row_start) {
                    board_tmp[r][c] += board_tmp[r][c - 1];
                } else if(c == col_start) {
                    board_tmp[r][c] += board_tmp[r - 1][c];
                } else {
                    board_tmp[r][c] += max(board_tmp[r - 1][c], board_tmp[r][c - 1]);
                }
            }
        }

        return board_tmp[row_end][col_end];
    }

    public static void calculateNotT(int row, int col) {
        dp[row][col].notT += board[row][col];
        if(row == 0 && col == 0) {
            return;
        } else if(row == 0) {
            dp[row][col].notT += dp[row][col - 1].notT;
        } else if(col == 0) {
            dp[row][col].notT += dp[row - 1][col].notT;
        } else {
            dp[row][col].notT += max(dp[row - 1][col].notT, dp[row][col - 1].notT);
        }
    }

    public static void calculateUseT(int row, int col) {
        int[][] board_tmp = new int[T + 1][T + 1];

        for(int r = 0; r <= T; r++) {
            for(int c = 0; c <= T; c++) {

                int row_now = row + r;
                int col_now = col + c;

                if(row_now < 0 || row_now >= N || col_now < 0 || col_now >= N) continue;


                board_tmp[r][c] = board[row_now][col_now];
                if(r == 0 && c == 0) {
                    continue;
                } else if(r == 0) {
                    board_tmp[r][c] += board_tmp[r][c - 1];
                } else if(c == 0) {
                    board_tmp[r][c] += board_tmp[r - 1][c];
                } else {
                    board_tmp[r][c] += max(board_tmp[r - 1][c], board_tmp[r][c - 1]);
                }
            }
        }

        int row_plus = T;
        int col_plus = 0;

        for(int t = 0; t <= T; t++) {

            int score = board_tmp[row_plus][col_plus];
            dp[row][col].useT = max(score, dp[row][col].useT);

            row_plus--;
            col_plus++;
        }
    }

    public static void calculateAnswer(int row, int col) {

        answer[row][col].notT = dp[row][col].notT;
        answer[row][col].useT = dp[row][col].notT + dp[row][col].useT;
        answer[row][col].alreadyUseT = answer[row][col].useT;

        if(row == 0 && col == 0) {
            return;
        } else if(row == 0) {
            answer[row][col].alreadyUseT = max(answer[row][col].alreadyUseT, answer[row][col - 1].alreadyUseT + board[row][col]);
        } else if(col == 0) {
            answer[row][col].alreadyUseT = max(answer[row][col].alreadyUseT, answer[row - 1][col].alreadyUseT + board[row][col]);
        } else {
            answer[row][col].alreadyUseT = max(answer[row][col].alreadyUseT, max(answer[row - 1][col].alreadyUseT, answer[row][col - 1].alreadyUseT) + board[row][col]);
        }
    }

    public static void logic() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                calculateNotT(i, j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                calculateUseT(i, j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                calculateAnswer(i, j);
            }
        }

        //printDP();
        //System.out.println(max(answer[i][j].notT, answer[i][j].useT));
        //printAnswer();

        System.out.println(max(max(answer[N - 1][N - 1].notT, answer[N - 1][N - 1].useT), answer[N - 1][N - 1].alreadyUseT));
    }

    public static void printAnswer() {
        System.out.println("==== printAnswer() start ====");
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(" (" + answer[i][j].notT + ", " + answer[i][j].useT + ", " + answer[i][j].alreadyUseT + ") ");
            }
            System.out.println();
        }
        System.out.println("==== printAnswer() end ====");
    }

    public static void printDP() {
        System.out.println("==== printDP() start ====");
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(" (" + dp[i][j].notT + ", " + dp[i][j].useT + ") ");
            }
            System.out.println();
        }
        System.out.println("==== printDP() end ====");
    }

    public static void printBoard() {
        System.out.println("==== printBoard() start ====");
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==== printBoard() end ====");
    }

    public static int max(int a, int b) {
        if(a > b) return a;
        return b;
    }

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        dp = new Node[N][N];
        answer = new Node[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = new Node();
                answer[i][j] = new Node();
            }
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
        //printBoard();
    }
}
