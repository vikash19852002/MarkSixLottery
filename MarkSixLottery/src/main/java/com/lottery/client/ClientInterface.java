package com.lottery.client;

/**
 * This interface is designed for communication from LotterServer to client
 * 
 * @author Vikas
 *
 */
public interface ClientInterface {

	default boolean notifyWinner(boolean isWinner) {
		if(isWinner) {
			System.out.println("**Congratualtion " + getName() + "!!! You are winner.");
		} else {
			System.out.println("Sorry " + getName() + ", Better luck next time.");
		}
		return isWinner;
	}
	
	public void buyTicket();
	
	public String getName();
	
	public void setNumber(int number);
	
	public int getNumber();
}
