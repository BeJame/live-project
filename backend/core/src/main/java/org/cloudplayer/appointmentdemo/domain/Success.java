package org.cloudplayer.appointmentdemo.domain;

public class Success {

	private String id; //身份证
	private String serial; //序列号
	private int round; //轮次

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
