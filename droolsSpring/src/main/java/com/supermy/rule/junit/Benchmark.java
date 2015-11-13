package com.supermy.rule.junit;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.supermy.rule.service.LoginServiceImpl;


public class Benchmark {
	public static void main(String[] args) throws InterruptedException {
		Benchmark benchmark = new Benchmark();
		benchmark.test();
	}

	public void test() throws InterruptedException {
		int threadCount = 2500;
		final int genCount = 100;
		//StopWatch watch = new StopWatch();

		final CountDownLatch latch = new CountDownLatch(threadCount);
		long start  =  System.currentTimeMillis();
		//watch.start();
		for (int i = 0; i < threadCount; ++i) {
			Thread thread = new Thread() {
				public void run() {
					for (int j = 0; j < genCount; ++j) {
	
					}
					latch.countDown();
				}
			};
			thread.start();
		}

		latch.await();
	//	watch.stop();
		
		long end = System.currentTimeMillis()-start;

		System.err.println("time:" + end);
		//time:0:00:49.145  
		//speed:4069589.9888086272  
		System.err.println("speed:" + genCount * threadCount / (end/ 1000.0));
	}
}
