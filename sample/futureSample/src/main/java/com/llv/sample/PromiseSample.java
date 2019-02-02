package com.llv.sample;

import java.util.concurrent.CompletableFuture;

public class PromiseSample {
	public static void main(String[] args) throws InterruptedException {
		// completedFutureExample();
		// runAsyncExample();
		// thenApplyExample();
		thenApplyAsyncExample();
	}

	static void completedFutureExample() {
		CompletableFuture cf = CompletableFuture.completedFuture("message");
		System.out.println(cf.isDone());
		System.out.println(cf.getNow(null));
	}

	static void runAsyncExample() throws InterruptedException {
		CompletableFuture cf = CompletableFuture.runAsync(() -> {// 以Async结尾的方法，它会异步的执行(没有指定executor的情况下)，
																	// 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务
			System.out.println(Thread.currentThread() + "--isDeamon:" + Thread.currentThread().isDaemon());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		System.out.println(cf.isDone());
		Thread.sleep(10000);
		System.out.println(cf.isDone());
	}

	static void thenApplyExample() {
		CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
			System.out.println(Thread.currentThread() + "--isDeamon:" + Thread.currentThread().isDaemon());
			return s.toUpperCase();
		});
		System.out.println(cf.getNow(null));
	}

	static void thenApplyAsyncExample() {
		CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
			System.out.println(Thread.currentThread() + "--isDeamon:" + Thread.currentThread().isDaemon());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s.toUpperCase();
		});
		System.out.println(cf.getNow(null));
		System.out.println(cf.join());
	}

}
