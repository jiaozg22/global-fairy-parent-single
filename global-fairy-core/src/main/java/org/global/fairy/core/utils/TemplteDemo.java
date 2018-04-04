package org.global.fairy.core.utils;

import java.util.Date;

public class TemplteDemo {

}

interface TimeStamped {
	long getStamp();
}

class TimeStampedImpl implements TimeStamped {
	private final long timeStamp;

	public TimeStampedImpl() {
		timeStamp = new Date().getTime();
	}

	@Override
	public long getStamp() {
		return timeStamp;
	}

}

interface SerialNumbered {
	long getSerialNumber();
}

class SerialNumberedImpl implements SerialNumbered {
	private static long counter = 1;
	private final long serialNumber = counter++;

	@Override
	public long getSerialNumber() {
		return serialNumber;
	}
}

interface Basic {
	public void set(String val);

	public String get();
}

class BasicImpl implements Basic {
	private String value;
	public String val;

	@Override
	public void set(String val) {
		value = val;
	}

	@Override
	public String get() {
		return value;
	}
}

class Mixin extends BasicImpl implements TimeStamped, SerialNumbered {
	private TimeStamped timeStamped = new TimeStampedImpl();
	private SerialNumbered serialNumbered = new SerialNumberedImpl();
	
	@Override
	public long getSerialNumber() {
		return serialNumbered.getSerialNumber();//依赖 动态了类型
	}

	@Override
	public long getStamp() {
		return timeStamped.getStamp();
	}

}