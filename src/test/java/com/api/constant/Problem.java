package com.api.constant;

public enum Problem {

	SMARTPHONE_IS_RUNNING_SLOW(1), POOR_BATTERY_LIFE(2), PHONE_ORD_APP_CRASHES(3), SYNC_ISSUE(3),
	MICRO_SD_CARD_IS_NOT_WORKING_ON_YOUR_PHONE(4), OVERHEATING(5);

	int code;

	Problem(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
