package com.littleji.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * Created by Jimmy on 2016/11/8.
 */
public class TestNo127 {
    @Test
    public void assert1(){
        No127_WordLadder a = new No127_WordLadder();
        Set<String> list = new LinkedHashSet<String>();
        String [] stringlist = new String[]{"hot", "dot", "dog","lot","log"};
        list.addAll(new ArrayList<String>(Arrays.asList(stringlist)));
        a.ladderLength("hit", "cog", list);
    }
}
