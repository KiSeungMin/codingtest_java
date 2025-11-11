package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem17144 {
    static int up;
    static int down;
    static int remain = -1;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        int cleaner = 0;
        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());

                if (arr[r][c] == -1) {
                    cleaner = r;
                }
            }
        }
        up = cleaner - 1;
        down = cleaner;

        for (int t = 0; t < T; t++) {
            logic();
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != -1) {
                    sum += arr[i][j];
                }
            }
        }

        System.out.println(sum);
    }

    public static void print() {
        System.out.println("==================");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }

    public static void logic() {
        spreadDust();

        rotateRight(up);
        rotateRightUp();
        rotateLeft(0);
        rotateLeftDown();

        rotateRight(down);
        rotateRightDown();
        rotateLeft(arr.length - 1);
        rotateLeftUp();
    }

    public static void spreadDust() {
        int[][] arr_clone = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr_clone.length; i++) {
            arr_clone[i] = arr[i].clone();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    spread(i, j, arr_clone);
                }
            }
        }
    }

    public static void spread(int row, int col, int[][] arr_clone) {
        int[] dy = {-1, 0, 0, 1};
        int[] dx = {0, -1, 1, 0};
        int dust_amount = arr_clone[row][col] / 5;

        for (int i = 0; i < dy.length; i++) {
            int row_next = row + dy[i];
            int col_next = col + dx[i];

            if (row_next < 0 || row_next >= arr.length || col_next < 0 || col_next >= arr[0].length) {
                continue;
            }

            if (arr[row_next][col_next] == -1) {
                continue;
            }

            arr[row][col] -= dust_amount;
            arr[row_next][col_next] += dust_amount;
        }
    }

    public static void rotateLeft(int row) {
        int tmp = arr[row][0];

        for (int i = 0; i < arr[0].length - 1; i++) {
            arr[row][i] = arr[row][i + 1];
        }

        arr[row][arr[0].length - 2] = remain;
        remain = tmp;
    }

    public static void rotateRight(int row) {
        remain = arr[row][arr[0].length - 1];

        for (int i = arr[0].length - 1; i > 1; i--) {
            arr[row][i] = arr[row][i - 1];
        }

        arr[row][1] = 0;
    }

    public static void rotateLeftUp() {

        for (int i = down + 1; i < arr.length - 2; i++) {
            arr[i][0] = arr[i + 1][0];
        }

        arr[arr.length - 2][0] = remain;
        remain = -1;
    }

    public static void rotateRightUp() {
        int tmp = arr[0][arr[0].length - 1];

        for (int i = 0; i < up - 1; i++) {
            arr[i][arr[0].length - 1] = arr[i + 1][arr[0].length - 1];
        }
        arr[up - 1][arr[0].length - 1] = remain;
        remain = tmp;
    }

    public static void rotateLeftDown() {
        for (int i = up - 1; i > 1; i--) {
            arr[i][0] = arr[i - 1][0];
        }

        arr[1][0] = remain;
        remain = -1;
    }

    public static void rotateRightDown() {
        int tmp = arr[arr.length - 1][arr[0].length - 1];

        for (int i = arr.length - 1; i > down + 1; i--) {
            arr[i][arr[0].length - 1] = arr[i - 1][arr[0].length - 1];
        }
        arr[down + 1][arr[0].length - 1] = remain;
        remain = tmp;
    }
}
