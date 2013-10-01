package org.pzjay.demo;

// 手势滑动距离控制
class SnsConstant {
	private static final int FLING_MIN_X_DISTANCE = 150;
	private static final int FLING_MAX_Y_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;


	public static int getFlingMinXDistance() {
		return FLING_MIN_X_DISTANCE;
	}


	public static int getFingMaxYDistance() {
		return FLING_MAX_Y_DISTANCE;
	}


	public static int getFlingMinVelocity() {
		return FLING_MIN_VELOCITY;
	}
}