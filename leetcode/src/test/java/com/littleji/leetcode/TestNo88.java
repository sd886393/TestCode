package com.littleji.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/10/28.
 */
public class TestNo88 {
    @Test
    public void assert1(){
        No88_MergeSortedArray a = new No88_MergeSortedArray();
        int []nums1 = new int[]{1,2,4,5,6,0};
        int []nums2 = new int[]{3};
        a.merge(nums1,5,nums2,1);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6},nums1 );
    }

    @Test
    public void assert2(){
        No88_MergeSortedArray a = new No88_MergeSortedArray();
        int []nums1 = new int[]{1};
        int []nums2 = new int[]{0};
        a.merge(nums1,1,nums2,0);
        Assert.assertArrayEquals(new int[]{1},nums1 );
    }
    @Test
    public void assert3(){
        No88_MergeSortedArray a = new No88_MergeSortedArray();
        int []nums1 = new int[]{0};
        int []nums2 = new int[]{1};
        a.merge(nums1,0,nums2,1);
        Assert.assertArrayEquals(new int[]{1},nums1 );
    }

}
