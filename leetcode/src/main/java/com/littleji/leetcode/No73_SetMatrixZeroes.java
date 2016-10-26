package com.littleji.leetcode;

/**
 * Created by Jimmy on 2016/10/26.
 */
public class No73_SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int lrow =matrix.length;
        if( lrow < 1)
            return;
        int lcol = matrix[0].length;
        int []rflag = new int[lrow];
        int []cflag = new int[lcol];

        for (int i = 0; i < lrow; i++){
            for (int j =0; j < lcol; j++){
                if (matrix[i][j] == 0){
                    rflag[i] = 1;
                    cflag[j] = 1;
                }
            }
        }
        for(int i=0; i<rflag.length; i++){
            if (rflag[i] == 1){
                for (int j =0; j<lcol; j++)
                    matrix[i][j] = 0;
            }

        }
        for(int i=0; i<cflag.length; i++){
            if (cflag[i] == 1){
                for (int j =0; j<lrow; j++)
                    matrix[j][i] = 0;
            }
        }
    }
}
