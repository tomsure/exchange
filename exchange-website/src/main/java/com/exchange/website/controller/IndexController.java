package com.exchange.website.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.website.response.AdvisoryResponse;
import com.exchange.website.response.CarouselResponse;
import com.exchange.website.response.MarketResponse;

/**
 * 首页
 * 
 * @author xing.wen
 *
 */
@RestController
@RequestMapping("/website/index")
public class IndexController {
	/**
	 * 轮播
	 * 
	 * @return
	 */
	@GetMapping("/carousel")
	@ResponseBody
	public List<CarouselResponse> carousel() {
		StringBuffer stringbfURL = new StringBuffer();
		stringbfURL.append("http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg");
		CarouselResponse carouselResponse = new CarouselResponse();
		carouselResponse.setUrl(stringbfURL.toString());
		carouselResponse.setTitle("阅读Oxford Metrica最新研究");
		carouselResponse.setContent("在2018年第一季度，我们继续专注于创建有效公共市场的核心使命，满足各种公司和经济自营商的需求，同时为投资者提供更好的信息体验和更高的透明度");
		List<CarouselResponse> responseList = new ArrayList<CarouselResponse>();
		responseList.add(carouselResponse);
		responseList.add(carouselResponse);
		responseList.add(carouselResponse);
		return responseList;
	}

	/**
	 * 諮詢
	 * 
	 * @return
	 */
	@GetMapping("/advisory")
	@ResponseBody
	public List<AdvisoryResponse> advisory() {
		AdvisoryResponse advisoryResponse = new AdvisoryResponse();
		advisoryResponse.setUrl("http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg");
		advisoryResponse.setTitle("阅读Oxford Metrica最新研究");
		advisoryResponse.setAuthor("xing.wen");
		advisoryResponse.setContent("在2018年第一季度，我们继续专注于创建有效公共市场的核心使命，满足各种公司和经济自营商的需求，同时为投资者提供更好的信息体验和更高的透明度");
		advisoryResponse.setRelease_time("2018-09-09");
		List<AdvisoryResponse> advisoryResponseArray = new ArrayList<AdvisoryResponse>();
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		return advisoryResponseArray;
	}

	/**
	 * 新聞諮詢
	 * 
	 * @return
	 */
	@GetMapping("/new/advisory")
	@ResponseBody
	public List<AdvisoryResponse> new_advisory() {
		AdvisoryResponse advisoryResponse = new AdvisoryResponse();
		advisoryResponse.setUrl("http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg");
		advisoryResponse.setTitle("阅读Oxford Metrica最新研究");
		advisoryResponse.setAuthor("xing.wen");
		advisoryResponse.setContent("在2018年第一季度，我们继续专注于创建有效公共市场的核心使命，满足各种公司和经济自营商的需求，同时为投资者提供更好的信息体验和更高的透明度");
		advisoryResponse.setRelease_time("2018-09-09");
		List<AdvisoryResponse> advisoryResponseArray = new ArrayList<AdvisoryResponse>();
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		advisoryResponseArray.add(advisoryResponse);
		return advisoryResponseArray;
	}

	/**
	 * 大盤信息
	 */
	@GetMapping("/market")
	@ResponseBody
	public MarketResponse market() {
		MarketResponse marketResponse = new MarketResponse();
		marketResponse.setDollar_securities("1.5");
		marketResponse.setSum_securities("123123");
		marketResponse.setSum_trade("12392730");
		marketResponse.setSum_vol("12342212");
		return marketResponse;
	}

	/**
	 * 諮詢详情
	 * 
	 * @return
	 */
	@GetMapping("/advisory/info")
	@ResponseBody
	public AdvisoryResponse advisoryInfo(String id) {
		AdvisoryResponse advisoryResponse = new AdvisoryResponse();
		advisoryResponse.setUrl("http://img2.imgtn.bdimg.com/it/u=3588772980,2454248748&fm=27&gp=0.jpg");
		advisoryResponse.setTitle("阅读Oxford Metrica最新研究");
		advisoryResponse.setAuthor("xing.wen");
		advisoryResponse.setContent("在2018年第一季度，我们继续专注于创建有效公共市场的核心使命，满足各种公司和经济自营商的需求，同时为投资者提供更好的信息体验和更高的透明度");
		advisoryResponse.setRelease_time("2018-09-09");
		return advisoryResponse;
	}

}
