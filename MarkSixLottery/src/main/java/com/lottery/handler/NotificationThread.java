package com.lottery.handler;

import java.util.Map;

import com.lottery.client.ClientInterface;

/**
 * This class is designed to send event Notification to all clients who bought the ticket.
 * 
 * @author Vikas
 *
 */
public class NotificationThread extends Thread{

	private Integer luckyNumber;
	private Map<Integer, ClientInterface> lotteryClientInfo;

	public NotificationThread(Integer luckyNumber, Map<Integer, ClientInterface> lotteryClientInfo) {
		this.luckyNumber = luckyNumber;
		this.lotteryClientInfo = lotteryClientInfo;
	}

	@Override
	public void run() {
		lotteryClientInfo.entrySet().stream().forEach(clientInfo -> {
			clientInfo.getValue().notifyWinner(clientInfo.getValue().getNumber() == luckyNumber);
		});
	}

}
