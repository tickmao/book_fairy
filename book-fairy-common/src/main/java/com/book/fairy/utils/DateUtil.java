package com.book.fairy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    /** 日期格式yyyy-MM-dd字符串常量 */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
            "yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
            "yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat(
            "yyyyMMddHHmmss");


    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }


    /**
     * 获取YYYY-MM格式
     *
     * @return
     */
    public static String getMonth() {
        return sdfMonth.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return
     */
    public static String getTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s, String pattern) {

        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        DateFormat fmt = new SimpleDateFormat(pattern);
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long aa = 0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }


    /**
     * 在指定日期增加天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    public static Date addMins(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }

    public static Date addSeconds(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, amount);
        return c.getTime();
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days, String pattern) {

        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat(pattern);
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到指定时间n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDates(String days, Integer nums, String pattern) {

        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdfd = new SimpleDateFormat(pattern);
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
        Date date = sdfd.parse(days, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, nums);
        Date date1 = calendar.getTime();
        String dateStr = sdfd.format(date1);
        return dateStr;
    }

    public static int getDayWeek(String day) throws ParseException {
        int i = 0;
        Date date = fomatDate(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        i = calendar.get(Calendar.DAY_OF_WEEK);
        return i;
    }

    //l两个时间段像个的年月
    public static List<String> getMonthBetween(String minDate, String maxDate, String pattern) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();

        if (pattern == null) {
            pattern = "yyyy-MM";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    //l两个时间段像个的年月
    public static List<String> getDateBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DATE));

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DATE));
        Long startTIme = min.getTimeInMillis();
        Long endTime = max.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;

        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat(pattern);
            System.out.println(df.format(d));
            result.add(df.format(d));
            time += oneDay;
        }

        System.out.println(result.toString());
        return result;
    }

    //获取指定月份的天数
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }


    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 昨天的开始时间
     *
     * @return
     */
    public static long startOfyesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 昨天的结束时间
     *
     * @return
     */
    public static long endOfyesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 某天的开始时间
     *
     * @param dayUntilNow 距今多少天以前
     * @return 时间戳
     */
    public static long startOfSomeDay(int dayUntilNow) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -dayUntilNow);
        Date date = calendar.getTime();
        return date.getTime() / 1000;
    }

    /**
     * 某天的年月日
     *
     * @param dayUntilNow 距今多少天以前
     * @return 年月日map  key为  year month day
     */
    public static Map<String, Object> getYearMonthAndDay(int dayUntilNow) {
        Map<String, Object> map = new HashMap<String, Object>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -dayUntilNow);
        map.put("year", calendar.get(Calendar.YEAR));
        map.put("month", calendar.get(Calendar.MONTH) + 1);
        map.put("day", calendar.get(Calendar.DAY_OF_MONTH));
        return map;
    }

    /**
     * 将一个字符串转换成日期格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date toDate(String date, String pattern) {
        if (("" + date).equals("")) {
            return null;
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newDate;
    }

    public static String toDates(String date) {
        String dateString = "";
        if (("" + date).equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        try {
            newDate = sdf.parse(date);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dateString = toString(newDate,"yyyy-M-d");
        return dateString;
    }

    /**
     * 把日期转换成字符串型
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String toString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        String dateString = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateString = sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateString;
    }

    public static String toString(Long time, String pattern) {
        if (time > 0) {
            if (time.toString().length() == 10) {
                time = time * 1000;
            }
            Date date = new Date(time);
            String str = DateUtil.toString(date, pattern);
            return str;
        }
        return "";
    }


    /**
     * 获取上个月的开始结束时间
     *
     * @return
     */
    public static Long[] getLastMonth() {
        // 取得系统当前时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        // 取得系统当前时间所在月第一天时间对象
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // 日期减一,取得上月最后一天时间对象
        cal.add(Calendar.DAY_OF_MONTH, -1);

        // 输出上月最后一天日期
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String months = "";
        String days = "";

        if (month > 1) {
            month--;
        } else {
            year--;
            month = 12;
        }
        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(day).length() > 1)) {
            days = "0" + day;
        } else {
            days = String.valueOf(day);
        }
        String firstDay = "" + year + "-" + months + "-01";
        String lastDay = "" + year + "-" + months + "-" + days;

        Long[] lastMonth = new Long[2];
        lastMonth[0] = DateUtil.getDateline(firstDay);
        lastMonth[1] = DateUtil.getDateline(lastDay);

        //  //System.out.println(lastMonth[0] + "||" + lastMonth[1]);
        return lastMonth;
    }


    /**
     * 获取当月的开始结束时间
     *
     * @return
     */
    public static Long[] getCurrentMonth() {
        // 取得系统当前时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        // 输出下月第一天日期
        int notMonth = cal.get(Calendar.MONTH) + 2;
        // 取得系统当前时间所在月第一天时间对象
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // 日期减一,取得上月最后一天时间对象
        cal.add(Calendar.DAY_OF_MONTH, -1);


        String months = "";
        String nextMonths = "";


        if (!(String.valueOf(month).length() > 1)) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        if (!(String.valueOf(notMonth).length() > 1)) {
            nextMonths = "0" + notMonth;
        } else {
            nextMonths = String.valueOf(notMonth);
        }
        String firstDay = "" + year + "-" + months + "-01";
        String lastDay = "" + year + "-" + nextMonths + "-01";
        Long[] currentMonth = new Long[2];
        currentMonth[0] = DateUtil.getDateline(firstDay);
        currentMonth[1] = DateUtil.getDateline(lastDay);

        //  //System.out.println(lastMonth[0] + "||" + lastMonth[1]);
        return currentMonth;
    }


    public static long getDateline() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getDateline(String date) {
        return (long) (toDate(date, "yyyy-MM-dd").getTime() / 1000);
    }

    //精确到时分秒
    public static long getTimeline(String time) {
        return (long) (toDate(time, "yyyy-MM-dd HH:mm:ss").getTime() / 1000);
    }

    public static long getDateHaveHour(String date) {
        return (long) (toDate(date, "yyyy-MM-dd HH").getTime() / 1000);
    }

    public static long getDateline(String date, String pattern) {
        return (long) (toDate(date, pattern).getTime() / 1000);
    }

    /**
     * 两个日期相差几个月
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalMonth(Date start, Date end) {
        if (start.after(end)) {
            return 0;
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);

        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        int date = endCalendar.get(Calendar.DATE) - startCalendar.get(Calendar.DATE);

        int intervalMonth = 0;
        if (year > 0) {
            intervalMonth += year * 12;
        }
        if (month > 0) {
            intervalMonth += month;
        }
        if (date < 0) {
            intervalMonth -= 1;
        }

        return intervalMonth;
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 两个日期相差几天
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDate(Date start, Date end) {
        if (start.after(end)) {
            return 0;
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        //设置时间为0时
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (endCalendar.getTime().getTime() / 1000) - (int) (startCalendar.getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    public static String formatToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String formatToString(Date date) {
        return sdfTime.format(date);
    }


    public static String getPreMonth(String repeatDate, String pattern) {

        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern); //制定日期格式
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.MONTH, 1); //将当前日期加一个月
        String validityDate = df.format(c.getTime());  //返回String型的时间
        return validityDate;
    }

    public static long getDaysBetween(Date fromDate,Date toDate){
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000l * 60 * 60 * 24);
    }

    public static int getMonthsBetween(Date date1, Date date2){
        int iMonth = 0;
        int flag = 0;
        try{
            Calendar fromCalendar1 = Calendar.getInstance();
            fromCalendar1.setTime(date1);

            Calendar fromCalendar2 = Calendar.getInstance();
            fromCalendar2.setTime(date2);

            if (fromCalendar2.equals(fromCalendar1))
                return 0;
            if (fromCalendar1.after(fromCalendar2)){
                Calendar temp = fromCalendar1;
                fromCalendar1 = fromCalendar2;
                fromCalendar2 = temp;
            }
            if (fromCalendar2.get(Calendar.DAY_OF_MONTH) < fromCalendar1.get(Calendar.DAY_OF_MONTH))
                flag = 1;

            if (fromCalendar2.get(Calendar.YEAR) > fromCalendar1.get(Calendar.YEAR))
                iMonth = ((fromCalendar2.get(Calendar.YEAR) - fromCalendar1.get(Calendar.YEAR))
                        * 12 + fromCalendar2.get(Calendar.MONTH) - flag)
                        - fromCalendar1.get(Calendar.MONTH);
            else
                iMonth = fromCalendar2.get(Calendar.MONTH)
                        - fromCalendar1.get(Calendar.MONTH) - flag;

        } catch (Exception e){
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 是否只有日期数据
     *
     * @param date
     * @return
     */
    public static boolean onlyDateData(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        return h==0 && m==0 && s==0;
    }



    /**
     * 设置 时，分,秒
     *
     * @param date
     * @param h
     * @param m
     * @param s
     */
    public static Date setHms(Date date, int h, int m, int s) {
        if(date==null) return null;
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,h);
        c.set(Calendar.MINUTE,m);
        c.set(Calendar.SECOND,s);
        return c.getTime();
    }

    /**
     * 根据指定的日期表达式解析
     *
     * @param dateStr
     * @param datePattern
     * @return
     */
    public static Date parseDate(String dateStr,String datePattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Could not parse date: " + dateStr,e);
        }
    }

    public static Date autoParseDate(String dateStr){
        String datePattern = null;
        if(dateStr.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
            datePattern = "yyyy-MM-dd";
        }else if(dateStr.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}")){
            datePattern = "yyyy-MM-dd HH:mm";
        }else if(dateStr.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")){
            datePattern = "yyyy-MM-dd HH:mm:ss";
        }else{
            throw new IllegalArgumentException("Could not parse date:" + dateStr +", date format is error ");
        }

        return parseDate(dateStr,datePattern);
    }

    public static void main(String[] args) throws ParseException {

//        System.out.println(addDays(new Date(), 1));
//
//        Date as = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
//        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = matter1.format(as);
//        System.out.println(time);
        Date dates = fomatDate("2017-10-10 12:12:11");
        System.out.println(formatToString(dates));
//    	System.out.println(getDays());
//    	System.out.println(getAfterDayWeek("3"));
        //起止日期
//        List<String> list = new ArrayList<String>();
//        Calendar cal = Calendar.getInstance();
//        int year =2017;
//        int month =1;
//        int dayNumOfMonth = getDaysByYearMonth(year, month);
//        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
//        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
//            Date d = cal.getTime();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String df = simpleDateFormat.format(d);
//            list.add(df);
//
//        }
//        System.out.println(  list);

//        Date as = new Date(new Date().getTime()-24*60*60*1000);
//        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
//        String time = matter1.format(as);
//        System.out.println(time);

//        // 获取当月第一天和最后一天
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String firstday, lastday;
//        Calendar cale = null;
//        // 获取前月的第一天
//        cale = Calendar.getInstance();
//        cale.set(Calendar.YEAR, 2012);
//        cale.set(Calendar.MONTH, 6);
//        cale.add(Calendar.MONTH, 0);
//        cale.set(Calendar.DAY_OF_MONTH, 1);
//        firstday = format.format(cale.getTime());
//        // 获取前月的最后一天
//        cale = Calendar.getInstance();
//        cale.set(Calendar.YEAR, 2012);
//        cale.set(Calendar.MONTH, 6);
//        cale.add(Calendar.MONTH, 1);
//        cale.set(Calendar.DAY_OF_MONTH, 0);
//        lastday = format.format(cale.getTime());
//        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
//			List<String> results =getMonthBetween(startDate,endDate);
//		for (String res:results){
//			System.out.println(res);
//		}

    }

}
