package hello.controller;

import hello.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
public class AsyncController {

    private static final Logger LOGGER = LogManager.getLogger(AsyncController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping("/async-task")
    public String processAsyncTask() {
        LOGGER.info("Servlet thread used!!!");
        long startTime = System.currentTimeMillis();
        taskService.processAsync("test data...");
        long elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info("Servlet thread released!!!");
        return "elapsedTime = " + elapsedTime;

    }

    @RequestMapping("/sync-callable")
    public Callable<String> processSyncCallable() throws Exception {
        LOGGER.info("Servlet thread used!!!");
        Callable<String> callable = () -> taskService.processSync("test data...");
        callable.call();
        // Servlet thread released
        LOGGER.info("Servlet thread released!!!");

        return callable;
    }

    @RequestMapping("/async-callable")
    public void processAsyncCallable() throws Exception {
        LOGGER.info("Servlet thread used!!!");
        Callable<Future<Void>> callable = () -> taskService.processAsync("test data...");
        callable.call();
        // Servlet thread released
        LOGGER.info("Servlet thread released!!!");
    }

    @RequestMapping("/async-deferred")
    public DeferredResult<String> processAsyncDeferred() {
        LOGGER.info("Servlet thread used!!!");
        // Create the deferredResult and initiate a callback object, task, with it
        DeferredResult<String> deferredResult = new DeferredResult<>();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> taskService.processSync("test data..."))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));

        // Servlet thread released
        LOGGER.info("Servlet thread released!!!");

        return deferredResult;
    }

}
