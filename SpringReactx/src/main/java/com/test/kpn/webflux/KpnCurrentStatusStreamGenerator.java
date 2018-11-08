package com.test.kpn.webflux;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.test.kpn.model.KpnStatus;

import reactor.core.publisher.Flux;

@Component
public class KpnCurrentStatusStreamGenerator implements KpnCurrentStatusStream {

	private static final Logger LOGGER = LoggerFactory.getLogger(KpnCurrentStatusStreamGenerator.class);

	@Value("${travel.from.place}")
	private String travelFromPlace;

	@Value("${travel.to.place}")
	private String travelToPlace;

	@Value("${travel.date.formatted}")
	private String travelDateFormatted;

	@Autowired
	private BlockingQueue<KpnStatus> kpnStatusStreamQueue;

	@Override
	public Flux<KpnStatus> fetchStatus() {
		return Flux.<KpnStatus>create(fluxSink -> {
			while (!fluxSink.isCancelled()) {
				try {
					fluxSink.next(kpnStatusStreamQueue.take());
				} catch (InterruptedException interruptedException) {
					LOGGER.warn("interrupted", interruptedException);
					throw new RuntimeException("Interrupted", interruptedException);
				}
			}
		}).share();
	}

}
