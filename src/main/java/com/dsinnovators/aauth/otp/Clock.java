package com.dsinnovators.aauth.otp;

public class Clock {

	private final int interval;
	private final long initTimeSeconds;

	public Clock() {
		interval = 30;
		initTimeSeconds = System.currentTimeMillis() / 1000;
	}

	public Clock(int interval) {
		this.interval = interval;
		initTimeSeconds = System.currentTimeMillis() / 1000;
	}

	public long getCurrentInterval() {
		long currentTimeSeconds = (System.currentTimeMillis() - initTimeSeconds) / 1000;
		return currentTimeSeconds / interval;
	}
}
