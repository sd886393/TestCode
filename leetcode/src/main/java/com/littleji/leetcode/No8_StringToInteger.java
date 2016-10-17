package com.littleji.leetcode;

/**
 * Created by Jimmy on 2016/10/14.
 */
public class No8_StringToInteger {


    public static  int myAtof(String str){
        if(str.length()<1)
            return 0;
        final int INT_MAX  = 2147483647;
        final int INT_MIN  =-2147483648;
        //Remove leadiing or trailing zeros
        int begin = 0;
        int end = 0;
        String noZeros="";
        int negative = 0;
        int fraction = 0;

        float result_int = 0;
        float result_flo = 0;
        int result = 0;

        str =str.trim();
        //判断是否输入正确
        for (int i=0,j=0 ; i<str.length();i++){
           if (str.charAt(i) == '-' ||str.charAt(i) == '+' )
               j++;
            if (j>1)
               return 0;
        }



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
            else if( str.charAt(i)!= '+' && str.charAt(i)!= '-' && str.charAt(i) < '0' || str.charAt(i) > '9') {
                if (i > 0)
                    begin = i-1;
                else
                    begin = 0;
                break;
            }

            if( i == str.length()-1){
                return 0;
            }

        }
        //去除后面的0
            for ( int i=begin ; i < str.length() ; i++){
                if( '0'< str.charAt(i) && str.charAt(i) <= '9'  ) {
                    end = i;
                }
                else if (str.charAt(i) == '.'){
                    fraction = i-begin;
                    end = i - 1;
                }
                if (str.charAt(i)!= '+' && str.charAt(i)!= '-' && str.charAt(i) < '0' || str.charAt(i) > '9') {
                    if (i>0)
                        end = i - 1;
                    else
                        end = 0;
                    break;
                }
            }

        if (fraction == 0 ) {
            end = str.length() - 1;
            for (int i = begin; i<str.length();i++) {
                if (str.charAt(i)!= '+' && str.charAt(i)!= '-' &&str.charAt(i) < '0' || str.charAt(i) > '9') {
                    if (i>0)
                        end = i - 1;
                    else
                        end = 0;
                    break;
                }
            }
        }
        noZeros = String.copyValueOf(str.toCharArray(), begin, end - begin + 1);

        if (end == 0 && str.charAt(0) < '0' || str.charAt(0) > '9')
            return 0;

        //判断是否为分数
        if (fraction > 0){
            for (int i = begin, bit = fraction - begin; i< fraction; i++, bit--){
                result_int += (noZeros.charAt(i)-'0') * Math.pow(10,bit-1);
            }
            for (int i = end, bit = end - fraction; i > fraction; i--, bit--){
                result_flo += (noZeros.charAt(i)-'0') *  Math.pow(0.1,bit);
            }
            if (negative == 1){
                return (int) ((result_flo + result_int) * -1);
            }
            else
                return (int) (result_flo + result_int);

        }
        else{
            if (negative == 1){
                Integer a = (INT_MAX+1);
                if(a.toString().compareTo(str) == 0)
                    return INT_MIN;
            }
            int before = 0;
            for (int i = 0, bit = end-begin+1; i< end-begin+1; i++, bit--){
                result += (int) ((noZeros.charAt(i)-'0') * Math.pow(10,bit-1));
                if (result < before && negative==0)
                    return INT_MAX;
                else if (result < before && negative==1)
                    return INT_MIN;
                before = result;
            }
            if (negative == 1)
                return result*-1;
            else
                return result;
        }

        //


    }
    public static void main(String [] args){

        No8_StringToInteger a = new No8_StringToInteger();
        String target = "abc";

        System.out.println("123".compareTo("123"));
        int result = a.myAtof(target);
        System.out.println(result);
    }
}
