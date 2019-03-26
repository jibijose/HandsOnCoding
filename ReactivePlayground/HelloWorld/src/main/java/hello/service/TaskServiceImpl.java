package hello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Async
    @Override
    public Future<Void> processAsync(String data) {
        LOGGER.info("processing start!!!");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        LOGGER.info("processing done!!!");
        return new AsyncResult<>(null);
    }

    @Override
    public String processSync(String data) {
        LOGGER.info("processing start!!!");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        LOGGER.info("processing done!!!");
        return "data_processed";
    }
}