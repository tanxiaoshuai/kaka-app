package com.sobey.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public final static String YEARTOSS = "yyyy-MM-dd HH:mm:ss";

    public static String longForTime(long s , String mat){
        SimpleDateFormat format = new SimpleDateFormat(mat);
        Date date = new Date(s);
        return format.format(date).toString();
    }
}
