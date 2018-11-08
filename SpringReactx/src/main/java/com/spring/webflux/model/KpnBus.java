package com.spring.webflux.model;

import java.io.Serializable;

public class KpnBus implements Serializable {

	private static final long serialVersionUID = 8544531055259295666L;

	private String status;
	private String type;
	private String departure;
	private String arrival;
	private String duration;
	private String available;
	private String fare;

	public KpnBus() {

	}

	public KpnBus(String type, String departure, String arrival, String duration, String available, String fare) {
		this.type = type;
		this.departure = departure;
		this.arrival = arrival;
		this.duration = duration;
		this.available = available;
		this.fare = fare;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "KpnBus [status=" + status + ", type=" + type + ", departure=" + departure + ", arrival=" + arrival
				+ ", duration=" + duration + ", available=" + available + ", fare=" + fare + "]";
	}

}
