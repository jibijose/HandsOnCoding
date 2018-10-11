package com.test.dsalg.custom.queue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CustomBlockingQueueTest {

	@Test
	public void testPutAndGet() throws Exception {
		CustomBlockingQueue<Integer> blockingQueue = new CustomBlockingQueue<>(3);

		Thread p1 = new Thread(new Producer<Integer>(blockingQueue, 1));
		Thread p2 = new Thread(new Producer<Integer>(blockingQueue, 2));
		Thread p3 = new Thread(new Producer<Integer>(blockingQueue, 3));
		Thread p4 = new Thread(new Producer<Integer>(blockingQueue, 4));
		Thread p5 = new Thread(new Producer<Integer>(blockingQueue, 5));
		
		Thread c1 = new Thread(new Consumer<Integer>(blockingQueue));
		Thread c2 = new Thread(new Consumer<Integer>(blockingQueue));
		Thread c3 = new Thread(new Consumer<Integer>(blockingQueue));
		Thread c4 = new Thread(new Consumer<Integer>(blockingQueue));
		Thread c5 = new Thread(new Consumer<Integer>(blockingQueue));
		
		p1.start(); 
		p2.start(); 
		p3.start(); 
		p4.start(); 
		p5.start();
		Thread.sleep(1000);
		
		c1.start(); 
		c2.start(); 
		c3.start(); 
		c4.start(); 
		c5.start();
		Thread.sleep(1000);
		
		p1.join(); p2.join(); p3.join(); p4.join(); p5.join();
		c1.join(); c2.join(); c3.join(); c4.join(); c5.join();
		Assert.assertEquals("Should be equal", 0, blockingQueue.size());
	}
}
