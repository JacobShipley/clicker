package com.jakes.clicker.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.jakes.clicker.Consts;
import com.jakes.clicker.MainGame;
import com.jakes.clicker.ServiceHelper;

public class AndroidLauncher extends AndroidApplication implements ServiceHelper, GameHelperListener {
	private GameHelper helper;
	private static final String AD_UNIT_ID = "ca-app-pub-5912289119593352/9539341248";
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		helper = new GameHelper(this);
		helper.setup(this);
		helper.setUnknownErrorMessage("Sign in for achievements and leaderboards!");
        
		// Create an ad.
	    AdView adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    //LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

	    LayoutInflater inflater =
	    	    (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_main, null);
	    MainGame game = new MainGame(this);
	    

	    View gameView = initializeForView(game);
	    
	    layout.addView(gameView);
	    
	    RelativeLayout.LayoutParams adParams = 
	            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
	                    RelativeLayout.LayoutParams.WRAP_CONTENT);
	        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
	        //adParams.addRule(RelativeLayout.ALIGN_PARENT);
	        

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .addTestDevice("0A56BB6AD0BA010731F6886054801B2E")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
	    
	    layout.addView(adView, adParams);

	    setContentView(layout);
		//AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new MainGame(), config);
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}

	@Override
	public void onStart(){
		super.onStart();
		helper.onStart(this);
	}

	@Override
	public void onStop(){
		super.onStop();
		helper.onStop();
	}
	
	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		helper.onActivityResult(request, response, data);
	}

	@Override
	public boolean getSignedIn() {
		return helper.isSignedIn();
	}

	@Override
	public void login() {	
		try {
			runOnUiThread(new Runnable(){
				public void run() {
					helper.beginUserInitiatedSignIn();
				}
			});
		} catch (final Exception ex) {
		}
	}

	@Override
	public void submitScore(int score) {	
		helper.getGamesClient().submitScore(Consts.LEADERBOARD, score);
	}

	@Override
	public void unlockAchievement(String achievementId) {	
		helper.getGamesClient().unlockAchievement(achievementId);
	}
	
	@Override
	public void incrementAchievement(String achievemendId){
		helper.getGamesClient().incrementAchievement(achievemendId, 1);
	}

	@Override
	public void getLeaderboard() {	
		startActivityForResult(helper.getGamesClient().getLeaderboardIntent(Consts.LEADERBOARD), 100);
	}

	@Override
	public void getAchievements() {		
		startActivityForResult(helper.getGamesClient().getAchievementsIntent(), 101);
	}

	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
		
	}
}
