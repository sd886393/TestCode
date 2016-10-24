package com.littleji.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/10/24.
 */
public class TestNo28 {
    @Test
    public void Assert1(){
        No28_Implement_strStr a = new No28_Implement_strStr();
        Assert.assertEquals(4, a.strStr("1234768", "768"));
    }

    @Test
    public void Assert2(){
        No28_Implement_strStr a = new No28_Implement_strStr();
        Assert.assertEquals(0, a.strStr("mississippi", "sippia"));
    }


    @Test
    public void Assert3(){
        No28_Implement_strStr a = new No28_Implement_strStr();
        Assert.assertEquals(6, a.strStr("bbbbababbbaabbba", "abb"));
    }

    @Test
    public void Assert4(){
        No28_Implement_strStr a = new No28_Implement_strStr();
        Assert.assertEquals(0, a.strStr("aaa", "aa"));
    }
}
