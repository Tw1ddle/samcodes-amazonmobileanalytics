package com.samcodes.amazonmobileanalytics;

import android.os.Bundle;
import android.util.Log;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.*;
import org.haxe.extension.Extension;

public class AmazonMobileAnalyticsExtension extends Extension {
	private static MobileAnalyticsManager analytics;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			analytics = MobileAnalyticsManager.getOrCreateInstance(mainActivity.getApplicationContext(), "appId", "identityPoolId"); // TODO
		} catch(InitializationException ex) {
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(analytics != null) {
			analytics.getSessionClient().pauseSession();
			// Attempt to send any events that have been recorded to the Mobile Analytics service.
			analytics.getEventClient().submitEvents();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if(analytics != null)  {
			analytics.getSessionClient().resumeSession();
		}
	}
	
	private static final int STATE_LOSE = 0;
	private static final int STATE_WIN = 1;
	public static void onCustomEvent(String levelName, String difficulty, double timeToComplete, int playerState) {
		// TODO
		
		//Create a Level Complete event with some attributes and metrics(measurements)
		//Attributes and metrics can be added using with statements
		AnalyticsEvent levelCompleteEvent = analytics.getEventClient().createEvent("LevelComplete")
				.withAttribute("LevelName", levelName)
				.withAttribute("Difficulty", difficulty)
				.withMetric("TimeToComplete", timeToComplete);

		//attributes and metrics can also be added using add statements
		if (playerState == STATE_LOSE)
			levelCompleteEvent.addAttribute("EndState", "Lose");
		else if (playerState == STATE_WIN)
			levelCompleteEvent.addAttribute("EndState", "Win");

		//Record the Level Complete event
		if(analytics != null) {
			analytics.getEventClient().recordEvent(levelCompleteEvent);
		}
	}
}