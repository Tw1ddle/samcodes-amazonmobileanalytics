#include "SamcodesAmazonMobileAnalytics.h"

#import <UIKit/UIKit.h>

#import "AWSMobileAnalytics.h"

namespace samcodesamazonmobileanalytics
{
	static AWSMobileAnalytics* analytics = 0;
	
	void init(const char* appId, const char* identityPoolId)
	{
		analytics = [AWSMobileAnalytics mobileAnalyticsForAppId:[NSString stringWithUTF8String:appId] identityPoolId:[NSString stringWithUTF8String:identityPoolId]];
	}
	
	void addGlobalAttribute(const char* attributeName, const char* attributeValue)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient addGlobalAttribute:[NSString stringWithUTF8String:attributeValue] forKey:[NSString stringWithUTF8String:attributeName]];
	}
	
	void addGlobalAttributeForEventType(const char* eventType, const char* attributeName, const char* attributeValue)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient addGlobalAttribute:[NSString stringWithUTF8String:attributeValue] forKey:[NSString stringWithUTF8String:attributeName] forEventType:[NSString stringWithUTF8String:eventType]];
	}
	
	void removeGlobalAttribute(const char* attributeName)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient removeGlobalAttributeForKey:[NSString stringWithUTF8String:attributeName]];
	}
	
	void removeGlobalAttributeForEventType(const char* eventType, const char* attributeName)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient removeGlobalAttributeForKey:[NSString stringWithUTF8String:attributeName] forEventType:[NSString stringWithUTF8String:eventType]];
	}
	
	void addGlobalMetric(const char* metricName, float metricValue)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient addGlobalMetric:[NSNumber numberWithFloat:metricValue] forKey:[NSString stringWithUTF8String:metricName]];
	}
	
	void addGlobalMetricForEventType(const char* eventType, const char* metricName, float metricValue)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient addGlobalMetric:[NSNumber numberWithFloat:metricValue] forKey:[NSString stringWithUTF8String:metricName] forEventType:[NSString stringWithUTF8String:eventType]];
	}
	
	void removeGlobalMetric(const char* metricName)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient removeGlobalMetricForKey:[NSString stringWithUTF8String:metricName]];
	}
	
	void removeGlobalMetricForEventType(const char* eventType, const char* metricName)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
	}
	
	void submitEvents()
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		[analytics.eventClient submitEvents];
	}
	
	void recordEvent(const char* eventType, const char** attributeKeys, const char** attributeValues, const char** metricKeys, float* metricValues, int attributeCount, int metricCount)
	{
		if(!analytics || !analytics.eventClient)
		{
			return;
		}
		id<AWSMobileAnalyticsEvent> event = [analytics.eventClient createEventWithEventType:[NSString stringWithUTF8String:eventType]];
		
		for(int i = 0; i < attributeCount; i++)
		{
			[event addAttribute:[NSString stringWithUTF8String:attributeValues[i]] forKey:[NSString stringWithUTF8String:attributeKeys[i]]];
		}
		for(int i = 0; i < metricCount; i++)
		{
			[event addMetric:[NSNumber numberWithFloat:metricValues[i]] forKey:[NSString stringWithUTF8String:metricKeys[i]]];
		}
		
		[analytics.eventClient recordEvent:event];
	}
}