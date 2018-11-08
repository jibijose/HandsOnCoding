package com.spring.webflux.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.spring.webflux.model.KpnStatus;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, KpnStatus> kafkaTemplate;

	public void sendKpnStatus(String name) {
		kafkaTemplate.send("test-topic", new KpnStatus(name));
	}
}
