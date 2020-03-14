package com.wongnai.interview.wamup;


import org.junit.Test;

import java.util.InputMismatchException;

public class DigitalRoot {
	public Object check(long number) {
		//TODO: Warmup practice => Implement this method to find out the digital root number of the input.
		// The digital root of a non-negative integer is the single-digit value obtained by an iterative process
		// of summing digits, on each iteration using the result from the previous iteration to compute the digit sum.
		// The process continues until a single-digit number is reached.
		// -------------------------------
		// -------------------------------
		// Example:
		//   Input : 12345
		//   Output : 6 (Because 1 + 2 + 3 + 4 + 5 equals 15 and then 1 + 5 equals 6)
		// -------------------------------
		// -------------------------------
		// All test case in DigitalRootTest must be passed.

		if (number<=0){
			throw new InputMismatchException();
		}
		String numberasString = String.valueOf(number);
		while (numberasString.length() != 1 ){
			long aws = 0;
			for(int i = 0 ; i<numberasString.length();i++){
				aws += Character.getNumericValue(numberasString.charAt(i));
			}
			numberasString = Long.toString(aws);
		}
		return Long.valueOf(numberasString);


	}
}
