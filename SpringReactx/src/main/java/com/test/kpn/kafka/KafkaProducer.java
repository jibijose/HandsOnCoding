package com.test.kpn.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.test.kpn.model.KpnStatus;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, KpnStatus> kafkaTemplate;

	public void sendKpnStatus(KpnStatus kpnStatus) {
		kafkaTemplate.send("test-topic", kpnStatus);
	}
}
