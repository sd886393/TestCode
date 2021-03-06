package com.littleji.leetcode;

/**
 * Created by Jimmy on 2016/11/7.
 */
public class No125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        s = s.toLowerCase();
        int head=0;
        int end = s.length() - 1 ;
        while (head<s.length()  &&  !isCharacterOrDigit(s.charAt(head))){
            head++;
        }
        while (end>0 && !isCharacterOrDigit(s.charAt(end)) ){
            end--;
        }
        while(head < end) {
            if (s.charAt(head) == s.charAt(end) ){
                head++;
                end--;
                while (head<s.length()  && !isCharacterOrDigit(s.charAt(head))){
                    head++;
                }
                while (end>0 &&!isCharacterOrDigit(s.charAt(end))){
                    end--;
                }
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }
    public boolean isCharacterOrDigit(char a){
        if ((a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z')|| Character.isDigit(a))
            return true;
        else
            return false;
    }
}
