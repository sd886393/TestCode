package com.littleji.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/10/19.
 */
public class TestNo20 {
    @Test
    public void assert1(){
        No20_ValidParentheses a = new No20_ValidParentheses();
        Assert.assertEquals( true,a.isValid("()"));
    }

    @Test
    public void assert2(){
        No20_ValidParentheses a = new No20_ValidParentheses();
        Assert.assertEquals(true,a.isValid("([])"));
    }

    @Test
    public void assert3(){
        No20_ValidParentheses a = new No20_ValidParentheses();
        Assert.assertEquals(false,a.isValid(")}{({))[{{[}"));
    }
}
