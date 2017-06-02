package models.base;

import org.apache.ivy.util.StringUtils;
import play.db.jpa.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
;

/**
 * Created by hfl
 */
public class DateUtil extends Model {


    /**
     * 为年字段赋值  获得指定时间类型的年（例如： '2015'）
     */
    public static String getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    /**
     * 为月字段赋值 获得指定时间类型的年月（例如： '201504'）
     */
    public static String getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(date);
    }

    /**
     * 为日字段赋值  获得指定时间类型的年月日（例如： ‘20150406’）
     */
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 为周字段赋值  获得指定时间类型的年月周（例如： ‘201504-1’）
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-F");
        return sdf.format(date);
    }

    /**
     * 字符串转化成日期
     *
     * @param sDate         日期字符串
     * @param formatPattern 转化格式
     * @return
     */
    public static Date stringToDate(String sDate, String formatPattern) {
        Date date = null;
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat(formatPattern);
            date = formatDate.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化时间：将string类型的时间转化为 日期类型
     */
    public static Date stringDateAddSeconds(String dateStr, String seconds) {
        Date date = DateUtil.stringToDate(dateStr + ":" + seconds, "yyyy-MM-dd HH:mm:ss");
        return date;
    }

    /**
     * 计算星期几，返回星期几 对应的 数字
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        switch(week){
            case 1:
                return  7;//周日
            case 2:
                return  1;// 周一
            case 3:
                return  2;//周二
            case 4:
                return  3;//周三
            case 5:
                return  4;// 周四
            case 6:
                return  5;//周五
            default:
                return  6;//周六
        }

    }

    /**
     * 获得本周第一天的日期 ： 返回值为日期类型
     */
    public static Date getFirstDayOfWeek(Date date){
        int day = DateUtil.getDayOfWeek(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        calendar.add(calendar.DATE, 1 - day);
        return calendar.getTime();
    }

    /**
     * 获得本周最后一天的日期 ： 返回值为日期类型
     */
    public static Date getEndDayOfWeek(Date date){
        int day = DateUtil.getDayOfWeek(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(calendar.DATE, 7 - day);
        return calendar.getTime();
    }


}


