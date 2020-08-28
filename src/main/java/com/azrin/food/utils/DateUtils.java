package com.azrin.food.utils;

import com.azrin.food.ExceptionHandler.BadRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public static Date getCurrentDate() throws Exception{
        Date date = null;
        try {
            String dateStr = simpleDateFormat.format(new Date());
            date = simpleDateFormat.parse(dateStr);
        }catch (Exception e){
            throw new BadRequest(ExceptionMessage.DATE_FORMAT_EXCEPTION);
        }
        return date;
    }

    public static Date stringToDate(String dateStr) throws Exception{
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        }catch (Exception e){
            throw new BadRequest(ExceptionMessage.DATE_FORMAT_EXCEPTION);
        }
        return date;
    }

    public static String dateToString(Date date){
        String dateStr = null;
        try {
            dateStr = simpleDateFormat.format(date);
        }catch (Exception e){
            throw new BadRequest(ExceptionMessage.DATE_FORMAT_EXCEPTION);
        }
        return dateStr;
    }
}
