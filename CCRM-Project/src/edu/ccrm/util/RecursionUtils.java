package edu.ccrm.util;

import java.io.File;

public class RecursionUtils {
    public static long totalSize(File f){
        if(f.isFile()) return f.length();
        long sum=0;
        for(File child:f.listFiles()) sum+=totalSize(child);
        return sum;
    }
}