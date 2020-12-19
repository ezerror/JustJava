package me.ezerror.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：师源
 * @date ：Created in 2020/12/3 11:18
 * @description：
 * @modified By：
 * @version:
 */
public class SeasonUtil {
    public static Map<String, Date> getQuarterStartEndTime(int quarter) {
        Map<String, Date> result = new HashMap<>();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            if (quarter == 1) {
                start.set(Calendar.MONTH, 0);
                start.set(Calendar.DATE, 1);
                end.set(Calendar.MONTH, 2);
                end.set(Calendar.DATE, 31);
            }
            else if (quarter == 2) {
                start.set(Calendar.MONTH, 3);
                start.set(Calendar.DATE, 1);
                end.set(Calendar.MONTH, 5);
                end.set(Calendar.DATE, 30);
            }
            else if (quarter == 3) {
                start.set(Calendar.MONTH, 6);
                start.set(Calendar.DATE, 1);
                end.set(Calendar.MONTH, 8);
                end.set(Calendar.DATE, 30);
            }
            else if (quarter == 4) {
                start.set(Calendar.MONTH, 9);
                start.set(Calendar.DATE, 1);
                end.set(Calendar.MONTH, 11);
                end.set(Calendar.DATE, 31);
            }
            result.put("startTime", DateUtil.getBeginOfDate(start.getTime()));
            result.put("endTime", DateUtil.getEndOfDate(end.getTime()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据时间获取月份
     */
    private static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取季度
     *
     * @param date
     * @return
     */
    private static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    public static void main(String[] args) {
        Map<String, Date> quarterStartEndTime = getQuarterStartEndTime(getSeason(new Date()));
        System.out.println(quarterStartEndTime);
    }
}
