package com.samcodes.amazonmobileanalytics;

import android.os.Bundle;
import android.util.Log;
import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.*;
import java.lang.Throwable;
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
			Log.e(TAG, "Failed to create AmazonMobileAnalytics manager, initialization exception");
			return;
		} catch(Throwable e) {
			// Catchall for NoSuchMethodErrors and other exceptions from outdated mobile analytics SDK
			Log.e(TAG, "Failed to create AmazonMobileAnalytics manager, other error/exception");
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
		submitEvents(); // Attempt to send any events that have been recorded to the Mobile Analytics service.
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
		//Log.i(TAG, "Adding global attribute with name " + attributeName + " and value " + value);
		analytics.getEventClient().addGlobalAttribute(attributeName, value);
	}
	
	public static void addGlobalAttributeForEventType(String eventType, String attributeName, String value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Adding global attribute for event type " + eventType + " with name " + attributeName + " and value " + value);
		analytics.getEventClient().addGlobalAttribute(eventType, attributeName, value);
	}
	
	public static void removeGlobalAttribute(String attributeName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Removing global attribute with name " + attributeName);
		analytics.getEventClient().removeGlobalAttribute(attributeName);
	}
	
	public static void removeGlobalAttributeForEventType(String eventType, String attributeName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Removing global attribute for event type " + eventType + " with name " + attributeName);
		analytics.getEventClient().removeGlobalAttribute(eventType, attributeName);
	}
	
	public static void addGlobalMetric(String metricName, float value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Adding global metric " + metricName + " with value " + value);
		analytics.getEventClient().addGlobalMetric(metricName, (double)value);
	}
	
	public static void addGlobalMetricForEventType(String eventType, String metricName, float value) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Adding global metric " + metricName + " with value " + value + " for event type " + eventType);
		analytics.getEventClient().addGlobalMetric(eventType, metricName, (double)value);
	}
	
	public static void removeGlobalMetric(String metricName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Removing global metric " + metricName);
		analytics.getEventClient().removeGlobalMetric(metricName);
	}
	
	public static void removeGlobalMetricForEventType(String eventType, String metricName) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		//Log.i(TAG, "Removing global metric " + metricName + " for event type " + eventType);
		analytics.getEventClient().removeGlobalMetric(eventType, metricName);
	}
	
	public static void recordEvent(String eventType, String[] attributeNames, String[] attributeValues, String[] metricNames, float[] metricValues) {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		
		AnalyticsEvent event = analytics.getEventClient().createEvent(eventType);
		for(int i = 0; i < attributeNames.length; i++) {
			//Log.i(TAG, "Adding attribute [" + attributeNames[i] + "=" + attributeValues[i] + "] to event " + eventType);
			event.addAttribute(attributeNames[i], attributeValues[i]);
		}
		for(int i = 0; i < metricNames.length; i++) {
			double value = metricValues[i];
			//Log.i(TAG, "Adding metric [" + metricNames[i] + "=" + metricValues[i] + "] to event " + eventType);
			event.addMetric(metricNames[i], value);
		}
		
		//Log.i(TAG, "Recording event " + eventType);
		analytics.getEventClient().recordEvent(event);
	}
	
	public static void submitEvents() {
		if(analytics == null || analytics.getEventClient() == null) {
			Log.e(TAG, "Analytics manager is null");
			return;
		}
		
		Log.i(TAG, "Submitting events");
		analytics.getEventClient().submitEvents();
	}
}