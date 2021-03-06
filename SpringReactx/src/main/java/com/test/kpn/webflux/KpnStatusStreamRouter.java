package com.test.kpn.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class KpnStatusStreamRouter {

	@Bean
	public RouterFunction<ServerResponse> route(KpnStatusStreamHandler kpnStatusStreamHandler) {
		return RouterFunctions.route(
				RequestPredicates.GET("/kpn/status").and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)),
				kpnStatusStreamHandler::statusUpdated);
	}

}
