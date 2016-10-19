package com.littleji.leetcode;

import java.util.Stack;

/**
 * Created by Jimmy on 2016/10/19.
 */
public class No20_ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() == 0 || s.length()%2 != 0)
            return false;
        char [] ref = new char[256];
        ref[(int)'('] = ')';
        ref[(int)'['] = ']';
        ref[(int)'{'] = '}';
/*        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';
        ref[(int)'('] = ')';*/
        for (int i=0;;){
            if (s.charAt(i)=='(' && s.charAt(i+1)==')' ||s.charAt(i)=='{'&& s.charAt(i+1)=='}'||s.charAt(i)=='[' && s.charAt(i+1)==']'){
            }
            else
                return false;
            if (i + 2 >= s.length())
                break;
            else
                i += 2;
        }
        return true;
    }
}
