package com.littleji.leetcode;

import java.util.Set;

/**
 * Created by Jimmy on 2016/11/8.
 */
public class No127_WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int [] bindex = findWord(beginWord, wordList);
        int [] eindex = findWord(endWord, wordList);
        if (bindex[0] == -1)
            return 0;
        else if (bindex[0] == eindex[0]){
            return 2+bindex[1];
        }
        else if ( bindex[1] == 0 && bindex[1] == eindex[1] ){
            return 0;
        }
        else {
            return Math.abs(bindex[0] - eindex[0]) + bindex[1] + eindex[1] + 1;
        }
    }
    public int[] findWord(String word, Set<String> list){
        if (list == null || word == null){
            return new int []{-1,0};
        }
        int trans = 0;
        int wordlen = word.length();
        int j = 0;
        for(String each : list){
            if (each.length() != wordlen) {
                j++;
                continue;
            }
            for (int i = 0; i < each.length() && trans < 2; i++) {
                if (word.charAt(i) == each.charAt(i))
                    continue;
                else{
                    trans++;
                }
            }
            if (trans < 2)
                return new int [] {j,trans};
            trans=0;
            j++;
        }
        return new int []{-1,0};
    }
}
