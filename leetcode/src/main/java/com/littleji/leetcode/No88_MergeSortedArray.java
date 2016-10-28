package com.littleji.leetcode;

/**
 * Created by Jimmy on 2016/10/27.
 */
public class No88_MergeSortedArray {
    public void merge(int[] nums1, int n, int[] nums2, int m) {
        if( m == 0)
            return;
        else if (n == 0){
            for (int i = 0; i<m ; i++){
                nums1[i] = nums2[i];
            }
            nums1 = nums2.clone();
            return;
        }
        int[] nums=new int[n+m];
        int idx1=0, idx2=0;
        while(idx1 < n ){
            if (nums1[idx1] > nums2[0]){
                int temp = nums1[idx1];
                nums1[idx1] = nums2[idx2];
                nums2[idx2] = temp;
                for (int i = 0; i+1 < m; i++){
                    if (nums2[i+1] < nums2[i]){
                        int tmp = nums1[i];
                        nums1[i] = nums1[i+1];
                        nums1[i+1] = tmp;
                    }
                }
                idx1++;
            }
            else{
                idx1++;
            }
        }
        while(idx2 < m){
            nums1[idx1] = nums2[idx2];
            idx2++;
        }
    }
}

