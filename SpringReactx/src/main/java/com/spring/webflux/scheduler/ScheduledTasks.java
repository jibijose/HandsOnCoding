package com.spring.webflux.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.webflux.kafka.KafkaProducer;

@Component
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private KafkaProducer kafkaProducer;

	@Scheduled(initialDelay = 1000, fixedRate = 2000)
	public void pullKpnStatus() {
		kafkaProducer.sendKpnStatus("jibi");
		LOGGER.info("Kpn Status pulled successfully");
	}
}
