package com.spring.webflux.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.spring.webflux.model.KpnStatus;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "test-topic", groupId = "group_text")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}

	@KafkaListener(topics = "test-topic", groupId = "group_json", containerFactory = "kpnStatusKafkaListenerFactory")
	public void consumeJson(KpnStatus kpnStatus) {
		System.out.println("Consumed JSON Message: " + kpnStatus);
	}

}
