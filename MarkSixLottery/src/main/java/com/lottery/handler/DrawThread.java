package com.lottery.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.lottery.client.ClientInterface;

/**
 * This class is designed to run a Scheduler thread at specified time 
 * and picks a Unique/Random number in Lucky draw.
 * 
 * @author Vikas
 *
 */
public class DrawThread extends Thread{

	private Semaphore lock;
	private int durationInSecond;
	private Map<Integer, ClientInterface> lotteryClientInfo;
	private Random random;

	public DrawThread(Semaphore lock, int durationInSecond, Map<Integer, ClientInterface> lotteryClientInfo) {
		this.lock = lock;
		this.durationInSecond = durationInSecond;
		this.lotteryClientInfo = lotteryClientInfo;
		this.random = new Random();
	}

	@Override
	public void run() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(() -> declareDraw(), durationInSecond, durationInSecond, TimeUnit.SECONDS);
	}

	private void declareDraw() {
		try {
			lock.acquire();
			System.out.println("************************");
			System.out.println("*     DRAW Started     *");
			System.out.println("************************");
			if(lotteryClientInfo.size() == 0) {
				System.out.println("Oops!!! No one bought the ticket.");
				return;
			}
			ArrayList<Integer> keySet = new ArrayList<>(lotteryClientInfo.keySet());
			int luckyNumber = random.nextInt(keySet.size());
			System.out.println("   LUCKY NUMBER: " + keySet.get(luckyNumber));
			new NotificationThread(keySet.get(luckyNumber), new HashMap<>(lotteryClientInfo)).start();
			lotteryClientInfo.clear();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.release();
		}
	}

}
