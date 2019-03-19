package com.broctagon.marketserver.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

	public static LocalDateTime getCurrentDateTime(long timestamp){
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));
		return dateTime.withNano(0);
	}
	
	public static LocalDateTime getDay(String currentTime) {
		LocalDateTime currentDateTime = LocalDateTime.parse(currentTime.trim(), formatter);
		return currentDateTime.withHour(0).withMinute(0).withSecond(0);
	}
	
	public static long getNextDay(LocalDateTime currentTime) {
		LocalDateTime nextDay = currentTime.plusDays(1);
		return nextDay.toEpochSecond(ZoneOffset.of("+8"));
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
		return marketTime.toEpochSecond(ZoneOffset.of("+8"));
	}

	/**
	 * 单位月
	 * .withDayOfMonth(1)  取本月第一天
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
	 * 单位分
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
		LocalDateTime dateTime = currentDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusDays(7 * count).withHour(0).withMinute(0).withSecond(0);
		return dateTime;
	}

	public static long getNextTimeByTimeframe(String currentTime, String timeframe) {
		LocalDateTime marketTime = null;
		LocalDateTime currentDateTime = getCurrentDateTime(Long.parseLong(currentTime));
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
		return marketTime.toEpochSecond(ZoneOffset.of("+8"));
	}
	

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2018, 9, 4);
		System.out.println(date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));//2017-08-15,如果当前时间刚好是星期一,那么就返回当前时间
	}
}
