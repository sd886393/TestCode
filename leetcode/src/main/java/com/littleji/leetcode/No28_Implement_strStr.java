package com.littleji.leetcode;

import java.util.HashMap;

/**
 * Created by Jimmy on 2016/10/21.
 */
public class No28_Implement_strStr {
    public int strStr(String text, String pattern) {
        int result = 0;
        if (pattern == null || text == null) return -1;
        if (pattern.equals("")) return 0;   // 如果模式串为空，认为第0位已经匹配
        int tlen = text.length(), plen = pattern.length();
        if (plen > tlen) return -1;
        HashMap map = new HashMap(plen + 1);
        for (int i =0; i<plen; i++) map.put(pattern.charAt(i), i);
        System.out.println(map.toString());
        return -1;
    }
    public static void main(String [] args){
        No28_Implement_strStr a = new No28_Implement_strStr();
        int re = a.strStr("12387683124123","768");
        System.out.println(re);
    }
}
