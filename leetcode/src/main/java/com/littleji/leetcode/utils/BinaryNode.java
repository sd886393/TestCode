package com.littleji.leetcode.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Jimmy on 2016/10/25.
 */
public class BinaryNode<T> {
    public  T val;
    public  BinaryNode(T data){
        this(data, null, null);
    }
    public BinaryNode(T data, BinaryNode lt, BinaryNode rt ){
        super();
        this.val = data;
        this.left = left;
        this.right = right;
    }
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public T getData(){
        return this.val;
    }
    public T getT() throws InstantiationException, IllegalAccessException {
        Type sType = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
        Class<T> mTClass = (Class<T>) (generics[0]);
        return mTClass.newInstance();
    }
    public T analytic2Object(Class<T> tClass, String str)
                 throws InstantiationException, IllegalAccessException {
                 return tClass.newInstance();
             }
}
