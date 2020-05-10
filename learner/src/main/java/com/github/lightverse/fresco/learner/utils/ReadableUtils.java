package com.github.lightverse.fresco.learner.utils;

import java.text.DecimalFormat;

public class ReadableUtils {

    public static String bytesToReadable(int size){
            //获取到的size为：1705230
            int GB = 1024 * 1024 * 1024;//定义GB的计算常量
            int MB = 1024 * 1024;//定义MB的计算常量
            int KB = 1024;//定义KB的计算常量
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            String resultSize = "";
            if (size / GB >= 1) {
                //如果当前Byte的值大于等于1GB
                resultSize = df.format(size / (float) GB) + "G";
            } else if (size / MB >= 1) {
                //如果当前Byte的值大于等于1MB
                resultSize = df.format(size / (float) MB) + "M";
            } else if (size / KB >= 1) {
                //如果当前Byte的值大于等于1KB
                resultSize = df.format(size / (float) KB) + "K";
            } else {
                resultSize = size + "B   ";
            }
            return resultSize;
    }
}
