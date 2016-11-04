package com.littleji.leetcode;

import com.littleji.leetcode.utils.BinaryNode;

class No98_ValidateBinarySearchTree{
    Integer rmin, lmax;
    public boolean isValidBST(BinaryNode root) {
        System.out.println(root.data.getClass());
        if (root.left == null|| root.right == null )
            return false;
        Integer data = (Integer)root.getData();
        boolean result = false;
        do{

        }while(result );

        return true;

    }

    public boolean doBST(BinaryNode l, BinaryNode r){
        if (l == null){
            if (r == null) {
                lmax = (Integer)l.data;
                rmin = (Integer)r.data;
                return true;
            }
            else
                return false;
        }
        else if (r == null)
            return false;
        
    }

    public static void main (String [] args){
        No98_ValidateBinarySearchTree a = new No98_ValidateBinarySearchTree();
        BinaryNode<Integer>b = new BinaryNode<Integer>(1);
        a.isValidBST(b);
    }
}

