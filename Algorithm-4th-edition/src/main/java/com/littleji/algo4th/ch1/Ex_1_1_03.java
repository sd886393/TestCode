package com.littleji.algo4th.ch1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class Ex_1_1_03 {
    public static void main(String[] args) {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/ex_1_1_03_input.txt");
            System.setIn(fis);
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter(" ");
            int[] input = new int[3];
            for(int i = 0; sc.hasNextInt() && i < 3; i++ ){
                input[i] = sc.nextInt();
            }
            if(input.length != 3){
                System.exit(-1);
            }
            for(int i=0; i<input.length; i++){
                StdOut.println(input[i]);
            }
  
            if((input[1] == input[0]) && (input[1] == input[0])){
                StdOut.println("正确");
            }else{
                StdOut.println("错误");
            }


            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }


    
}