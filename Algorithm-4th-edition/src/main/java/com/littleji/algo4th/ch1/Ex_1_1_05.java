package com.littleji.algo4th.ch1;

import edu.princeton.cs.introcs.StdOut;

public class Ex_1_1_05  {

    public static boolean test(double a, double b) {
        if(0 < a && a < 1 && 0 < b && b < 1)return true;
        return false;
        
    }
    public static void main(String[] args) {
        StdOut.println(test(0.5,1.2));
    }
}