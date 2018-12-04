package com.littleji.algo4th.ch4;


import edu.princeton.cs.introcs.StdRandom;

public class Graph {

    Graph(int V) {

    }

    public static void change1(int [] p){
        System.out.println(p);
        p = new int[]{2};
        System.out.println(p);
    }

    public static void change2(int p){
        System.out.println(p);
        p = 3;
        System.out.println(p);
    }
    public static void main(String [] args){
        int [] a = {1};
        int b = 2;
        System.out.println(b);
        change1(a);
        change2(b);
        StdRandom.uniform();
    }

}
