package com.littleji.algo4th.ch1;

public class Ex_1_1_09 {
    public static String toBinaryString(int input){
        int N = input;
        String s = "";
        while(N>0){
            s = ((Integer)(N%2)).toString() + s ;
            N = N/2;
        }
        return s;
    }
    public static void main(String[] args) {

        System.out.println(toBinaryString(2));
    }
}
