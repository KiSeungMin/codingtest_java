package org.example.codetree;

import java.util.*;
import java.io.*;

public class Hyundai8_2 {
    public static int N, M;
    public static ArrayList<Integer> A;
    public static ArrayList<Integer> B;
    public static Queue<Integer> queue;
    public static int[] dp_A;
    public static int[] dp_B;
    public static int answer = -1;

    public static void getAnswer() {

        if(dp_A[M] > 0) {
            answer = dp_A[M];
        }

        if(dp_B[M] > 0 && (answer == -1 || dp_B[M] < answer)) {
            answer = dp_B[M];
        }

        for(int i = 1; i < M; i++) {
            if(dp_A[i] == 0 || dp_B[M - i] == 0) continue;

            int result_now = dp_A[i] + dp_B[M - i];

            if(answer == -1 || result_now < answer) {
                answer = result_now;
            }
        }

        System.out.println(answer);
    }

    public static void setB() {

        for(int i = 0; i < B.size(); i++) {
            int num = B.get(i);

            for(int j = M - num; j >= 0; j--) {
                if(j != 0 && dp_B[j] == 0) continue;

                if(dp_B[num + j] == 0 || dp_B[j] < dp_B[num + j] - 1) {
                    dp_B[num + j] = dp_B[j] + 1;
                }
            }
        }

        //printDP(dp_B);
    }

    public static void BFS_A() {

        while(true) {
            if(queue.isEmpty()) break;

            int top = queue.peek(); queue.remove();

            for(int i = 0; i < A.size(); i++) {
                int num_now = top + A.get(i);
                if(num_now > M) break;

                if(dp_A[num_now] == 0 || dp_A[num_now] > dp_A[top] + 1) {
                    dp_A[num_now] = dp_A[top] + 1;
                    queue.add(num_now);
                }
            }
        }
    }

    public static void setA() {
        queue = new LinkedList<>();
        queue.add(0);

        BFS_A();
        //printDP(dp_A);
    }

    public static void printDP(int[] dp) {
        for(int i = 0; i < dp.length; i++) {
            System.out.println(i + " : " + dp[i]);
        }
    }

    public static void logic() {
        Collections.sort(A);
        Collections.sort(B);
        setA();
        setB();
        getAnswer();
    }

    public static void input() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new ArrayList<Integer>();
        B = new ArrayList<Integer>();
        dp_A = new int[M + 1];
        dp_B = new int[M + 1];

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            char c = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            if(c == 'A'){
                A.add(num);
            } else {
                B.add(num);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        logic();
    }
}
