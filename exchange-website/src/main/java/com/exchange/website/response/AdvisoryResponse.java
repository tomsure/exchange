package com.exchange.website.response;

/**
 * 咨询
 * 
 * @author lj
 *
 */
public class AdvisoryResponse {
	//图片地址
	private String url;
	//标题
	private String title;
	//內容
	private String content;
	//發佈者
	private String author;
	//時間
	private String release_time;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRelease_time() {
		return release_time;
	}

	public void setRelease_time(String release_time) {
		this.release_time = release_time;
	}
}
