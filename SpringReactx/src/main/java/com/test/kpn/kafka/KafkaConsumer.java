package com.test.kpn.kafka;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.test.kpn.model.KpnStatus;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private BlockingQueue<KpnStatus> kpnStatusStreamQueue;

	@KafkaListener(topics = "test-topic", groupId = "group_json", containerFactory = "kpnStatusKafkaListenerFactory")
	public void consumeJson(KpnStatus kpnStatus) {
		try {
			kpnStatusStreamQueue.put(kpnStatus);
			LOGGER.info("Consumed JSON Message: {}", kpnStatus);
		} catch (InterruptedException interruptedException) {
			LOGGER.warn("Interrupted", interruptedException);
		}

	}

}
