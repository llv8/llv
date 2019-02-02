package com.llv.sample.api;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyFutureImpl<V> implements IFuture<V> {

	protected volatile V result; // 需要保证其可见性
	/**
	 * 监听器集
	 */
	protected Collection<IFutureListener<V>> listeners = new CopyOnWriteArrayList<IFutureListener<V>>();

	@Override
	public IFuture<V> addListener(IFutureListener<V> listener) {
		if (listener == null) {
			throw new NullPointerException("listener");
		}
		listeners.add(listener);
		return this;
	}

	public void setSuccess(V result) {
		this.result = result;
		notifyListeners();
	}

	private void notifyListeners() {
		for (IFutureListener<V> l : listeners) {
			try {
				l.operationCompleted(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public V get() {
		return result;
	}

}
