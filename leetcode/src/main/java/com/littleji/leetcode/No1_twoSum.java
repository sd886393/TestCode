package com.littleji.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jimmy on 2016/10/14.
 */
public class No1_twoSum {
        public int [] twoSum(int [] nums, int target){
            int [] result = new int[2];
            //loop through the numbers above and calculate
            for (int x=0; x<nums.length-1 ;x++){
                for (int y = x+1; y<nums.length; y++){
                    if (nums[x] + nums[y] == target){
                        result[0] = x;//index.get(nums[x]).intValue();
                        result[1] = y;//index.get(nums[y]).intValue();
                        return result;
                    }
                }
            }
            return result;
        }
    public int [] twoSum_map(int [] nums, int target){
        int [] result = new int[2];
        HashMap <Integer, Integer>map =new HashMap<Integer, Integer>();

        for (int x=0; x<nums.length;x++){
            if(map.get(nums[x]) != null){
                result[0]=map.get(nums[x]);
                result[1]=x;
                return  result;
            }
            map.put(target-nums[x], x);
        }
        return result;
    }
        public static void main(String [] args){
            No1_twoSum a = new No1_twoSum();
            int [] b = {-3,4,3,90};
            //int [] b = {0,4,3,0};
            int [] result = a.twoSum_map(b, 0);
        }
}
