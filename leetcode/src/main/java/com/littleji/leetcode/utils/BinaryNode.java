package com.littleji.leetcode.utils;

/**
 * Created by Jimmy on 2016/10/25.
 */
public class BinaryNode<T> {
    T data;
    public  BinaryNode(T data){
        this(data, null, null);
    }
    public BinaryNode(T data, BinaryNode lt, BinaryNode rt ){
        super();
        this.data = data;
        this.left = left;
        this.right = right;
    }
    BinaryNode<T> left;
    BinaryNode<T> right;
}
