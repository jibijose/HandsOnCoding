package com.spring.webflux.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KpnStatus implements Serializable {

	private static final long serialVersionUID = 5196576033599044769L;

	private String status = "ok";
	private List<KpnBus> kpnBuses = new ArrayList<>();

	public KpnStatus() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KpnBus> getKpnBuses() {
		return kpnBuses;
	}

	public void setKpnBuses(List<KpnBus> kpnBuses) {
		this.kpnBuses = kpnBuses;
	}

	public void addKpnBus(KpnBus kpnBus) {
		kpnBuses.add(kpnBus);
	}

	@Override
	public String toString() {
		return "KpnStatus [status=" + status + "]";
	}
}
