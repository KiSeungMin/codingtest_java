package org.example.codetree;

import java.util.*;
import java.io.*;

public class Samsung_2021_Second_1 {
    public static int N, M;
    public static int[][] board;
    public static boolean[][] visited;
    public static int answer;
    public static Dice dice;

    public static class Dice {
        public int row;
        public int col;
        public int[] numbers;
        public int direction;

        Dice() {
            this.row = 0;
            this.col = 0;
            // 위, 아래, 12시, 3시, 6시, 9시
            this.numbers = new int[] {1,6,5,3,2,4};
            this.direction = 1;
        }

        public void roll() {

            if(this.direction == 0) {
                this.rollUp();
            } else if(this.direction == 1) {
                this.rollRight();
            } else if(this.direction == 2) {
                this.rollDown();
            } else if(this.direction == 3) {
                this.rollLeft();
            }

            //System.out.println("direction: " + direction + ", row: " + this.row + " , col: " + this.col);
        }

        public void rollUp() {

            this.row--;

            if(this.row < 0) {
                this.row++;
                this.direction = 2;
                rollDown();
                return;
            }

            int[] new_numbers = new int[6];

            new_numbers[3] = numbers[3];
            new_numbers[5] = numbers[5];
            new_numbers[0] = numbers[4];
            new_numbers[4] = numbers[1];
            new_numbers[1] = numbers[2];
            new_numbers[2] = numbers[0];

            numbers = new_numbers;
        }

        public void rollRight() {
            this.col++;

            if(this.col >= N) {
                this.col--;
                this.direction = 3;
                rollLeft();
                return;
            }

            int[] new_numbers = new int[6];

            new_numbers[2] = numbers[2];
            new_numbers[4] = numbers[4];
            new_numbers[3] = numbers[0];
            new_numbers[1] = numbers[3];
            new_numbers[5] = numbers[1];
            new_numbers[0] = numbers[5];

            numbers = new_numbers;
        }

        public void rollDown() {
            this.row++;

            if(this.row >= N) {
                this.row--;
                this.direction = 0;
                rollUp();
                return;
            }

            int[] new_numbers = new int[6];

            new_numbers[3] = numbers[3];
            new_numbers[5] = numbers[5];
            new_numbers[4] = numbers[0];
            new_numbers[1] = numbers[4];
            new_numbers[2] = numbers[1];
            new_numbers[0] = numbers[2];

            numbers = new_numbers;
        }

        public void rollLeft() {
            this.col--;

            if(this.col < 0) {
                this.col++;
                this.direction = 1;
                rollRight();
                return;
            }

            int[] new_numbers = new int[6];

            new_numbers[2] = numbers[2];
            new_numbers[4] = numbers[4];
            new_numbers[0] = numbers[3];
            new_numbers[3] = numbers[1];
            new_numbers[1] = numbers[5];
            new_numbers[5] = numbers[0];

            numbers = new_numbers;
        }

        public void printNumbers() {
            for(int i = 0; i < 6; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
        }
    }

    public static void setDirection() {

        int dice_score = dice.numbers[1];
        int board_score = board[dice.row][dice.col];

        //System.out.println("dice_score: " + dice_score + " , board_score: " + board_score);

        if(dice_score > board_score) {
            dice.direction = (dice.direction + 1) % 4;
        } else if(dice_score < board_score) {
            dice.direction = (dice.direction + 3) % 4;
        }
    }

    public static void DFS(int row, int col, int score_now) {

        if(visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        answer += score_now;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < 4; i++) {

            int row_next = row + dx[i];
            int col_next = col + dy[i];

            if(row_next < 0 || row_next >= N || col_next < 0 || col_next >= N) {
                continue;
            }

            if(visited[row_next][col_next] || board[row_next][col_next] != score_now) {
                continue;
            }

            DFS(row_next, col_next, score_now);
        }
    }

    public static void addScore() {

        int row_now = dice.row;
        int col_now = dice.col;

        int score_now = board[row_now][col_now];

        DFS(row_now, col_now, score_now);
    }

    public static void clearVisited() {
        visited = new boolean[N][N];
    }

    public static void logic() {
        for(int m = 0; m < M; m++) {
            clearVisited();
            dice.roll();
            addScore();
            setDirection();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        answer = 0;
        dice = new Dice();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
        System.out.println(answer);
    }
}
