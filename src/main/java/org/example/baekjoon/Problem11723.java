package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class Problem11723 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int s = 1 << 21;
        int M = Integer.parseInt(st.nextToken());
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int num;

            if (cmd.equals("add")) {
                num = Integer.parseInt(st.nextToken());

                s = s | (1 << num);
            } else if (cmd.equals("remove")) {
                num = Integer.parseInt(st.nextToken());
                int k = (1 << num);
                k = ~k;

                s = s & k;
            } else if (cmd.equals("check")) {
                num = Integer.parseInt(st.nextToken());

                if ((s & (1 << num)) > 0) {
                    sb.append(1).append('\n');
                } else {
                    sb.append(0).append('\n');
                }
            } else if (cmd.equals("toggle")) {
                num = Integer.parseInt(st.nextToken());

                s = s ^ (1 << num);
            } else if (cmd.equals("all")) {
                s = (1 << 21) - 1;
            } else if (cmd.equals("empty")) {
                s = 0;
            }
        }

        System.out.println(sb);
    }
}
