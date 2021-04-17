package com.lottery.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import com.lottery.client.ClientInterface;
import com.lottery.handler.DrawThread;
import com.lottery.util.NumberGenerator;

/**
 * This class is designed to serve client request and start Draw scheduler
 * 
 * @author Vikas
 *
 */
public class LotteryStore {

	public static final LotteryStore instance;
	public static final int durationInSecond;
	public static final int lotteryNumberLength;
	private final Map<Integer, ClientInterface> lotteryClientInfo;
	private final Semaphore lock;
	
	static {
		instance = new LotteryStore();
		durationInSecond = 20;
		lotteryNumberLength = 6;
	}
	
	private LotteryStore() {
		lotteryClientInfo = new HashMap<>();
		lock = new Semaphore(1);
	}
	

	public ClientInterface buyLottery(ClientInterface client) {
		try {
			lock.acquire();
			//get Unique Number here
			Integer number = getUniqueNumber();
			client.setNumber(number);
			//Allot that number to client
			lotteryClientInfo.put(number, client);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.release();
		}
		return client;
	}
	
	/**
	 * It fetch unique number from NumberGenerator
	 * 
	 * @return
	 * 		Unique number
	 */
	public int getUniqueNumber() {
		int number = NumberGenerator.getNumber(lotteryNumberLength);
		while(lotteryClientInfo.keySet().contains(number)) {
			number = NumberGenerator.getNumber(lotteryNumberLength);
		}
		return number;
	}
	
	/**
	 * This method start Draw scheduler which run continuously after given duration
	 */
	public void startDrawScheduler() {
		DrawThread drawThread = new DrawThread(lock, durationInSecond, lotteryClientInfo);
		drawThread.start();
		try {
			drawThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static LotteryStore getInstance() {
		return instance;
	}
	
}
