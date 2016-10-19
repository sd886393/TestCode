package com.littleji.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/10/19.
 */
public class TestNo21 {
    @Test
    public void assert1(){
        No21_MergeTwoSortedLists a = new No21_MergeTwoSortedLists();
        ListNode a1 = new ListNode(1) ;
        ListNode a2 = new ListNode(3) ;
        ListNode a3 = new ListNode(4) ;
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(0) ;
        Assert.assertEquals(false,a.mergeTwoLists(a1,b1));
    }

    @Test
    public void assert2(){
        No21_MergeTwoSortedLists a = new No21_MergeTwoSortedLists();
        ListNode a1 = new ListNode(2) ;

        ListNode b1 = new ListNode(1) ;

        ListNode r1 = new ListNode(2);
        ListNode r2 = new ListNode(1);
        r1.next = r2;
        Assert.assertEquals(r1,a.mergeTwoLists(a1,b1));
    }
}
