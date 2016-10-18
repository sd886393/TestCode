package com.littleji.leetcode; /**
 * Created by Jimmy on 2016/10/18.
 */
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

public class TestNo15 {
    @Test
    public void assert1(){
        int [] nums = {0,0,0};
        No15_3Sum a = new No15_3Sum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList row1 = new ArrayList<Integer>(Arrays.asList(0, 0, 0));
        result.add(row1);
        Assert.assertEquals(a.threeSum(nums), result);
    }

    @Test
    public void assert2(){
        int [] nums = {-1,0,1,2,-1,-4};
        No15_3Sum a = new No15_3Sum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList row1 = new ArrayList<Integer>(Arrays.asList(-1,-1,2));
        ArrayList row2 = new ArrayList<Integer>(Arrays.asList(-1,0,1));
        result.add(row1);
        result.add(row2);
        Assert.assertEquals(a.threeSum(nums), result);
    }

    @Test
    public void assert3(){
        int [] nums = {0,0,0,0};
        No15_3Sum a = new No15_3Sum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList row1 = new ArrayList<Integer>(Arrays.asList(0,0,0));
        result.add(row1);
        Assert.assertEquals(a.threeSum(nums), result);
    }

    @Test
    public void assert4(){
        int [] nums = {1,-1,-1,0};
        No15_3Sum a = new No15_3Sum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList row1 = new ArrayList<Integer>(Arrays.asList(-1,0,1));
        result.add(row1);
        Assert.assertEquals(a.threeSum(nums), result);
    }

    @Test
    public void assert5(){
        int [] nums = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        No15_3Sum a = new No15_3Sum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList row1 = new ArrayList<Integer>(Arrays.asList(-5,1,4));
        result.add(row1);
        ArrayList row2 = new ArrayList<Integer>(Arrays.asList(-4,0,4));
        result.add(row2);
        ArrayList row3 = new ArrayList<Integer>(Arrays.asList(-4,1,3));
        result.add(row3);
        ArrayList row4 = new ArrayList<Integer>(Arrays.asList(-2,-2,4));
        result.add(row4);
        ArrayList row5 = new ArrayList<Integer>(Arrays.asList(-2,1,1));
        result.add(row5);
        ArrayList row6 = new ArrayList<Integer>(Arrays.asList(0,0,0));
        result.add(row6);
        Assert.assertEquals(result,a.threeSum(nums));
    }


}
