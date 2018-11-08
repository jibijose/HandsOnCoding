package com.test.kpn.webflux;

import com.test.kpn.model.KpnStatus;

import reactor.core.publisher.Flux;

public interface KpnCurrentStatusStream {

	public Flux<KpnStatus> fetchStatus();

}