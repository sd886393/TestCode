package com.littleji.algo4th.ch2;
import com.littleji.algo4th.util;
class SelectionSort implements Sort {

    public   boolean less (Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
    public  void exch(Comparable [] a, int i, int j){
        Comparable tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public  void sort(Comparable [] a){
        for(int i=0; i< a.length; i++){
            for(int j=i+1; j< a.length; j++){
                if(less(a[j], a[i])){
                    exch(a, i, j);
                }
            }
        }
    }
    //与插入排序对比发现,在使用随机
    public static void main(String[] args) {
        Integer[] origin = util.random(100000);
        long start = System.currentTimeMillis();
        for (long x = 0; x < 1; x++) {
            SelectionSort selection = new SelectionSort();
            selection.sort(origin);
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time);

    }
}
