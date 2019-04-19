package com.littleji.algo4th.ch1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

public class BinarySearch {

    public static void generateRandom(int total) {
        Out out = new Out("test.txt");
        for (int i = 0; i < total; i++) {
            out.print((int)(Math.random() * 100)+" ");
        }
        out.close();
    }

    public static boolean have(int[] where, int what) {
        int hi = where.length;
        int lo = 0;
        int mi ;
        while (lo < hi) {
            mi = (lo + hi) / 2;
            if (where[mi] < what) {
                lo = mi + 1;
                continue;
            }
            if (what < where[mi]) {
                hi = mi - 1;
                continue;
            }
            return true;
        }
        return false;
    }

    //mvn exec:java  -D"exec.mainClass"="com.littleji.algo4th.ch1.BinarySearch" -D"exec.args"="white.txt test.txt"
    public static void main(String[] args)  {
        String[] args1 = {"", ""};
        args1[0] = "white.txt";
        args1[1] = "test.txt";
        
        generateRandom(100);
        int[] whitelist = In.readInts(args1[0]);
        FileInputStream fis;
        Scanner sc;
		try {
            fis = new FileInputStream(args1[1]);
            System.setIn(fis);
            sc = new Scanner(System.in);
            Arrays.sort(whitelist);
            int tmp;
            sc.useDelimiter(" ");
            while(sc.hasNextInt() ){
                tmp = sc.nextInt();
                if(have(whitelist, tmp)) StdOut.println(tmp);
            }
            sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}