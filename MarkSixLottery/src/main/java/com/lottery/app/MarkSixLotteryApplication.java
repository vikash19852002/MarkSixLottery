package com.lottery.app;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lottery.client.ClientInterface;
import com.lottery.clientImpl.Client;
import com.lottery.service.LotteryStore;

/**
 * This class is responsible to start the application
 * 
 * @author Vikas
 *
 */
@SpringBootApplication
public class MarkSixLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkSixLotteryApplication.class, args);
		LotteryStore.getInstance().startDrawScheduler();
		
		//TODO: To test the application by user input please uncomment below line.
		acceptClient();
	}
	
	/**
	 * Method is designed for testing the Lottery system by user input
	 */
	public static void acceptClient() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Please enter your name: ");
			String name  = sc.nextLine();
			ClientInterface client = new Client(name);
			client.buyTicket();
		}
	}

}
