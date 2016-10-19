package com.littleji.leetcode;

import java.util.List;

/**
 * Created by Jimmy on 2016/10/19.
 */
public class No21_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //Determine whether the input is legal or not
        if (l1 == null ){
            return l2;
        }
        else if (l2 == null)
            return l1 ;
        ListNode h = new ListNode(0);
        ListNode r = h;
        while(l1 != null && l2 != null){
            if (l1.val < l2.val){
                r.next = l1;
                l1 = l1.next;
            }
            else{
                r.next = l2;
                l2 = l2.next;
            }
            r = r.next;
        }
        if (l1 == null){
            r.next = l2;
        }
        else
            r.next = l1;
        return h.next;
    }
}
