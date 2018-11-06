package com.littleji.algo4th;

public class util {
    public static Integer [] random(int l){
        Integer[] origin = new Integer[l] ;
        for(int i=0; i<l; i++ ) {
            origin[i] = (int) (Math.random() * 2 * l + 1);
        }
        return origin;
    }
}
