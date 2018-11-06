package com.littleji.algo4th.ch2;

import com.littleji.algo4th.util;

//倒置:指数组中两个顺序颠倒的元素
//部分有序:如果数组中倒置数量小于数组大小的某个倍数,则称其为部分有序的
//典型的几种部分有序数组:
// 1 数组中的各个元素距离它的最终位置都不远;
// 2 一个有序的大数组接一个小数组;
// 3 数组中只有几个元素的位置不正确;
//插入排序需要的交换操作和数组中倒置的数量相同
public class InsertionSort extends SelectionSort {
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
        }
    }

    //测试插入排序与选择排序之间的性能差异
    public static void main(String[] args) {
        Integer[] origin1 = util.random(10000);
        Integer[] origin2 = origin1.clone();
        long start = System.currentTimeMillis();
        for (long x = 0; x < 1; x++) {
            SelectionSort selection = new SelectionSort();
            selection.sort(origin1);
        }
        long end1 = System.currentTimeMillis();
        long seg1 = end1 - start;
        System.out.println(seg1);
        for (long x = 0; x < 1; x++) {
            InsertionSort insertion = new InsertionSort();
            insertion.sort(origin2);
        }
        long end2 = System.currentTimeMillis();
        long seg2 = end2 - end1;
        System.out.println(seg2);
    }
}
