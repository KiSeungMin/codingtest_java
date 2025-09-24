package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem14503 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            //System.out.println("row: " + r + " col: " + c);
            if (arr[r][c] == 0) {
                arr[r][c] = 2;
                answer++;
            }

            boolean canClean = false;
            int[][] next = {{r - 1, c}, {r + 1, c}, {r, c - 1}, {r, c + 1}};
            for(int[] n : next) {
                boolean result = check(arr, n[0], n[1]);

                if(result) {
                    canClean = true;
                    break;
                }
            }

            if(canClean) {
                d = rotate(d);

                int[] nextPoint = go(r, c, d);

                if(check(arr, nextPoint[0], nextPoint[1])){
                    r = nextPoint[0];
                    c = nextPoint[1];
                    //System.out.println("next r: " + r + " c: " + c);
                }
            } else {
                int[] nextPoint = back(r, c, d);

                if (arr[nextPoint[0]][nextPoint[1]] == 1) {
                    break;
                } else {
                    r = nextPoint[0];
                    c = nextPoint[1];
                    //System.out.println("next r: " + r + " c: " + c);
                }
            }
        }

        System.out.println(answer);
    }

    public static int[] go(int row, int col, int direction) {
        if (direction == 0) {
            return new int[]{row - 1, col};
        } else if (direction == 1) {
            return new int[]{row, col + 1};
        } else if (direction == 2) {
            return new int[]{row + 1, col};
        } else{
            return new int[]{row, col - 1};
        }
    }

    public static int[] back(int row, int col, int direction) {
        if (direction == 0) {
            return new int[]{row + 1, col};
        } else if (direction == 1) {
            return new int[]{row, col - 1};
        } else if (direction == 2) {
            return new int[]{row - 1, col};
        } else if (direction == 3) {
            return new int[]{row, col + 1};
        }

        return null;
    }

    public static int rotate(int direction) {
        int result = direction - 1;

        if(result < 0){
            result = 3;
        }

        return result;
    }

    public static boolean check(int[][] arr, int row, int col) {
        if (arr[row][col] == 0) {
            return true;
        }

        return false;
    }
}
