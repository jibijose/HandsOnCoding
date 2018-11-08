package com.spring.webflux.model;

import java.io.Serializable;

public class KpnStatus implements Serializable {

	private static final long serialVersionUID = 5196576033599044769L;

	private String name;

	public KpnStatus() {
	}

	public KpnStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "KpnStatus [name=" + name + "]";
	}
}
