package com.vipapp.appmark2.items;

public class TransformedItem<T1, T2> {
	private T1 item1;
	private T2 item2;

	public TransformedItem(T1 t1, T2 t2) {
		this.item1 = t1;
		this.item2 = t2;
	}

	public T1 getItem1() {
		return this.item1;
	}

	public T2 getItem2() {
		return this.item2;
	}

	public void setItem1(T1 t1) {
		this.item1 = t1;
	}

	public void setItem2(T2 t2) {
		this.item2 = t2;
	}
}
