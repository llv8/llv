package com.llv.sample;

public class CauseByTest {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			myException();
		} catch (RuntimeException e) {
			for (StackTraceElement st : e.getCause().getStackTrace()) {
				System.out.println(st);
			}
			throw e;
		}

	}

	public static void myException() {
		try {
			throw new RuntimeException("my exception");
		} catch (RuntimeException e) {
			RuntimeException ex = new IndexOutOfBoundsException("index out");
			ex.initCause(e);
			throw ex;
		}

	}
}
