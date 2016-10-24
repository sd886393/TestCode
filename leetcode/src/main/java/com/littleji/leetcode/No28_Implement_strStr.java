package com.littleji.leetcode;

import java.util.HashMap;

/**
 * Created by Jimmy on 2016/10/21.
 */
public class No28_Implement_strStr {
    public int strStr(String text, String pattern) {
        int result = 0;
        if (pattern == null || text == null) return 0;
        if (pattern.equals("")) return 0;   // 如果模式串为空，认为第0位已经匹配
        int tlen = text.length(), plen = pattern.length();
        if (plen > tlen) return -1;
        HashMap <Character,Integer> map= new HashMap(plen + 1);
        for (int i =0; i<plen; i++) map.put(pattern.charAt(i),i);
        int ptext = 0, pnow = 0, ppattern=0;
        while( ptext < tlen ){
            if (ptext != 0){
                pnow += plen - map.getOrDefault(text.charAt(ptext),0);
            }
            if(pnow + plen -1 >= tlen)
                return -1;
            for (int i = pnow ; i< plen + pnow;++i,++ppattern){
               if (text.charAt(i) != pattern.charAt(ppattern))
                   break;
            }
            if (ppattern == plen)
                return pnow;
            else{
                ptext = pnow + plen;
                ppattern=0;
            }
        }
        return -1;
    }
}
