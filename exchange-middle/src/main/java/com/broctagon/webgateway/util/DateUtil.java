package com.broctagon.webgateway.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	public static Date TimeConversion(String date) {
		try {
			return new SimpleDateFormat("HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * 
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
	
	
	
	/**  
	    * 判断当前日期是星期几<br>  
	    * <br>  
	    * @param pTime 修要判断的时间<br>  
	    * @return dayForWeek 判断结果<br>  
	    * @Exception 发生异常<br>  
	    */   
	public  static  int  dayForWeek(String pTime){  
	 DateFormat format = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	 Calendar c = Calendar.getInstance();  
	 try {
		c.setTime(format.parse(pTime));
	} catch (ParseException e) {
		e.printStackTrace();
	}  
	 int  dayForWeek = 0 ;  
	 if (c.get(Calendar.DAY_OF_WEEK) == 1 ){  
	  dayForWeek = 7 ;  
	 }else {  
	  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1 ;  
	 }  
	 return  dayForWeek;  
	}
	
	
	public static String getDateFormat(Date date) {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static Date getDateFormats(Date date) {
		DateFormat df=new SimpleDateFormat("HH:mm:ss");
		try {
			return df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String format(LocalDateTime dateTime){
		return dateTime.format(formatter);
	}
	
	public static LocalDateTime getCurrentDateTime(long timestamp){
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
		return dateTime.withNano(0);
	}
	
	public static LocalDateTime getDay(String currentTime) {
		LocalDateTime currentDateTime = LocalDateTime.parse(currentTime.trim(), formatter);
		return currentDateTime.withHour(0).withMinute(0).withSecond(0);
	}
	
	public static long getNextDay(LocalDateTime currentTime) {
		LocalDateTime nextDay = currentTime.plusDays(1);
		return nextDay.toInstant(ZoneOffset.UTC).getEpochSecond();
	}
	
	/**
	 * Get market start time from currentTime, timeframe and count.
	 * count is the number of candles before now based on timeframe.
	 * like count is 5, timeframe is M1 and currentTime is 20180123-11:05:00(2018-01-23 11:05:00)
	 * the method will return 20180123110100(2018-01-23 11:01:00)
	 */
	public static long getTimeByTimeframe(String currentTime, String timeframe, int count) {
		LocalDateTime marketTime = null;
		LocalDateTime currentDateTime = getCurrentDateTime(Long.parseLong(currentTime));
		timeframe = timeframe.toUpperCase();
		if("MN".equals(timeframe)) {
			marketTime = getTimeByMN(currentDateTime, timeframe, count);
		}else if(timeframe.toUpperCase().startsWith("M")) {
			marketTime = getTimeByM(currentDateTime, timeframe, count);
		}else if(timeframe.toUpperCase().startsWith("H")) {
			marketTime = getTimeByH(currentDateTime, timeframe, count);
		}else if(timeframe.toUpperCase().startsWith("D")) {
			marketTime = getTimeByD(currentDateTime, timeframe, count);
		}else if(timeframe.toUpperCase().startsWith("W")){
			marketTime = getTimeByW(currentDateTime, timeframe, count);
		}
		return marketTime.toInstant(ZoneOffset.UTC).getEpochSecond();
	}

	/**
	 * input:                     output:
	 *   time : 20180123-05:04:03
	 *   count : 0
	 *   timeframe : MN           20180101-00:00:00
	 *   
	 * 	 time : 20180123050403
	 *   count : 1
	 *   timeframe : MN           20180120100000
	 */
	public static LocalDateTime getTimeByMN(LocalDateTime currentDateTime, String timeframe, int count) {
		LocalDateTime dateTime = currentDateTime.withDayOfMonth(1).minusMonths(count).withHour(0).withMinute(0).withSecond(0);
		return dateTime;
	}

	/**
	 * input:                     output:
	 *   time : 20180123050403
	 *   count : 0
	 *   timeframe : M1           20180123050400
	 *   
	 * 	 time : 20180123050403
	 *   count : 1
	 *   timeframe : M5           20180123050000
	 *   
	 * 	 time : 20180123050403
	 *   count : 2
	 *   timeframe : M5           20180123045500	 
	 */
	public static LocalDateTime getTimeByM(LocalDateTime currentDateTime, String timeframe, int count) {
		int duration = Integer.parseInt(timeframe.replace("M", ""));
		int minute = currentDateTime.getMinute() / duration * duration;
		LocalDateTime dateTime = currentDateTime.withMinute(minute).minusMinutes(duration * count).withSecond(0);
		return dateTime;
	}
	
	/**
	 * input:                     output:
	 *   time : 20180123050403
	 *   count : 0
	 *   timeframe : H1           20180123050000
	 *   
	 * 	 time : 20180123050403
	 *   count : 1
	 *   timeframe : H4           20180123040000
	 *   
	 * 	 time : 20180123050403
	 *   count : 2
	 *   timeframe : H4           20180123000000	 
	 */
	public static LocalDateTime getTimeByH(LocalDateTime currentDateTime, String timeframe, int count) {
		int duration = Integer.parseInt(timeframe.replace("H", ""));
		int hour = currentDateTime.getHour() / duration * duration;
		LocalDateTime dateTime = currentDateTime.withHour(hour).minusHours(duration * count).withMinute(0).withSecond(0);
		return dateTime;
	}
	
	/**
	 * input:                     output:
	 *   time : 20180123050403
	 *   count : 0
	 *   timeframe : D1           20180123000000
	 *   
	 * 	 time : 20180123050403
	 *   count : 1
	 *   timeframe : D1           20180122000000
	 *   
	 * 	 time : 20180123050403
	 *   count : 2
	 *   timeframe : D2           20180121000000	 
	 */
	public static LocalDateTime getTimeByD(LocalDateTime currentDateTime, String timeframe, int count) {
		int duration = Integer.parseInt(timeframe.replace("D", ""));
		int day = currentDateTime.getDayOfMonth() / duration * duration;
		LocalDateTime dateTime = currentDateTime.withDayOfMonth(day).minusDays(duration * count).withHour(0).withMinute(0).withSecond(0);
		return dateTime;
	}

	/**
	 * input:                     output:
	 *   time : 20180123050403
	 *   count : 0
	 *   timeframe : W1           20180122000000
	 *   
	 * 	 time : 20180123050403
	 *   count : 1
	 *   timeframe : W1           20180115000000
	 *   
	 * 	 time : 20180123050403
	 *   count : 2
	 *   timeframe : W1           20180108000000	 
	 */
	public static LocalDateTime getTimeByW(LocalDateTime currentDateTime, String timeframe, int count) {
		LocalDateTime dateTime = currentDateTime.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusDays(7 * count).withHour(0).withMinute(0).withSecond(0);
		return dateTime;
	}

	public static long getNextTimeByTimeframe(String currentTime, String timeframe) {
		LocalDateTime marketTime = null;
		LocalDateTime currentDateTime = LocalDateTime.parse(currentTime, formatter);
		timeframe = timeframe.toUpperCase();
		if("MN".equals(timeframe)) {
			marketTime = getTimeByMN(currentDateTime, timeframe, -1);
		}else if(timeframe.toUpperCase().startsWith("M")) {
			marketTime = getTimeByM(currentDateTime, timeframe, -1);
		}else if(timeframe.toUpperCase().startsWith("H")) {
			marketTime = getTimeByH(currentDateTime, timeframe, -1);
		}else if(timeframe.toUpperCase().startsWith("D")) {
			marketTime = getTimeByD(currentDateTime, timeframe, -1);
		}else if(timeframe.toUpperCase().startsWith("W")){
			marketTime = getTimeByW(currentDateTime, timeframe, -1);
		}
		return marketTime.toInstant(ZoneOffset.UTC).getEpochSecond();
	}

	public static String getLastTimeByTimeframe(long currentTime, String timeframe, int i) {
		LocalDateTime marketTime = null;
		LocalDateTime currentDateTime = getCurrentDateTime(currentTime);
		timeframe = timeframe.toUpperCase();
		if("MN".equals(timeframe)) {
			marketTime = getTimeByMN(currentDateTime, timeframe, -1).minusMonths(i);
		}else if(timeframe.toUpperCase().startsWith("M")) {
			int duration = Integer.parseInt(timeframe.replace("M", ""));
			marketTime = getTimeByM(currentDateTime, timeframe, -1).minusMinutes(i * duration);
		}else if(timeframe.toUpperCase().startsWith("H")) {
			int duration = Integer.parseInt(timeframe.replace("H", ""));
			marketTime = getTimeByH(currentDateTime, timeframe, -1).minusHours(i * duration);
		}else if(timeframe.toUpperCase().startsWith("D")) {
			marketTime = getTimeByD(currentDateTime, timeframe, -1).minusDays(i);
		}else if(timeframe.toUpperCase().startsWith("W")){
			marketTime = getTimeByW(currentDateTime, timeframe, -1).minusWeeks(i);
		}
		return formatter.format(marketTime);
	}
}
