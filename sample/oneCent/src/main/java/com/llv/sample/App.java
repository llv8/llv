package com.llv.sample;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		double a = 1.0;
		double b =0.8;
		double c =a-b;
		System.out.println(c);
		System.out.println(0.7);
		double d = BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(0.8)).doubleValue();
		System.out.println(d);;
	}
}
