package com.littleji.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jimmy on 2016/10/18.
 */
public class No15_3Sum {
    public static List<List<Integer>> threeSum(int[] nums) {

        List <List<Integer>> result= new ArrayList<List<Integer>>();
        if (nums.length <3)
            return result;
        else
            Arrays.sort(nums);

        /* 假设nums中有两组解,下表分别是[a,i,j],[a,x,y]
        *该两组下标所对应的值如果相同的话,则有以下两种情况:
        *1. i>x && j<y
        *2. i<x && j>y
        */
        for (int i =0; i<nums.length; i++){
            int left = i+1, right = nums.length-1;
            while(left < right){
                if(nums[i] + nums[left] + nums[right] == 0) {
                    ArrayList<Integer> row = new ArrayList<Integer>();
                    row.add(nums[i]);
                    row.add(nums[left]);
                    row.add(nums[right]);
                    result.add(row);
                }
                while(left < right){
                    if (nums[left] == nums[left+1])
                        left += 2;
                    else
                        left++;
                    if (nums[right] == nums[right-1])
                        right += 2;
                    else
                        right--;
                }
            }
        }
        return result;
    }
    public static void main(String [] args){
        int [] nums ={-1, 0, 1, 2, -1, -4};
        No15_3Sum a = new No15_3Sum();
        List<List<Integer>> result = No15_3Sum.threeSum(nums);
        System.out.println();
    }
}
