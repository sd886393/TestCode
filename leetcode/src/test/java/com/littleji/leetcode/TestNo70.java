package com.littleji.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/10/25.
 */
public class TestNo70 {
    @Test
    public void Assert1(){
        No70_ClimbingStairs a = new No70_ClimbingStairs();
        Assert.assertEquals(2, a.climbStairs(4));
    }
}
