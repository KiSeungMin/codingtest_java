package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem14499 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int [N][M];
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(x, y);
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++) {
            int direction = Integer.parseInt(st.nextToken());

            logic(arr, dice, direction);
        }
    }

    public static void logic(int[][] arr, Dice dice, int direction) {

        Point point = getNextPoint(arr, dice.getRow(), dice.getCol(), direction);
        int next_row = point.getRow();
        int next_col = point.getCol();

        if (next_row != -1 && next_col != -1) {

            dice.move(next_row, next_col);
            dice.rotate(direction);

            if (arr[next_row][next_col] == 0) {
                int bottom = dice.getBottom();
                arr[next_row][next_col] = bottom;
            } else {
                dice.set(2, arr[next_row][next_col]);
                arr[next_row][next_col] = 0;
            }

            System.out.println(dice.getTop());
        }
    }

    public static Point getNextPoint(int[][] arr, int row_now, int col_now, int direction) {
        if (direction == 1 && col_now + 1 < arr[0].length) {
            return new Point(row_now, col_now + 1);
        } else if (direction == 2 && col_now - 1 >= 0) {
            return new Point(row_now, col_now - 1);
        } else if (direction == 3 && row_now - 1 >= 0) {
            return new Point(row_now - 1, col_now);
        } else if (direction == 4 && row_now + 1 < arr.length) {
            return new Point(row_now + 1, col_now);
        }

        return new Point(-1, -1);
    }

    public static class Point {
        int row; int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }
    }

    public static class Dice {
        private int[] numbers;
        private int row;
        private int col;

        public Dice(int row, int col) {
            this.numbers = new int[6];
            this.row = row;
            this.col = col;
        }

        public int getTop() {
            return numbers[0];
        }

        public int getBottom() {
            return numbers[2];
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public void move(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void rotate(int direction) {
            if (direction == 1) {
                swap(0, 4);
                swap(4, 2);
                swap(5, 2);
            } else if (direction == 2) {
                swap(0, 5);
                swap(5, 2);
                swap(2, 4);
            } else if (direction == 3) {
                swap(0, 3);
                swap(0, 1);
                swap(1, 2);
            } else if (direction == 4) {
                swap(1, 2);
                swap(0, 1);
                swap(0, 3);
            }
        }

        public void set(int index, int num) {
            numbers[index] = num;
        }

        public void swap(int index1, int index2) {
            int num1 = numbers[index1];
            int num2 = numbers[index2];

            int tmp = num2;
            numbers[index2] = num1;
            numbers[index1] = tmp;
        }
    }
}