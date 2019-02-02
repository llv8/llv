package com.llv.sample.api;

public interface IFuture<V> {

	IFuture<V> addListener(IFutureListener<V> l);

	void setSuccess(V v);

	V get();

}