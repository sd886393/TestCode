package com.littleji.algo4th.ch2;
class Selection {
    private static boolean less (Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
    public static void exch(Comparable [] a, int i, int j){
        Comparable tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void sort(Comparable [] a){
        for(int i=0; i< a.length; i++){
            for(int j=i+1; j< a.length; j++){
                if(less(a[j], a[i])){
                    exch(a, i, j);
                }
            }
        }
    }
    public static void main(String[] args) {
        Integer[] origin = {5,2,1,6,4,8,9};
        sort(origin);
        for (Integer i : origin) {
            System.out.println(i);
        }

    }
}
