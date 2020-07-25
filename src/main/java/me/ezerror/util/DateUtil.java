package me.ezerror.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final long DAY_IN_MILLISECOND = 86400000L;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取该日期所在月第一天
     *
     * @param date
     * @return
     */
    public static Date getBeginOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return getBeginOfDate(cale.getTime());
    }

    /**
     * 获取该日期所在月最后一天
     *
     * @param date
     * @return
     */
    public static Date getEndOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return getEndOfDate(cale.getTime());
    }

    /**
     * 获取该日期所在年的第一天
     *
     * @return
     */
    public static Date getBeginOfYear(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        return getBeginOfDate(calendar.getTime());
    }

    /**
     * 获取该日期所在年的最后一天
     *
     * @return
     */
    public static Date getEndOfYear(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return getEndOfDate(calendar.getTime());
    }

    public static Date getBeginOfDate(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        currCal.set(Calendar.HOUR_OF_DAY, 0);
        currCal.set(Calendar.MINUTE, 0);
        currCal.set(Calendar.SECOND, 0);
        return currCal.getTime();
    }

    public static Date getEndOfDate(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        currCal.set(Calendar.HOUR_OF_DAY, 23);
        currCal.set(Calendar.MINUTE, 59);
        currCal.set(Calendar.SECOND, 59);
        return currCal.getTime();
    }


    public static String parseDateToString(Date date) {
        return parseDateToString(date, DATE_FORMAT);
    }

    public static Object parseDateToString(Date date, String... formatModes) {
        if (formatModes.length > 1) {
            List<String> timeList = new ArrayList<>();
            for (String formatMode : formatModes) {
                timeList.add(parseDateToString(date, formatMode));
            }
            return timeList;
        }
        else {
            return parseDateToString(date, formatModes.length == 1 ? formatModes[0] : null);
        }
    }

    private static String parseDateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        else {
            SimpleDateFormat formatter = null;
            try {
                formatter = new SimpleDateFormat(format);
                return formatter.format(date);
            }
            catch (Exception e) {
                formatter = new SimpleDateFormat(DATE_FORMAT);
                return formatter.format(date);
            }
        }
    }
}
