package com.littleji.algo4th.graph;

import java.io.BufferedInputStream;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Jimmy on 2016/11/15.
 */
public class Graph <T>  {
    private final int V;
    private int E;
    private HashSet<LinkedList<T>> adj;
    Graph(int V){
        this.V = V;
    }
    Graph(BufferedInputStream in, int V){
        this.V = V;
    }
    public int V(){
        return this.V;
    }
    public int E() { return this.E; }
}
