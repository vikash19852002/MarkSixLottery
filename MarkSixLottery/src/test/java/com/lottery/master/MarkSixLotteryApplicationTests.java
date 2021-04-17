package com.lottery.master;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.lottery.client.ClientInterface;
import com.lottery.clientImpl.Client;
import com.lottery.service.LotteryStore;

@SpringBootTest(classes={com.lottery.app.MarkSixLotteryApplication.class})
@RunWith(MockitoJUnitRunner.class)
class MarkSixLotteryApplicationTests {

	@Test
	void purchaseLottery() throws InterruptedException, ExecutionException {
		List<Future<ClientInterface>> clientList = new ArrayList<>();
		LotteryStore.getInstance().startDrawScheduler();
		
		ExecutorService executor =  Executors.newFixedThreadPool(5);
		for(int i=1; i <= 20; i++) {
			clientList.add(executor.submit(()-> {
				ClientInterface client = mock(Client.class);
				client.buyTicket();
				return client;
			}));
		}
		
		Thread.sleep(LotteryStore.durationInSecond * 1000);
		
		//Test all methods are called inside lottery system
		for(Future<ClientInterface> futureClient : clientList) {
			ClientInterface client = futureClient.get();
			verify(client, times(1)).setNumber(Mockito.anyInt());
			verify(client, times(1)).getNumber();
			verify(client, times(1)).notifyWinner(Mockito.anyBoolean());
		}
	}
	
}
