package com.broctagon.marketserver.model;

import java.time.LocalDateTime;

public class DayMarket {
	
	private double open;
	
	private double high;
	
	private double low;
	
	private double current;

	private double volume;

	private LocalDateTime time;
	
	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "DayMarket [open=" + open + ", high=" + high + ", low=" + low + ", volume=" + volume + ", time=" + time
				+ "]";
	}
	
}
