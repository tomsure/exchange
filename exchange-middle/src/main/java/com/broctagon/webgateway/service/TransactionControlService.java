package com.broctagon.webgateway.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.constant.Path;
import com.broctagon.webgateway.util.DateUtil;
import com.broctagon.webgateway.util.RedisUtil;


@Service
public class TransactionControlService {
	
	@Autowired
	private RedisUtil RedisUtil;
	
	@Autowired
	private SimpMessageSendingOperations messageSender;
	
	
	
	public boolean JudgmenTime(String message, String sessionId) {
		String time = DateUtil.getDateFormat(new Date());
		int date = DateUtil.dayForWeek(time);
		if(date == 6 || date == 7) {
			messageSender.convertAndSend(Path.PREFIX + Path.WRANG_TRADETIME + sessionId, "{\"status\":-1}");
		}else {
			Date newtime = DateUtil.getDateFormats(new Date());
			String MorningStart = RedisUtil.hget(Constant.TRADINGTIME, Constant.MORNINGSTART).toString();
			String MorningEnd = RedisUtil.hget(Constant.TRADINGTIME, Constant.MORNINGEND).toString();
			String AfternoonStart = RedisUtil.hget(Constant.TRADINGTIME, Constant.AFTERNOONSTART).toString();
			String AfternoonEnd = RedisUtil.hget(Constant.TRADINGTIME, Constant.AFTERNOONEND).toString();
			if(DateUtil.isEffectiveDate(newtime, DateUtil.TimeConversion(MorningStart), DateUtil.TimeConversion(MorningEnd)) ||
					DateUtil.isEffectiveDate(newtime, DateUtil.TimeConversion(AfternoonStart), DateUtil.TimeConversion(AfternoonEnd))) {
				return true;
			}else {
				messageSender.convertAndSend(Path.PREFIX + Path.WRANG_TRADETIME + sessionId, "{\"status\":-2}");
			}
		}
		return false;
	}

}
