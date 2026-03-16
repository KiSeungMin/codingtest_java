package org.example.codetree;

import java.util.*;
import java.io.*;

public class Samsung_2022_First_2 {
    static int N, M, K, C, answer;
    static Tree[][] board;

    public static class Tree {

        public int count;
        public int jecho;
        public boolean isEmpty;

        public Tree(int count) {
            this.count = count;
            this.jecho = 0;

            if(this.count == 0) {
                this.isEmpty = true;
            } else {
                this.isEmpty = false;
            }
        }
    }

    public static void jechoTree(int row, int col) {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};

        board[row][col].count = 0;
        board[row][col].jecho = C + 1;

        for(int i = 0; i < 4; i++) {
            for(int k = 1; k <= K; k++) {

                int row_next = row + dx[i] * k;
                int col_next = col + dy[i] * k;

                if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) {
                    break;
                }

                if(board[row_next][col_next].count <= 0) {
                    board[row_next][col_next].jecho = C + 1;
                    break;
                }

                board[row_next][col_next].count = 0;
                board[row_next][col_next].jecho = C + 1;
            }
        }
    }

    public static int getJechoCount(int row, int col) {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};

        int result = board[row][col].count;

        for(int i = 0; i < 4; i++) {
            for(int k = 1; k <= K; k++) {

                int row_next = row + dx[i] * k;
                int col_next = col + dy[i] * k;

                if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) {
                    break;
                }

                if(board[row_next][col_next].count <= 0) {
                    break;
                }

                result += board[row_next][col_next].count;
            }
        }

        return result;
    }

    public static void jecho() {
        int max_result = 0;
        int max_row = 0;
        int max_col = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(board[i][j].count <= 0) {
                    continue;
                }

                int result = getJechoCount(i, j);
                if(result > max_result) {
                    max_result = result;
                    max_row = i;
                    max_col = j;
                }
            }
        }

        jechoTree(max_row, max_col);
        answer += max_result;
    }

    public static void beonsikTree(int row, int col) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        ArrayList<Integer> row_list = new ArrayList<>();
        ArrayList<Integer> col_list = new ArrayList<>();

        for(int i = 0; i < 4; i++) {

            int row_next = row + dx[i];
            int col_next = col + dy[i];

            if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) {
                continue;
            }

            if(!board[row_next][col_next].isEmpty) {
                continue;
            }

            if(board[row_next][col_next].jecho > 0) {
                continue;
            }

            row_list.add(row_next);
            col_list.add(col_next);
        }

        if(row_list.size() == 0) {
            return;
        }

        int result = board[row][col].count / row_list.size();

        for(int i = 0; i < row_list.size(); i++) {
            int row_next = row_list.get(i);
            int col_next = col_list.get(i);

            board[row_next][col_next].count += result;
        }
    }

    public static void beonsik() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(board[i][j].count <= 0 || board[i][j].isEmpty) continue;

                beonsikTree(i, j);
            }
        }
    }

    public static int growTree(int row, int col) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int result = 0;

        for(int i = 0; i < 4; i++) {

            int row_next = row + dx[i];
            int col_next = col + dy[i];

            if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) {
                continue;
            }

            if(board[row_next][col_next].count <= 0) {
                continue;
            }

            result++;
        }

        return result;
    }

    public static void grow() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(board[i][j].count <= 0) {
                    continue;
                }

                int result = growTree(i, j);
                board[i][j].count += result;
            }
        }
    }

    public static void setting() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(board[i][j].jecho > 0 && board[i][j].count != -1) {
                    board[i][j].count = 0;
                    board[i][j].jecho--;
                }

                if(board[i][j].count == 0){
                    board[i][j].isEmpty = true;
                }

                if(board[i][j].count > 0) {
                    board[i][j].isEmpty = false;
                }
            }
        }
    }

    public static void logic() {
        for(int m = 0; m < M; m++) {
            setting();
            grow();
            beonsik();
            jecho();
            //printBoard();
        }
    }

    public static void printBoard() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(board[i][j].count + " ");
            }
            System.out.println();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = 0;

        board = new Tree[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int count = Integer.parseInt(st.nextToken());
                board[i][j] = new Tree(count);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
        System.out.println(answer);
    }
}
