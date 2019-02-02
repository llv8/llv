package com.llv.sample.api;

public interface IFutureListener<V> {
	public void operationCompleted(IFuture<V> future) throws Exception;
}
