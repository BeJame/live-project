
package org.cloudplayer.appointmentdemo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Round {
	private int id;
	private String name;
	private String begin;
	private String end;
	private boolean over;
	private int total;

	public Round() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.begin = simpleDateFormat.format(new Date());
		this.over = false;
		this.total = 10000;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBegin() {
		return this.begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isOver() {
		return this.over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
