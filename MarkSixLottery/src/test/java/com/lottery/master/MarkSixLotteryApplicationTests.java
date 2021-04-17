package com.lottery.master;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

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
	void purchaseLotter() throws InterruptedException {
		List<ClientInterface> clientList = new ArrayList<>();
		LotteryStore.getInstance().startDrawScheduler();
		for(int i=1; i <= 10; i++) {
			ClientInterface client = mock(Client.class);
			clientList.add(client);
			LotteryStore.getInstance().buyLottery(client);
			if(i%10 == 0) {
				Thread.sleep(LotteryStore.durationInSecond * 1000);
			}
		}
		//Test all methods are called inside lottery system
		for(ClientInterface client : clientList) {
			verify(client, times(1)).setNumber(Mockito.anyInt());
			verify(client, times(1)).getNumber();
			verify(client, times(1)).notifyWinner(Mockito.anyBoolean());
		}
	}

}
