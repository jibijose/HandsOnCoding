package com.spring.webflux.scheduler;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.webflux.kafka.KafkaProducer;
import com.spring.webflux.model.KpnStatus;
import com.spring.webflux.service.KpnService;

@Component
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private KpnService kpnService;

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void pullKpnStatus() {
		Optional<KpnStatus> optionalKpnStatus = kpnService.fetchKpnStatus();

		if (optionalKpnStatus.isPresent()) {
			KpnStatus kpnStatus = optionalKpnStatus.get();
			kafkaProducer.sendKpnStatus(kpnStatus);
			LOGGER.info("Kpn Status {} pulled successfully", kpnStatus);
		}
	}
}
