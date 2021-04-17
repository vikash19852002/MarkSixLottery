package com.lottery.util;

import java.util.Random;

/**
 * 
 * This class generates random number based on length provided.
 * 
 * @author Vikas
 */
public class NumberGenerator {

	/**
	 * It calculates and generate unique number with given length
	 * 
	 * @param n
	 *      Required length of number
	 * @return
	 * 		Random number of given length
	 */
	public static int getNumber(int n) {
		int m = (int) Math.pow(10, n - 1);
		return m + new Random().nextInt(9 * m);
	}
}
