package com.samcodes.amazonmobileanalytics;

import android.os.Bundle;
import android.util.Log;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.*;
import org.haxe.extension.Extension;

public class AmazonMobileAnalyticsExtension extends Extension {
	private static final String TAG = "AmazonMobileAnalyticsExtension";
	private static MobileAnalyticsManager analytics = null;
	
	private static String appId = "::ENV_AmazonMobileAnalyticsAppId::";
	private static String identityPoolId = "::ENV_AmazonMobileAnalyticsIdentityPoolId::";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Log.i(TAG, "Will get or create analytics manager");
			analytics = MobileAnalyticsManager.getOrCreateInstance(mainActivity.getApplicationContext(), appId, identityPoolId);
		} catch(InitializationException ex) {
			Log.e(TAG, "Failed to create AmazonMobileAnalytics manager");
			return;
		}
		Log.i(TAG, "Did retrieve or create analytics manager");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		
		analytics.getSessionClient().pauseSession();
		// Attempt to send any events that have been recorded to the Mobile Analytics service.
		analytics.getEventClient().submitEvents();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getSessionClient().resumeSession();
	}
	
	public static void addGlobalAttribute(String attributeName, String value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().addGlobalAttribute(attributeName, value);
	}
	
	public static void addGlobalAttributeForEventType(String eventType, String attributeName, String value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().addGlobalAttribute(eventType, attributeName, value);
	}
	
	public static void removeGlobalAttribute(String attributeName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().removeGlobalAttribute(attributeName);
	}
	
	public static void removeGlobalAttributeForEventType(String eventType, String attributeName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().removeGlobalAttribute(eventType, attributeName);
	}
	
	public static void addGlobalMetric(String metricName, float value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().addGlobalMetric(metricName, (double)value);
	}
	
	public static void addGlobalMetricForEventType(String eventType, String metricName, float value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().addGlobalMetric(eventType, metricName, (double)value);
	}
	
	public static void removeGlobalMetric(String metricName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().removeGlobalMetric(metricName);
	}
	
	public static void removeGlobalMetricForEventType(String eventType, String metricName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().removeGlobalMetric(eventType, metricName);
	}
	
	public static void recordEvent(String eventType, String[] attributeNames, String[] attributeValues, String[] metricNames, float[] metricValues) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		
		AnalyticsEvent event = analytics.getEventClient().createEvent(eventType);
		for(int i = 0; i < attributeNames.length; i++) {
			Log.i(TAG, "Adding attribute [" + attributeNames[i] + "=" + attributeValues[i] + " to event " + eventType);
			event.addAttribute(attributeNames[i], attributeValues[i]);
		}
		for(int i = 0; i < metricNames.length; i++) {
			double value = metricValues[i];
			Log.i(TAG, "Adding metric [" + metricNames[i] + "=" + metricValues[i] + " to event " + eventType);
			event.addMetric(metricNames[i], value);
		}
		
		analytics.getEventClient().recordEvent(event);
	}
	
	public static void submitEvents() {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		analytics.getEventClient().submitEvents();
	}
}