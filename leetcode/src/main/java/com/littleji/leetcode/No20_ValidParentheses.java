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
        Stack<Character> stack = new Stack<Character>();
        ref[(int)')'] = '(';
        ref[(int)']'] = '[';
        ref[(int)'}'] = '{';
        for (int i=0; i<s.length();i++){
            if (s.charAt(i)=='('  || s.charAt(i)=='{' || s.charAt(i)=='['  ){
                stack.push(s.charAt(i));
            }
            else if (s.charAt(i)==')' || s.charAt(i)=='}' ||s.charAt(i)==']'){
                System.out.println(stack.peek());
                if (stack.peek() == ref[s.charAt(i)])
                    stack.pop();
            }
            else
                return false;
        }
        if (stack.empty())
            return true;
        else
            return false;
    }
}
