package com.jakes.clicker.desktop;

import com.jakes.clicker.ServiceHelper;

public class ServiceHelperDesktop implements ServiceHelper{
	boolean signedInStateGPGS = false;

	@Override
	public boolean getSignedIn() {
		return signedInStateGPGS;
	}

	@Override
	public void login() {
		signedInStateGPGS = true;
	}

	@Override
	public void submitScore(int score) {
	}

	@Override
	public void unlockAchievement(String achievementId) {
		System.out.println("Achieved: " + achievementId);
	}

	@Override
	public void getLeaderboard() {
	}

	@Override
	public void getAchievements() {
	}

	@Override
	public void incrementAchievement(String achievementId) {
	}
}
