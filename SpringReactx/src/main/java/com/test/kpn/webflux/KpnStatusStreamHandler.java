package com.test.kpn.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.test.kpn.model.KpnStatus;

import reactor.core.publisher.Mono;

@Component
public class KpnStatusStreamHandler {

	@Autowired
	KpnCurrentStatusStream kpnCurrentStatusStream;

	public Mono<ServerResponse> statusUpdated(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(kpnCurrentStatusStream.fetchStatus(), KpnStatus.class);

	}

}
