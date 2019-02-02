package com.llv.sample;

import com.llv.sample.api.IFuture;
import com.llv.sample.api.IFutureListener;
import com.llv.sample.api.MyFutureImpl;

public class FutureGood {

	public static void main(String[] args) {
		new FutureGood().add(3 * 1000, 1, 2).addListener(new IFutureListener<Integer>() {

			@Override
			public void operationCompleted(IFuture<Integer> future) throws Exception {
				System.out.println(future.get());
			}
		});
		System.out.println("xxxx");

	}

	/**
	 * 延迟加
	 * 
	 * @param delay 延时时长 milliseconds
	 * @param a     加数
	 * @param b     加数
	 * @return 异步结果
	 */
	public MyFutureImpl<Integer> add(long delay, int a, int b) {
		MyFutureImpl<Integer> future = new MyFutureImpl<Integer>();
		new Thread(new DelayAdditionTask(delay, a, b, future)).start();
		return future;
	}

	private class DelayAdditionTask implements Runnable {

		private long delay;

		private int a, b;

		private MyFutureImpl<Integer> future;

		public DelayAdditionTask(long delay, int a, int b, MyFutureImpl<Integer> future) {
			super();
			this.delay = delay;
			this.a = a;
			this.b = b;
			this.future = future;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(delay);
				Integer i = a + b;
				future.setSuccess(i);
			} catch (InterruptedException e) {
			}
		}

	}
}