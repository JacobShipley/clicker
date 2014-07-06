package com.jakes.clicker;

/**
 * Google Play Game Services service helper interface.  Exposes common methods for interacting with GPGS
 * @author Jake
 *
 */
public interface ServiceHelper {
	public boolean getSignedIn();
	public void login();
	public void submitScore(int score);
	public void unlockAchievement(String achievementId);
	public void incrementAchievement(String achievementId);
	public void getLeaderboard();
	public void getAchievements();
}
