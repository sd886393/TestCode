package com.littleji.leetcode;

import org.junit.Test;

/**
 * Created by Jimmy on 2016/11/7.
 */
public class TestNo125 {
    @Test
    public void assert1(){
        No125_ValidPalindrome a = new No125_ValidPalindrome();
        a.isPalindrome("ab");
    }
    @Test
    public void assert2(){
        No125_ValidPalindrome a = new No125_ValidPalindrome();
        a.isPalindrome("aa");
    }
    @Test
    public void assert3(){
        No125_ValidPalindrome a = new No125_ValidPalindrome();
        a.isPalindrome("aA");
    }
    @Test
    public void assert4(){
        No125_ValidPalindrome a = new No125_ValidPalindrome();
        a.isPalindrome("0P");
    }
}
