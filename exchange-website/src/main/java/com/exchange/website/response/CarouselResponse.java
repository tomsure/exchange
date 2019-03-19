package com.exchange.website.response;

/**
 * 輪播
 * @author lj
 *
 */
public class CarouselResponse {
	//轮播图
	public String url;
	//标题
	public String title;
	//内容
	public String content;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
