package com.littleji.algo4th.ch1;

import javax.swing.text.StyledEditorKit;

public class Ex_1_1_11 {

    public static void main(String[] args) {
        boolean[][] booleanArray= {
            {true, false, false, true},
            {false, true, false},
            {false, false, true},
        };
        int max_col = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            if( booleanArray[i].length > max_col ){
                max_col = booleanArray[i].length;
            }
            System.out.print((i+1));
            for (int j = 0; j < booleanArray[i].length; j++) {
                if(booleanArray[i][j]){
                    System.out.print("* ");
                }
                else{
                    System.out.print("  ");
                }              
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < max_col ; i++) {
            System.out.print((i+1)+" ");
            
        }
    }

}