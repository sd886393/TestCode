package com.littleji.algo4th.ch1;
/*
递归有三条最重要的性质：
1. 方法的第一条总是一个包含 return 的条件语句
2. 总是在尝试解决一个规模更小的子问题
3. 递归调用的父问题和尝试解决的子问题之间不应该有交集。
 */
public class Ex_1_1_16 {
    public static void main(String[] args){
        System.out.println(exR1(6));//311361142246

    }
    public static String exR1(int n){
        if (n <= 0 ) return "";
        return exR1(n-3) + n + exR1(n-2) +n;
    }
}
