package com.littleji.leetcode;

import com.littleji.leetcode.utils.TreeNode;
import org.junit.Test;

/**
 * Created by Jimmy on 2016/11/7.
 */
public class TestNo98 {
    @Test
    public void assert1(){
        No98_ValidateBinarySearchTree a = new No98_ValidateBinarySearchTree();
        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        a.isValidBST(t2);
    }
}
