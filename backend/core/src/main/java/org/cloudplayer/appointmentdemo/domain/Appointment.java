
package org.cloudplayer.appointmentdemo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Appointment {
	private int round;
	private String name;
	@JsonProperty("idNumber")
	private String idNumber;
	private String phone;
	private int amount;
	private String serial;

	public Appointment() {
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
