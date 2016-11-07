package com.littleji.leetcode;

import com.littleji.leetcode.utils.BinaryNode;
import com.littleji.leetcode.utils.TreeNode;

class No98_ValidateBinarySearchTree{
    public boolean isValidBST(TreeNode root) {
       return doBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean doBST(TreeNode root, long upperlimit, long lowerlimit){
        if (root == null)
            return true;
        else if ((Integer)root.val >= upperlimit || (Integer)root.val <= lowerlimit){
                return false;
            }
        else{
            return doBST(root.left, (Integer)root.val, lowerlimit) && doBST(root.right, upperlimit, (Integer)root.val);
        }
    }

}

