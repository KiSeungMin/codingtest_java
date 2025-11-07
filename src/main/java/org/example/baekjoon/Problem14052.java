package org.example.baekjoon;

import java.util.*;
import java.io.*;
public class Problem14052{

    public static int[][] copy(int[][] arr) {
        int [][] result = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++) {
            result[i] = arr[i].clone();
        }

        return result;
    }

    public static ArrayList<Integer> getAdjacent(int[][] arr, int row, int col) {
        int M = arr[0].length;
        ArrayList<Integer> result = new ArrayList<>();
        if (row - 1 >= 0) {
            if (arr[row - 1][col] == 0) {
                arr[row - 1][col] = 2;
                result.add((row - 1) * M + col);
            }
        }

        if (row + 1 < arr.length) {
            if (arr[row + 1][col] == 0) {
                arr[row + 1][col] = 2;
                result.add((row + 1) * M + col);
            }
        }

        if (col - 1 >= 0) {
            if (arr[row][col - 1] == 0) {
                arr[row][col - 1] = 2;
                result.add((row) * M + col - 1);
            }
        }

        if (col + 1 < M) {
            if (arr[row][col + 1] == 0) {
                arr[row][col + 1] = 2;
                result.add((row) * M + col + 1);
            }
        }

        return result;
    }

    public static int bfs(int[][] arr, ArrayList<Integer> virus) {

        int N = arr.length;
        int M = arr[0].length;

        if (virus.isEmpty()) {
            int count = 0;
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == 0) {
                        count++;
                    }
                }
            }

            return count;
        }

        ArrayList<Integer> next_virus = new ArrayList<>();
        for (Integer v : virus) {
            int row = v / M;
            int col = v % M;

            ArrayList<Integer> adjacent = getAdjacent(arr, row, col);
            for (Integer num : adjacent) {
                next_virus.add(num);
            }
        }

        return bfs(arr, next_virus);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] arr = new int[N][M];
        ArrayList<Integer> virus = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 2) {
                    virus.add(i * M + j);
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < N * M - 2; i++) {
            if (arr[i / M][i % M] != 0) {
                continue;
            }

            for (int j = i + 1; j < N * M - 1; j++) {
                if (arr[j / M][j % M] != 0) {
                    continue;
                }
                for (int k = j + 1; k < N * M; k++) {
                    if (arr[k / M][k % M] != 0) {
                        continue;
                    }

                    int[][] arr_cpy = copy(arr);
                    ArrayList<Integer> virus_cpy = (ArrayList<Integer>) virus.clone();

                    arr_cpy[i / M][i % M] = 1;
                    arr_cpy[j / M][j % M] = 1;
                    arr_cpy[k / M][k % M] = 1;

                    int result = bfs(arr_cpy, virus_cpy);
                    answer = Math.max(answer, result);
                }
            }
        }

        System.out.println(answer);
    }
}
