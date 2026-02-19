package main.java.study.a05_shortest.d01.p1389;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static class Person{
        int index;
        int width;
        Person (int index, int width){
            this.index = index;
            this.width = width;
        }
    }

    public static int N;
    public static int minKevinBaconNumber = Integer.MAX_VALUE;
    public static int answer;
    public static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for(int i = 1 ; i < N+1 ; i++) {
            bfs(i, 0);
        }
        System.out.println(answer);
    }
    private static void bfs(int start, int width) {
        Queue<Person> queue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N+1];
        queue.add(new Person(start, width));
        isVisited[start] = true;
        int count = 0;
        while(!queue.isEmpty()) {
            Person p = queue.poll();
            int listSize = adjList[p.index].size();
            for(int i = 0 ; i < listSize; i++) {
                int adjIndex = adjList[p.index].get(i);
                if(!isVisited[adjIndex]) {
                    queue.add(new Person(adjIndex, p.width+1));
                    isVisited[adjIndex] = true;
                    count += p.width+1;
                }
            }
        }

        if(minKevinBaconNumber > count) {
            minKevinBaconNumber = count;
            answer = start;
        }
    }
}

