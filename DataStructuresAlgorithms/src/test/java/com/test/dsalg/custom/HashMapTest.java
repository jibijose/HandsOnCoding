package com.test.dsalg.custom;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class HashMapTest {

	@Test
	public void testPutAndGet() {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		hashMap.put(1, 100);
		Assert.assertEquals("Should be equal", new Integer(100), hashMap.get(1));
	}
	
	@Test
	public void testPutMulipleAndGet() {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		
		hashMap.put(1, 100);
		hashMap.put(1, 1000);
		Assert.assertEquals("Should be equal", new Integer(1000), hashMap.get(1));
	}
}
