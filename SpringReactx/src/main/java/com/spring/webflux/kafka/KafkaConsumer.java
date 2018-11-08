package com.spring.webflux.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.spring.webflux.model.KpnStatus;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "test-topic", groupId = "group_text")
	public void consume(String message) {
		LOGGER.info("Consumed message: {}", message);
	}

	@KafkaListener(topics = "test-topic", groupId = "group_json", containerFactory = "kpnStatusKafkaListenerFactory")
	public void consumeJson(KpnStatus kpnStatus) {
		LOGGER.info("Consumed JSON Message: {}", kpnStatus);
	}

}
