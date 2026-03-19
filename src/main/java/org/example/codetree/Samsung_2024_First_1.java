package org.example.codetree;

import java.util.*;
import java.io.*;

public class Samsung_2024_First_1 {

    public static int R, C, K;
    public static int[][] elements;
    public static int[][] board;
    public static boolean[][] visited;
    public static int count;
    public static int answer;
    public static int max_row;

    public static int[] dropElements(int col, int exit) {

        int center_row = -2;
        int center_col = col;

        int[] dx_down = {1, 2, 1};
        int[] dy_down = {-1, 0, 1};

        int[] dx_left = {-1, 0, 1, 1, 2};
        int[] dy_left = {-1, -2, -1, -2, -1};

        int[] dx_right = {-1, 0, 1, 1, 2};
        int[] dy_right = {1, 2, 1, 2, 1};

        int direction = 0;

        while(true) {

            if(direction == 0) {
                // 아래로 이동
                for(int i = 0; i < dx_down.length; i++) {

                    int row_next = center_row + dx_down[i];
                    int col_next = center_col + dy_down[i];

                    if(row_next < 0) continue;

                    if(row_next >= R) {
                        direction = 3;
                        break;
                    }

                    if(board[row_next][col_next] == -1) continue;

                    direction = 1;
                    break;
                }

                if(direction == 0) {
                    center_row++;
                }
            } else if(direction == 1) {
                // 왼쪽으로 회전
                for(int i = 0; i < dx_left.length; i++) {

                    int row_next = center_row + dx_left[i];
                    int col_next = center_col + dy_left[i];

                    if(col_next < 0) {
                        direction = 2;
                        break;
                    }

                    if(row_next < 0) continue;

                    if(row_next >= R) {
                        direction = 2;
                        break;
                    }

                    if(board[row_next][col_next] == -1) continue;

                    direction = 2;
                    break;
                }

                if(direction == 1) {
                    center_row += 1;
                    center_col -= 1;
                    exit = (exit + 3) % 4;
                    direction = 0;
                }
            } else if(direction == 2) {
                // 오른쪽으로 회전
                for(int i = 0; i < dx_right.length; i++) {

                    int row_next = center_row + dx_right[i];
                    int col_next = center_col + dy_right[i];

                    if(col_next >= C) {
                        direction = 3;
                        break;
                    }

                    if(row_next < 0) continue;

                    if(row_next >= R) {
                        direction = 3;
                        break;
                    }

                    if(board[row_next][col_next] == -1) continue;

                    direction = 3;
                    break;
                }

                if(direction == 2) {
                    center_row += 1;
                    center_col += 1;
                    exit = (exit + 1) % 4;
                    direction = 0;
                }

            } else if(direction == 3) {

                // 블럭 색칠
                int[] dx = {-1, 0, 1, 0, 0};
                int[] dy = {0, 1, 0, -1, 0};

                for(int i = 0; i < dx.length; i++) {

                    int row_next = center_row + dx[i];
                    int col_next = center_col + dy[i];

                    if(row_next < 0 || col_next < 0) {
                        clearBoard();
                        return new int[] {-1, -1};
                    }

                    board[center_row + dx[i]][center_col + dy[i]] = count;
                }

                board[center_row + dx[exit]][center_col + dy[exit]] = 10000 + count;
                count++;
                break;
            }
        }

        return new int[] {center_row, center_col};
    }

    public static void addScore(int row, int col, int count_now) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        //System.out.println("row_now: " + row + " col_now: " + col);

        if(row > max_row) max_row = row;

        for(int i = 0; i < dx.length; i++) {
            int row_next = row + dx[i];
            int col_next = col + dy[i];

            if(row_next < 0 || row_next >= R || col_next < 0 || col_next >= C) continue;

            if(visited[row_next][col_next]) continue;

            if(count_now >= 10000 && board[row_next][col_next] != -1) {
                visited[row_next][col_next] = true;
                addScore(row_next, col_next, board[row_next][col_next]);
                continue;
            }

            if(count_now == board[row_next][col_next] % 10000) {
                visited[row_next][col_next] = true;
                addScore(row_next, col_next, board[row_next][col_next]);
                continue;
            }
        }
    }

    public static void logic() {
        for(int k = 0; k < K; k++) {
            int[] centers = dropElements(elements[k][0] - 1, elements[k][1]);

            if(centers[0] == -1 && centers[1] == -1) continue;

            max_row = 0;
            visited = new boolean[R][C];
            visited[centers[0]][centers[1]] = true;
            addScore(centers[0], centers[1], board[centers[0]][centers[1]]);

            answer += max_row + 1;

            //printBoard();
            //System.out.println("answer: " + answer);
        }
    }

    public static void printBoard() {
        System.out.println("==== Print Board ====");
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void clearBoard() {
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                board[r][c] = -1;
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        elements = new int[K][2];
        board = new int[R][C];
        count = 1;
        answer = 0;

        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            elements[k][0] = Integer.parseInt(st.nextToken());
            elements[k][1] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        clearBoard();
        logic();
        System.out.println(answer);
    }
}
