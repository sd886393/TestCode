package com.littleji.leetcode;

/**
 * Created by Jimmy on 2016/10/14.
 */
public class No8_StringToInteger {


    public static  int myAtoi(String str){
        final int INT_MAX  = 2147483647;
        final int INT_MIN  =-2147483647;
        //Remove leadiing or trailing zeros
        int begin = 0;
        int end = 0;
        String noZeros="";
        int negative = 0;
        int fraction = 0;

        for ( int i=0 ; i < str.length() ; i++){
            if (str.charAt(i) == '-'){
                noZeros += "-";
                negative = 1;
            }
            else if ('.'==str.charAt(i) && i>0){
                begin = i-1;
                fraction = 1;
                break;
            }
            else if( '0'< str.charAt(i) && str.charAt(i) <= '9') {
                begin = i;
                break;
            }
            if( i == str.length()-1){
                return 0;
            }

        }
        //分数的话去除后面的0
        if (fraction == 1){
            for ( int i=begin ; i < str.length() ; i++){
                if( '0'< str.charAt(i) && str.charAt(i) <= '9') {
                    end = i;
                }
            }
            if (end == 0 )
                end = begin;
        }

        //判断是否为负数
        if (negative == 1){
            String tmp = String.copyValueOf(str.toCharArray(), begin, end-begin+1);
            noZeros += tmp;
        }
        else{
            noZeros = String.copyValueOf(str.toCharArray(), begin, end-begin+1);
        }

        //判断是否为分数
        if (fraction == 1){

        }

        System.out.println(noZeros);
        System.out.println(begin);
        System.out.println(end);
        return 1;
    }
    public static void main(String [] args){

        No8_StringToInteger a = new No8_StringToInteger();
        String target = "-10.00";
        int result = a.myAtoi(target);
        //System.out.println(result);
    }
}
