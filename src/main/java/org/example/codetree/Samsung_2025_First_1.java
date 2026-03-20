package org.example.codetree;


import java.io.*;
import java.util.*;

public class Samsung_2025_First_1 {
    public static int N, Q;
    public static int[][] elements;
    public static int[][] board;
    public static boolean[][] visited;
    public static int answer;
    public static int count;
    public static ArrayList<ArrayList<int[]>> boxes;
    public static ArrayList<int[]> box_sort;
    public static boolean[][] isAdd;

    public static void printAnswer() {

        answer = 0;
        isAdd = new boolean[Q + 1][Q + 1];
        visited = new boolean[N][N];

        for(int y_now = 0; y_now < N; y_now++) {
            for(int x_now = 0; x_now < N; x_now++) {

                int[] dy = {1, 0, 0, -1};
                int[] dx = {0, 1, -1, 0};

                for(int i = 0; i < dy.length; i++) {
                    int y_next = y_now + dy[i];
                    int x_next = x_now + dx[i];

                    if(y_next >= N || x_next >= N || y_next < 0 || x_next < 0) continue;
                    if(board[y_next][x_next] == 0) continue;

                    int num_now = board[y_now][x_now];
                    int num_next = board[y_next][x_next];

                    if(num_now != num_next) {
                        if(isAdd[num_now][num_next] == false && isAdd[num_next][num_now] == false)  {
                            isAdd[num_now][num_next] = true;
                            isAdd[num_next][num_now] = true;

                            int count_now = boxes.get(num_now).size();
                            int count_next = boxes.get(num_next).size();

                            answer += count_now * count_next;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static void moveElement(ArrayList<int[]> coordinates, int number, int[][] new_board) {

        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {

                boolean success = true;
                for(int c = 0; c  < coordinates.size(); c++) {
                    int y_now = coordinates.get(c)[0] + y;
                    int x_now = coordinates.get(c)[1] + x;

                    if(y_now >= N || x_now >= N || y_now < 0 || x_now < 0) {
                        success = false;
                        break;
                    }

                    if(new_board[y_now][x_now] != 0) {
                        success = false;
                        break;
                    }
                }

                if(success) {
                    for(int c = 0; c  < coordinates.size(); c++) {
                        int y_now = coordinates.get(c)[0] + y;
                        int x_now = coordinates.get(c)[1] + x;

                        new_board[y_now][x_now] = number;
                    }
                    return;
                }
            }
        }
    }

    public static void moveElements() {
        int[][] new_board = new int[N][N];

        for(int b = 0; b < box_sort.size(); b++) {
            moveElement(boxes.get(box_sort.get(b)[1]), box_sort.get(b)[1], new_board);
        }

        board = new_board;
        //System.out.println("==== move Element ====");
        //printBoard();
    }

    public static void DFS(int y, int x, int base_y, int base_x, int number) {

        visited[y][x] = true;
        int[] box = new int[]{y - base_y, x - base_x};
        boxes.get(number).add(box);

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        for(int i = 0; i < 4; i++) {
            int y_next = y + dy[i];
            int x_next = x + dx[i];

            if(y_next < 0 || y_next >= N || x_next < 0 || x_next >= N) continue;

            if(visited[y_next][x_next]) continue;

            if(board[y_next][x_next] == number) {
                DFS(y_next, x_next, base_y, base_x, number);
            }
        }
    }

    public static void calculateElements() {
        visited = new boolean[N][N];
        ArrayList<Integer> erase_list = new ArrayList<>();
        for(int i = 0; i < boxes.size(); i++) {
            boxes.set(i, new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(board[i][j] == 0) continue;

                if(visited[i][j]) continue;

                int number = board[i][j];

                if(!boxes.get(number).isEmpty()) {
                    erase_list.add(number);
                    continue;
                }

                DFS(i, j, i, j, number);
            }
        }

        for(int i = 0; i < erase_list.size(); i++) {
            int number =  erase_list.get(i);
            boxes.set(number, new ArrayList<>());
        }

        box_sort = new ArrayList<>();
        for(int i = 1; i < boxes.size(); i++) {
            if(!boxes.get(i).isEmpty()) {
                box_sort.add(new int[]{boxes.get(i).size(), i});
            }
        }

        box_sort.sort((a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }

            return b[0] - a[0];
        });
    }

    public static void addNewElement(int[] element) {
        count++;

        int x_start = element[0];
        int y_start = element[1];
        int x_end = element[2];
        int y_end = element[3];

        for(int x = x_start; x < x_end; x++) {
            for(int y = y_start; y < y_end; y++) {
                board[y][x] = count;
            }
        }

        //System.out.println("==== add new element ====");
        //printBoard();
    }

    public static void printBoard() {
        for(int i = N - 1; i >= 0; i--) {
            for(int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void logic() {

        for(int q = 0; q < Q; q++) {
            //System.out.println("===== logic start ======");
            addNewElement(elements[q]);
            calculateElements();
            moveElements();
            printAnswer();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        elements = new int[Q][4];
        boxes = new ArrayList<>();
        answer = 0;
        count = 0;
        visited = new boolean[N][N];
        board = new int[N][N];
        boxes.add(new ArrayList<>());

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            for(int p = 0; p < 4; p++) {
                elements[q][p] = Integer.parseInt(st.nextToken());
            }

            boxes.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
    }
}
