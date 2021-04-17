package com.lottery.master;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lottery.util.NumberGenerator;

@SpringBootTest(classes={com.lottery.app.MarkSixLotteryApplication.class})
class NumberTests {

	@Test
	void testNumberFour() throws InterruptedException {
		int number = NumberGenerator.getNumber(4);
		boolean result = number >= 1000 && number <= 9999;
		assertEquals(result, true);
	}
	
	@Test
	void testNumberSix() throws InterruptedException {
		int number = NumberGenerator.getNumber(6);
		boolean result = number >= 1000 && number <= 9999;
		assertEquals(result, false);
	}

}
