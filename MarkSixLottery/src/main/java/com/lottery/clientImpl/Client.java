package com.lottery.clientImpl;

import com.lottery.client.ClientInterface;
import com.lottery.dto.UserDto;
import com.lottery.service.LotteryStore;

/**
 * This class is designed to handle Client related task.
 * 
 * @author Vikas
 *
 */
public class Client implements ClientInterface {

	private UserDto user;
	
	public Client(String name) {
		user = new UserDto(name);
	}
	
	@Override
	public String getName() {
		return user.getName();
	}
	
	@Override
	public void setNumber(int number) {
		user.setNumber(number);
	}

	@Override
	public int getNumber() {
		return user.getNumber();
	}
	
	/**
	 * This method is used to buy ticket by clients.
	 */
	@Override
	public void buyTicket() {
		//Buy ticket from Lottery store here
		LotteryStore.getInstance().buyLottery(this);
		System.out.println(user.getName() + " : Got the Lottery number: " + user.getNumber());
	}
}
